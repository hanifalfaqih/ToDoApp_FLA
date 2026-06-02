package id.hanifalfaqih.todoapp.domain.model

import id.hanifalfaqih.todoapp.domain.state.TaskState
import id.hanifalfaqih.todoapp.domain.state.TodoState

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: TaskPriority,
    val isCompleted: Boolean,
    val weight: Int,
    val urgent: Boolean,
    var state: TaskState = TodoState()
) {

    fun moveToNextState() {
        state = state.nextState()
    }

    fun getCurrentState(): String {
        return state.getStateName()
    }
}
