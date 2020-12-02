package ru.secondmemory.model

import java.time.LocalDateTime
import java.util.*

/**
 *
 * @author Sergey Muratkin
 * Date: 2020-12-01
 */

data class Card(
        var title: String = "",
        var extra: List<String>
) : BaseEntity() {

    constructor(title: String) : this(
            title,
            ArrayList()
    ) {
        this.modifyDate = LocalDateTime.now()
    }

    override fun toString(): String {
        return "'$title'"
    }
}