package ru.secondmemory.model

import java.util.concurrent.ConcurrentHashMap


class Cards(
        val cards: MutableMap<String, Card> = ConcurrentHashMap()) {

    fun addCard(key: String, value: String) {
        cards[key] = Card(value)
    }

    fun addCard(key: String, transcript: String, translation: String) {
        cards[key] = WordCard(transcript, translation)
    }

    fun addCard(vararg args: String) {
        cards[args[0]] = ListCard(args)
    }

    override fun toString(): String {
        var rezult = ""
        cards.keys.map { rezult = rezult + it + ": " + cards[it] + "\n" }
        return rezult
    }


//    fun ListSection(vararg items: Arrays?) {
//        this(Arrays.asList<String>(*items))
//    }
//    fun getCard(key: String): Card {
//        return cards.get(key)
//    }
//    fun addCard(key: String, value: Card) {
//        cards[key] = value
//    }
}