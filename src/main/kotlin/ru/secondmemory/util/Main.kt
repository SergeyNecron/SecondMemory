package ru.secondmemory.util

import ru.secondmemory.model.CardType
import ru.secondmemory.model.Cards
import ru.secondmemory.service.CardService
import ru.secondmemory.service.CardServiceImpl
import java.util.*

fun main(args: Array<String>) {
    val enumMapCard: EnumMap<CardType, Cards> = getEnumCardsTestData()
    printCardFile(enumMapCard)
    printCards(CardType.WORDS)
}

private fun printCardFile(mapCard: EnumMap<CardType, Cards>) {
    CardType.values().map {
        println(it.title)
        println(mapCard.getValue(it).toString())
    }
}

private var service: CardService = CardServiceImpl()

private fun printCards(type: CardType) {
    service.getAllCardsWordDtoByType(type)
            .map { println("${it.key} ${it.transcript} ${it.translation}") }
}

