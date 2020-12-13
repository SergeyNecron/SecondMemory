package ru.secondmemory.model

import java.time.LocalDateTime
import kotlin.collections.ArrayList

/**
 * Sergey Muratkin
 * 2020-12-01
 */

open class Card(
        var key: String,
        var value: String,
        val type: CardType
) : BaseEntity() {

    val memoryTime: LocalDateTime = LocalDateTime.now()
    val rememberingTime: List<LocalDateTime> = ArrayList()

    override fun toString(): String {
        return "$value"
    }
}