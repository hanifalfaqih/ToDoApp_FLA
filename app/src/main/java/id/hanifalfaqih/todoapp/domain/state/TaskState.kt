package id.hanifalfaqih.todoapp.domain.state

interface TaskState {

    fun nextState(): TaskState

    fun getStateName(): String

}