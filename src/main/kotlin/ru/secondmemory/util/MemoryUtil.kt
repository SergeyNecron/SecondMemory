package ru.secondmemory.util

import ru.secondmemory.model.*
import ru.secondmemory.model.CardType.*
import java.util.*

fun main(args: Array<String>) {
    printCardFile(fillTestDataCardFile())
}

fun fillTestDataCardFile(): EnumMap<CardType, Cards> {
    val cardFile: EnumMap<CardType, Cards> = EnumMap(CardType::class.java)
    val words = Cards()
    val cites = Cards()
    val questions = Cards()
    val enumeration = Cards()
    val verse = Cards()

    words.addCard("resolve", "[riˈzɑlv]", "решить")
    words.addCard("deprecated", "[dˈeprəkɛɪːtɪd]", "устаревший")

    questions.addCard("Кто изобретён паровой котёл ?", "Д. Папином")
    questions.addCard("Когда изобретён паровой котёл ?", "1680 г.")

    enumeration.addCard("Крупнейшие города России", "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань ..")

    cites.addCard("Москва", "12678")
    cites.addCard("Санкт-Петербург", "5398")
    cites.addCard("Новосибирск", "1626")
    cites.addCard("Екатеринбург", "1494")
    cites.addCard("Казань", "1257")

    verse.addCard("Cергей Есенин. Заметался пожар голубой",
            "Заметался пожар голубой",
            "Позабылись родимые дали",
            "В первый раз я запел про любовь",
            "В первый раз отрекаюсь скандалить. ...")

    cardFile[WORDS] = words
    cardFile[CITES] = cites
    cardFile[ENUMERATION] = enumeration
    cardFile[QUESTIONS] = questions
    cardFile[VERSE] = verse

    return cardFile
}

fun printCardFile(mapCard: EnumMap<CardType, Cards>) {
//    val cards = mapCard[WORDS]
//    cards!!.addCard("vdsvds", "vdsvsdv")
//    mapCard[WORDS] = cards
    mapCard[WORDS]!!.addCard("fsdfsd", "fsdf454sd")
    CardType.values().map {
        println(it.title)
        println(mapCard.getValue(it).toString())
    }
}
