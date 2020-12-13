package ru.secondmemory.model

/**
 * Sergey Muratkin
 * 2020-12-03
 */

data class CardList(
        var _key: String,
        var _value: String,
        val _type: CardType,
        var extra: List<String>
) : Card(_key, _value, _type) {

    override fun toString(): String {
        val filtered = "[]="
        return "${extra.toString().filterNot { filtered.indexOf(it) > -1 }.drop(0)} "
    }
}