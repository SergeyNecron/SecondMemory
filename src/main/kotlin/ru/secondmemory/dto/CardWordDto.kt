package ru.secondmemory.dto

import ru.secondmemory.model.CardType
import ru.secondmemory.model.CardWord

data class CardWordDto(
        val _key: String,
        val transcript: String,
        val translation: String,
        val type: CardType
) : CardDto(key = _key, value = translation) {

    constructor(card: CardWord) : this(
            card.key,
            card.transcript,
            card.translation,
            card.type
    )
}