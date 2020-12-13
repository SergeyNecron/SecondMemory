package ru.secondmemory.model

/**
 * @author Sergey Muratkin
 * Date: 2020-12-01
 */

data class CardWord(
        var _key: String,
        var transcript: String,
        var translation: String,
) : Card(_key, translation, CardType.WORDS) {

    override fun toString(): String {
        return "$transcript $translation"
    }
}