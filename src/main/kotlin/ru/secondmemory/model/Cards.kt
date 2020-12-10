package ru.secondmemory.model

import java.util.concurrent.ConcurrentHashMap


class Cards(val cards: MutableMap<String, Card> = ConcurrentHashMap()) {

    fun addCard(key: String, value: String) {
        cards[key] = Card(value)
    }

    fun addCard(key: String, transcript: String, translation: String) {
        cards[key] = CardWord(transcript, translation)
    }

    fun addCard(vararg args: String) {
        cards[args[0]] = CardList(args)
    }

    override fun toString(): String {
        var rezult = ""
        cards.keys.map { rezult = rezult + it + ": " + cards[it] + "\n" }
        return rezult
    }

}