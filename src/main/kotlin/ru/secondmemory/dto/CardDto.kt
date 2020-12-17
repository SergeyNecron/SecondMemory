package ru.secondmemory.dto

import ru.secondmemory.model.Card

/**
 * @author Sergey Muratkin
 * Date: 2020-12-01
 */

open class CardDto(
        val key: String,
        val value: String
) {
    constructor() : this("", "")

    constructor(card: Card) : this(
            card.key,
            card.value
    )
}