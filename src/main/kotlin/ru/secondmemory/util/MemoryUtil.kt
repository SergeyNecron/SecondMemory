package ru.secondmemory.util

import ru.secondmemory.model.*
import ru.secondmemory.model.CardType.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

fun main(args: Array<String>) {
    printCardFile(fillTestDataCardFile())
}

fun fillTestDataCardFile(): ConcurrentMap<CardType, MutableMap<String, Card>> {
    val cardFile: ConcurrentMap<CardType, MutableMap<String, Card>> = ConcurrentHashMap()
    val words: MutableMap<String, Card> = ConcurrentHashMap()
    val cites: MutableMap<String, Card> = ConcurrentHashMap()
    val questions: MutableMap<String, Card> = ConcurrentHashMap()
    val enumeration: MutableMap<String, Card> = ConcurrentHashMap()
    val verse: MutableMap<String, Card> = ConcurrentHashMap()

    words["resolve"] = WordCard("[riˈzɑlv]", "решить")
    words["deprecated"] = WordCard("[dˈeprəkɛɪːtɪd]", "устаревший")

    questions["Кто изобретён паровой котёл ?"] = Card("Д. Папином")
    questions["Когда изобретён паровой котёл ?"] = Card("1680 г.")

    enumeration["Крупнейшие города России"] = ListCard("Москва",
            listOf("Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань .."))

    cites["Москва"] = Card("12678")
    cites["Санкт-Петербург"] = Card("5398")
    cites["Новосибирск"] = Card("1626")
    cites["Екатеринбург"] = Card("1494")
    cites["Казань"] = Card("1257")

    verse["Cергей Есенин. Заметался пожар голубой"] = ListCard("Заметался пожар голубой",
            listOf("Позабылись родимые дали.", "В первый раз я запел про любовь",
                    "В первый раз отрекаюсь скандалить. ..."))

    cardFile[WORDS] = words
    cardFile[CITES] = cites
    cardFile[ENUMERATION] = enumeration
    cardFile[QUESTIONS] = questions
    cardFile[VERSE] = verse

    return cardFile
}

fun printCardFile(mapCard: MutableMap<CardType, MutableMap<String, Card>>) {
    CardType.values().map {
        println(it.title)
        println(mapCard.getValue(it).toString().replace("=", ": "))
    }
}
