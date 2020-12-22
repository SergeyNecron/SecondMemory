package ru.secondmemory.service

import org.slf4j.LoggerFactory
import ru.secondmemory.dto.CardDto
import ru.secondmemory.dto.CardListDto
import ru.secondmemory.dto.CardWordDto
import ru.secondmemory.model.Card
import ru.secondmemory.model.CardList
import ru.secondmemory.model.CardType
import ru.secondmemory.model.CardWord
import ru.secondmemory.repository.CardRepository
import ru.secondmemory.repository.InMemoryCardRepository
import ru.secondmemory.util.*

class CardServiceImpl : CardService {

    private val log = LoggerFactory.getLogger(CardService::class.java)

    private val repository: CardRepository = InMemoryCardRepository()

    override fun save(type: CardType, card: Card): CardDto {
        return cardToCardDto(
            checkNotFoundWithType(repository.save(type, card), type)
        )
    }

    override fun delete(type: CardType, key: String): Boolean {
        return repository.delete(type, key)
    }

    override fun get(type: CardType, key: String): CardDto {
        return cardToCardDto(getCard(type, key))
    }

    override fun getList(type: CardType, key: String): CardListDto {
        val card: Card = getCard(type, key)
        return cardListToCardListDto(card as CardList, false)
    }

    override fun getAllByType(type: CardType): List<CardDto> {
        return repository.getAllByType(type)
            .map { cardToCardDto(it) }
    }

    override fun getAllWordByType(type: CardType): List<CardWordDto> {
        return repository.getAllByType(type)
            .map { cardWordToCardWordDto(it as CardWord) }
    }

    override fun getAllListByType(type: CardType): List<CardListDto> {
        return repository.getAllByType(type)
            .map { cardListToCardListDto(it as CardList, true) }
    }

    private fun getCard(type: CardType, key: String): Card {
        return checkNotFoundWithKey(repository.get(type, key), type, key)
    }
}