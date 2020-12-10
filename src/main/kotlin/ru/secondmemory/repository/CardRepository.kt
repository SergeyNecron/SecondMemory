package ru.secondmemory.repository

import ru.secondmemory.dto.CardDto
import ru.secondmemory.model.Card
import ru.secondmemory.model.CardType

interface CardRepository {
    // create if not found, when updated
    fun save(type: CardType, dto: CardDto)

    // false if not found
    fun delete(type: CardType, key: String): Boolean

    // null if not found
    fun get(type: CardType, key: String): Card

    fun getAllByType(type: CardType): Collection<CardDto>
}