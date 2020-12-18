package ru.secondmemory.dto

import ru.secondmemory.model.CardType

data class CardWordDto(
        val key: String,
        val transcript: String,
        val translation: String,
        val type: CardType)