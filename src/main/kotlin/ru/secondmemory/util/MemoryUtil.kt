package ru.secondmemory.util

import ru.secondmemory.model.Card
import java.util.concurrent.ConcurrentHashMap

fun main(args: Array<String>) {

    fun getMapTestCard(): MutableMap<String, MutableMap<String, Card>> {

        val groups: MutableMap<String, MutableMap<String, Card>> = ConcurrentHashMap()
        val cites: MutableMap<String, Card> = ConcurrentHashMap()
        val questions: MutableMap<String, Card> = ConcurrentHashMap()
        val enumeration: MutableMap<String, Card> = ConcurrentHashMap()
        val verse: MutableMap<String, Card> = ConcurrentHashMap()
        val words: MutableMap<String, Card> = ConcurrentHashMap()

        words["resolve [riˈzɑlv]"] = Card("решить")
        words["deprecated [dˈeprəkɛɪːtɪd]"] = Card("устаревший")

        questions["Кто изобретён паровой котёл"] = Card("Д. Папином")
        questions["Когда изобретён паровой котёл"] = Card("1680 г.")

        enumeration["Крупнейшие города России"] = Card("Москва",
                listOf("Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань .."))

        cites["Москва"] = Card(" 12678")
        cites["Санкт-Петербург"] = Card("5398")
        cites["Новосибирск"] = Card(" 1626")
        cites["Екатеринбург"] = Card(" 1494")
        cites["Казань"] = Card(" 1257")

        verse["Cергей Есенин. Заметался пожар голубой"] = Card("Заметался пожар голубой",
                listOf("Позабылись родимые дали.", "В первый раз я запел про любовь",
                        "В первый раз отрекаюсь скандалить. ..."))

        groups["Слова"] = words
        groups["Вопрос - Ответ"] = questions
        groups["Перечисления"] = enumeration
        groups["Население тыс. человек"] = cites
        groups["Стихи"] = verse

        return groups
    }

    val mapCard: MutableMap<String, MutableMap<String, Card>> = getMapTestCard()
    mapCard.keys.toString()

    println(mapCard.keys.toString())
}
