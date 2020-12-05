package ru.secondmemory.dto

import ru.secondmemory.model.Card

/**
 * @author Sergey Muratkin
 * Date: 2020-12-01
 */

data class CardDto(
        val key: String,
        val value: Card,
)