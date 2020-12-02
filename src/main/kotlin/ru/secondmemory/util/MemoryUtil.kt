package ru.secondmemory.util

import ru.secondmemory.model.Card
import ru.secondmemory.model.CardType
import ru.secondmemory.model.CardType.*
import ru.secondmemory.model.Cards

fun main(args: Array<String>) {

    fun getMapTestCard(): MutableMap<CardType, MutableMap<String, Card>> {
        val cards = Cards()

        cards.words["resolve [riˈzɑlv]"] = Card("решить")
        cards.words["deprecated [dˈeprəkɛɪːtɪd]"] = Card("устаревший")

        cards.questions["Кто изобретён паровой котёл"] = Card("Д. Папином")
        cards.questions["Когда изобретён паровой котёл"] = Card("1680 г.")

        cards.enumeration["Крупнейшие города России"] = Card("Москва",
                listOf("Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань .."))

        cards.cites["Москва"] = Card("12678")
        cards.cites["Санкт-Петербург"] = Card("5398")
        cards.cites["Новосибирск"] = Card("1626")
        cards.cites["Екатеринбург"] = Card("1494")
        cards.cites["Казань"] = Card("1257")

        cards.verse["Cергей Есенин. Заметался пожар голубой"] = Card("Заметался пожар голубой",
                listOf("Позабылись родимые дали.", "В первый раз я запел про любовь",
                        "В первый раз отрекаюсь скандалить. ..."))

        return cards.init()
    }

    val mapCard: MutableMap<CardType, MutableMap<String, Card>> = getMapTestCard()

    println(WORDS.title)
    println(mapCard.getValue(WORDS).toString())
    println(CITES.title)
    println(mapCard.getValue(CITES).toString())
    println(ENUMERATION.title)
    println(mapCard.getValue(ENUMERATION).toString())
    println(QUESTIONS.title)
    println(mapCard.getValue(QUESTIONS).toString())
    println(VERSE.title)
    println(mapCard.getValue(VERSE).toString())
//
}
