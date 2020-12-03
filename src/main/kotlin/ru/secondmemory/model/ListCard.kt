package ru.secondmemory.model

/**
 * Sergey Muratkin
 * 2020-12-03
 */

data class ListCard(
        var title: String = "",
        var extra: List<String>
) : Card(title) {

    override fun toString(): String {
        val filtered = "[]="
        return "$title, ${extra.toString().filterNot { filtered.indexOf(it) > -1 }} "
    }
}