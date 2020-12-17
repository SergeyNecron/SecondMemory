package ru.secondmemory.dto

import ru.secondmemory.model.CardList
import ru.secondmemory.model.CardType

data class CardListDto(
        val _key: String,
        val _value: String,
        val type: CardType,
        val extra: List<String>
) : CardDto(key = _key, value = _value) {

    constructor() : this(
            "",
            "",
            CardType.CARD,
            listOf()
    )

    constructor(card: CardList) : this(
            card.key,
            card.value,
            card.type,
            card.extra
    )
}