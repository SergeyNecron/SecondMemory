package ru.secondmemory.model

/**
 * @author Sergey Muratkin
 * Date: 2020-12-01
 */

data class CardWord(
        var transcript: String = "",
        var translation: String = "",
) : Card(transcript) {

    override fun toString(): String {
        return "$transcript $translation"
    }
}