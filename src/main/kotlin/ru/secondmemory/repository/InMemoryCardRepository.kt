package ru.secondmemory.repository

import ru.secondmemory.model.Card
import ru.secondmemory.model.CardType
import ru.secondmemory.model.Cards
import ru.secondmemory.util.getEnumCardsTestData
import java.util.*

class InMemoryCardRepository : CardRepository {
    private val memoryData: EnumMap<CardType, Cards> = getEnumCardsTestData()

    override fun save(type: CardType, card: Card): Card? {
        memoryData[type]?.addCard(card.key, card)
        return get(type, card.key)
    }

    override fun delete(type: CardType, key: String): Boolean {
        memoryData[type]?.cards?.remove(key) ?: return false
        return true
    }

    override fun get(type: CardType, key: String): Card? {
        return memoryData[type]?.cards?.get(key) ?: return null
    }

    override fun getAllByType(type: CardType): List<Card> {
        return memoryData[type]!!.cards.values.toList()
    }
}