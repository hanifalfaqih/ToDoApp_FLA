package id.hanifalfaqih.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.data.model.Note
import id.hanifalfaqih.todoapp.data.model.Priority

class NotesAdapter(private val notes: List<Note>, private val onNoteClick: (Note) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note_card, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener { onNoteClick(note) }
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_note_title)
        private val tvContent: TextView = itemView.findViewById(R.id.tv_note_content)
        private val container: View = itemView.findViewById(R.id.ll_note_container)

        fun bind(note: Note) {
            tvTitle.text = note.title
            tvContent.text = note.content
            
            val colorRes = when (note.priority) {
                Priority.HIGH -> R.color.note_pink
                Priority.MEDIUM -> R.color.note_yellow
                Priority.LOW -> R.color.note_blue
            }
            container.setBackgroundColor(ContextCompat.getColor(itemView.context, colorRes))
        }
    }
}