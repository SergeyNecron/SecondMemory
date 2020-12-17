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

class CardServiceImpl : CardService {

    private val log = LoggerFactory.getLogger(CardService::class.java)

    private val repository: CardRepository = InMemoryCardRepository()

    override fun updateOrSaveCard(type: CardType, card: Card): CardDto {
        return CardDto(repository.save(type, card) ?: return CardDto("", "")
        )
    }

    override fun deleteCard(type: CardType, key: String): Boolean {
        return repository.delete(type, key)
    }

    override fun getCardDto(type: CardType, key: String): CardDto {
        return CardDto(repository.get(type, key) ?: return CardDto()
        )
    }

    override fun getCardListDto(type: CardType, key: String): CardListDto {
        val card: Card? = repository.get(type, key)
        if (card != null && card is CardList) return CardListDto(card as CardList)
        return CardListDto()
    }

    override fun getAllCardsDtoByType(type: CardType): List<CardDto> {
        return repository.getAllByType(type)
                .map { CardDto(it) }
    }

    override fun getAllCardsWordDtoByType(type: CardType): List<CardWordDto> {
        return repository.getAllByType(type)
                .map { CardWordDto(it as CardWord) }
    }

    override fun getAllCardsListDtoByType(type: CardType): List<CardListDto> {
        return repository.getAllByType(type)
                .map { CardListDto(it as CardList) }
    }
}