package ru.secondmemory.model

/**
 * Sergey Muratkin
 * 2020-12-03
 */

data class ListCard(
        var extra: List<String>
) : Card(extra[0]) {

    constructor(extra: Array<out String>) : this(extra.drop(1))

    override fun toString(): String {
        val filtered = "[]="
        val value = " ${extra.toString().filterNot { filtered.indexOf(it) > -1 }} "
        val valueSize = 50
        if (value.length > valueSize)
            return value.substring(0, valueSize) + "..."
        else
            return value
    }
}