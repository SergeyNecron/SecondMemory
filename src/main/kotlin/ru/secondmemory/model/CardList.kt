package ru.secondmemory.model

/**
 * Sergey Muratkin
 * 2020-12-03
 */

data class CardList(
        var extra: List<String>
) : Card(extra[0]) {

    constructor(extra: Array<out String>) : this(extra.drop(1))

    override fun toString(): String {
        val filtered = "[]="
        return " ${extra.toString().filterNot { filtered.indexOf(it) > -1 }} "
    }
}