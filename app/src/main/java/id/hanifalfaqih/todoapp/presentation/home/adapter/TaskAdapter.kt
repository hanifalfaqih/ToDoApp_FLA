package id.hanifalfaqih.todoapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.hanifalfaqih.todoapp.presentation.home.ui.TaskItemUiModel

class TaskAdapter(
    private val onTaskClick: (Int) -> Unit
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val items = mutableListOf<TaskItemUiModel>()

    fun submitList(
        tasks: List<TaskItemUiModel>
    ) {
        items.clear()
        items.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val item = items[position]

        holder.itemView.setOnClickListener {
            onTaskClick(item.id)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TaskViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView)
}