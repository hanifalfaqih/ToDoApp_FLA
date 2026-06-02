package id.hanifalfaqih.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.di.ViewModelFactory
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import id.hanifalfaqih.todoapp.presentation.home.HomeViewModel
import id.hanifalfaqih.todoapp.ui.adapter.NotesAdapter
import kotlinx.coroutines.launch

class NotesDashboardFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_dashboard, container, false)

        setupRecyclerView(view)
        setupSearch(view)
        setupFab(view)
        setupTopProfile(view)
        observeViewModel()

        viewModel.loadTasks()

        return view
    }

    private fun setupRecyclerView(view: View) {
        adapter = NotesAdapter { task ->
            val bundle = Bundle().apply {
                putInt("taskId", task.id)
            }
            findNavController().navigate(R.id.action_notes_to_detail, bundle)
        }
        val rvNotes = view.findViewById<RecyclerView>(R.id.rv_notes)
        rvNotes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvNotes.adapter = adapter
    }

    private fun setupSearch(view: View) {
        val etSearch = view.findViewById<android.widget.EditText>(R.id.search_notes)
        etSearch.addTextChangedListener { text ->
            if (text.isNullOrBlank()) {
                viewModel.loadTasks()
            } else {
                viewModel.searchTask(text.toString())
            }
        }
    }

    private fun setupFab(view: View) {
        view.findViewById<FloatingActionButton>(R.id.fab_add_note).setOnClickListener {
            findNavController().navigate(R.id.action_notes_to_detail)
        }
    }

    private fun setupTopProfile(view: View) {
        view.findViewById<View>(R.id.btn_profile_dashboard).setOnClickListener {
            findNavController().navigate(R.id.action_notes_to_profile)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.taskState.collect { state ->
                    when (state) {
                        is UiState.Success -> {
                            adapter.submitList(state.data)
                        }
                        is UiState.Error -> {
                            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                        }
                        else -> Unit
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    if (event is Event.ShowMessage) {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}