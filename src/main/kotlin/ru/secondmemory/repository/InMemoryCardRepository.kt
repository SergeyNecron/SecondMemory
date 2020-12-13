package ru.secondmemory.repository

import ru.secondmemory.dto.CardDto
import ru.secondmemory.model.Card
import ru.secondmemory.model.CardType
import ru.secondmemory.model.Cards
import ru.secondmemory.util.getEnumCardsTestData
import java.util.*

class InMemoryCardRepository : CardRepository {
    private val repository: EnumMap<CardType, Cards> = getEnumCardsTestData()

    override fun save(type: CardType, dto: CardDto) {
        repository[type]!!.addCard(dto.key, dto.value)
    }

    override fun delete(type: CardType, key: String): Boolean {
        repository[type]!!.cards.remove(key)
        return true
    }

    override fun get(type: CardType, key: String): Card {
        return repository[type]!!.cards[key]!!
    }

    override fun getAllByType(type: CardType): Collection<CardDto> {
        return repository[type]!!
                .cards
                .keys
                .map { CardDto(it, this.get(type, it)) }
    }
}