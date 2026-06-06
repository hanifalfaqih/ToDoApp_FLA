package id.hanifalfaqih.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.ChipGroup
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.di.AppModule
import id.hanifalfaqih.todoapp.di.ViewModelFactory
import id.hanifalfaqih.todoapp.domain.model.TaskPriority
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import id.hanifalfaqih.todoapp.presentation.create.CreateTaskViewModel
import id.hanifalfaqih.todoapp.presentation.detail.TaskDetailViewModel
import id.hanifalfaqih.todoapp.presentation.update.UpdateTaskViewModel
import kotlinx.coroutines.launch

class NoteDetailFragment : Fragment() {

    private val detailViewModel: TaskDetailViewModel by viewModels {
        AppModule.provideViewModelFactory(requireContext())
    }
    private val createViewModel: CreateTaskViewModel by viewModels {
        AppModule.provideViewModelFactory(requireContext())
    }
    private val updateViewModel: UpdateTaskViewModel by viewModels {
        AppModule.provideViewModelFactory(requireContext())
    }

    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var cgPriority: ChipGroup
    
    private var taskId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_detail, container, false)
        
        taskId = arguments?.getInt("taskId") ?: -1

        etTitle = view.findViewById(R.id.et_note_title)
        etContent = view.findViewById(R.id.et_note_content)
        cgPriority = view.findViewById(R.id.cg_priority)

        view.findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            handleSave()
        }
        
        observeViewModels()

        if (taskId != -1) {
            detailViewModel.loadTaskDetail(taskId)
            view.findViewById<View>(R.id.btn_delete).apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Delete") { _, _ ->
                            detailViewModel.deleteTask(taskId)
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                }
            }
        }

        return view
    }

    private fun handleSave() {
        val title = etTitle.text.toString()
        val content = etContent.text.toString()
        val priority = when (cgPriority.checkedChipId) {
            R.id.chip_high -> "HIGH"
            R.id.chip_medium -> "MEDIUM"
            else -> "LOW"
        }

        if (title.isBlank() && content.isBlank()) {
            findNavController().popBackStack()
            return
        }

        if (taskId == -1) {
            createViewModel.createTask(title, content, priority)
        } else {
            updateViewModel.updateTask(taskId, title, content, priority)
        }
    }

    private fun observeViewModels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.taskDetailState.collect { state ->
                    if (state is UiState.Success) {
                        val task = state.data
                        etTitle.setText(task.title)
                        etContent.setText(task.description)
                        when (task.priority) {
                            TaskPriority.HIGH -> cgPriority.check(R.id.chip_high)
                            TaskPriority.MEDIUM -> cgPriority.check(R.id.chip_medium)
                            TaskPriority.LOW -> cgPriority.check(R.id.chip_low)
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                createViewModel.event.collect { handleEvent(it) }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                updateViewModel.event.collect { handleEvent(it) }
            }
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is Event.NavigateBack -> findNavController().popBackStack()
            is Event.ShowMessage -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }
}