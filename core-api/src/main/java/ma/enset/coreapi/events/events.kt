package ma.enset.coreapi.events

abstract class BaseEvent<T>(
        open val id: String
)

data class CustomerCreatedEvent(
        override val id: String,
        val name: String,
        val email:String
):BaseEvent<String>(id)

data class CustomerUpdatedEvent(
        override val id: String,
        val name: String,
        val email:String
):BaseEvent<String>(id)

