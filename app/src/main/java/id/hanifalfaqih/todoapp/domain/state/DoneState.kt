package id.hanifalfaqih.todoapp.domain.state

class DoneState : TaskState {

    override fun nextState(): TaskState {
        return this
    }

    override fun getStateName(): String {
        return "DONE"
    }
}