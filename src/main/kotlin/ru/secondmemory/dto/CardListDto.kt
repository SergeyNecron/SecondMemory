package ru.secondmemory.dto

import ru.secondmemory.model.CardType

data class CardListDto(
        val key: String,
        val value: String,
        val type: CardType,
        val extra: String)