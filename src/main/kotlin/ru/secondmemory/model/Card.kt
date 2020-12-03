package ru.secondmemory.model

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

/**
 * Sergey Muratkin
 * 2020-12-01
 */

open class Card(
        var value: String = "",
        val memoryTime: LocalDateTime,
        val rememberingTime: List<LocalDateTime> = ArrayList()
) : BaseEntity() {

    constructor(value: String) : this(
            value,
            LocalDateTime.now(),
            ArrayList()
    )

    override fun toString(): String {
        return value
    }
}