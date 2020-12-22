package ru.secondmemory.util

import ru.secondmemory.exception.NotFoundException
import ru.secondmemory.model.BaseEntity
import ru.secondmemory.model.Card
import ru.secondmemory.model.CardType
import java.lang.IllegalArgumentException

fun <T> checkNotFoundWithId(entity: T?, id: Int): T =
    entity ?: throw NotFoundException("Not found entity with id=$id")


fun <T> checkNotFound(entity: T?, message: String): T =
    entity ?: throw NotFoundException("Not found entity with $message")


fun checkNotFoundWithType(card: Card?, type: CardType): Card =
    checkNotFound(card, "(type='$type')")


fun checkNotFoundWithKey(card: Card?, type: CardType, key: String): Card =
    checkNotFound(card, "(type='$type' key='$key')")


fun checkNew(card: Card): Card =
    if (!card.isNew()) {
        throw IllegalArgumentException("$card + must be new (id=null)")
    } else card


fun assureIdConsistent(entity: BaseEntity, id: Int) {
    if (entity.isNew()) {
        entity.id = id
    } else if (entity.id != id)
        throw IllegalArgumentException("$entity + must be with id= + $id")
}
