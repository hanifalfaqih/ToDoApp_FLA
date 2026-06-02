package id.hanifalfaqih.todoapp.domain.state

class TodoState : TaskState {

    override fun nextState(): TaskState {
        return InProgressState()
    }

    override fun getStateName(): String {
        return "TODO"
    }
}