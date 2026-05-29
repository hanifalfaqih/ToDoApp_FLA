package id.hanifalfaqih.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.data.model.Note
import id.hanifalfaqih.todoapp.data.model.Priority
import id.hanifalfaqih.todoapp.ui.adapter.NotesAdapter

class NotesDashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_dashboard, container, false)

        val rvNotes = view.findViewById<RecyclerView>(R.id.rv_notes)
        val dummyNotes = listOf(
            Note("Product Meeting", "1. Review previous items\n2. Update status", Priority.HIGH),
            Note("Grocery List", "Milk, Bread, Eggs, Fruits", Priority.MEDIUM),
            Note("Quick Ideas", "New app feature for reminders", Priority.LOW),
            Note("Project Specs", "Define goals and timeline", Priority.HIGH)
        )
        
        rvNotes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvNotes.adapter = NotesAdapter(dummyNotes) {
            findNavController().navigate(R.id.action_notes_to_detail)
        }

        view.findViewById<View>(R.id.btn_profile_dashboard).setOnClickListener {
            findNavController().navigate(R.id.action_notes_to_profile)
        }

        view.findViewById<FloatingActionButton>(R.id.fab_add_note).setOnClickListener {
            findNavController().navigate(R.id.action_notes_to_detail)
        }

        return view
    }
}