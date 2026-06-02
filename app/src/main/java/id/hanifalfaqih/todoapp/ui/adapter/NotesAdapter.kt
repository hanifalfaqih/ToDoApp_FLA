package id.hanifalfaqih.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.model.TaskPriority

class NotesAdapter(private val onNoteClick: (Task) -> Unit) :
    ListAdapter<Task, NotesAdapter.NoteViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note_card, parent, false)
        return NoteViewHolder(view, onNoteClick)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteViewHolder(itemView: View, private val onNoteClick: (Task) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_note_title)
        private val tvContent: TextView = itemView.findViewById(R.id.tv_note_content)
        private val tvPriority: TextView = itemView.findViewById(R.id.tv_note_priority)
        private val container: View = itemView.findViewById(R.id.ll_note_container)

        fun bind(task: Task) {
            tvTitle.text = task.title
            tvContent.text = task.description
            tvPriority.text = task.priority.name
            
            val colorRes = when (task.priority) {
                TaskPriority.HIGH -> R.color.note_pink
                TaskPriority.MEDIUM -> R.color.note_yellow
                TaskPriority.LOW -> R.color.note_blue
            }
            container.setBackgroundColor(ContextCompat.getColor(itemView.context, colorRes))
            itemView.setOnClickListener { onNoteClick(task) }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean = oldItem == newItem
    }
}