package ru.secondmemory.model

import java.util.concurrent.ConcurrentHashMap


class Cards() {
    val cards: MutableMap<String, Card> = ConcurrentHashMap()

    constructor(listCard: MutableList<Card>) : this() {
        listCard.map { cards[it.key] = it }
    }

    fun addCard(key: String, card: Card) {
        cards[key] = card
    }

    override fun toString(): String {
        var rezult = ""
        cards.keys.map { rezult = rezult + it + ": " + cards[it] + "\n" }
        return rezult
    }

}