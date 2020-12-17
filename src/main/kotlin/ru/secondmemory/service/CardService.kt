package ru.secondmemory.service

import ru.secondmemory.dto.CardDto
import ru.secondmemory.dto.CardListDto
import ru.secondmemory.dto.CardWordDto
import ru.secondmemory.model.Card
import ru.secondmemory.model.CardType

interface CardService {

    fun updateOrSaveCard(type: CardType, card: Card): CardDto

    fun deleteCard(type: CardType, key: String): Boolean

    fun getCardDto(type: CardType, key: String): CardDto

    fun getCardListDto(type: CardType, key: String): CardListDto

    fun getAllCardsDtoByType(type: CardType): List<CardDto>

    fun getAllCardsWordDtoByType(type: CardType): List<CardWordDto>

    fun getAllCardsListDtoByType(type: CardType): List<CardListDto>
}