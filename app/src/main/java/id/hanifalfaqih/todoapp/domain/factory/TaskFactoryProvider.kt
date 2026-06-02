package id.hanifalfaqih.todoapp.domain.factory

object TaskFactoryProvider {

    fun getFactory(
        priority: String
    ): TaskFactory {

        return when (priority.uppercase()) {

            "HIGH" ->
                HighPriorityTaskFactory()

            "MEDIUM" ->
                MediumPriorityTaskFactory()

            else ->
                LowPriorityTaskFactory()
        }
    }
}