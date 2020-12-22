package ru.secondmemory.service

import ru.secondmemory.dto.CardDto
import ru.secondmemory.dto.CardListDto
import ru.secondmemory.dto.CardWordDto
import ru.secondmemory.model.Card
import ru.secondmemory.model.CardType

interface CardService {

    fun save(type: CardType, card: Card): CardDto

    fun delete(type: CardType, key: String): Boolean

    fun get(type: CardType, key: String): CardDto

    fun getList(type: CardType, key: String): CardListDto

    fun getAllByType(type: CardType): List<CardDto>

    fun getAllWordByType(type: CardType): List<CardWordDto>

    fun getAllListByType(type: CardType): List<CardListDto>
}