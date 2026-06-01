package id.hanifalfaqih.todoapp.domain.state

class InProgressState : TaskState {

    override fun nextState(): TaskState {
        return DoneState()
    }

    override fun getStateName(): String {
        return "IN_PROGRESS"
    }
}