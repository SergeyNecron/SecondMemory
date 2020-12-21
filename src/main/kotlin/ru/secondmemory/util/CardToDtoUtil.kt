package ru.secondmemory.util

import ru.secondmemory.dto.CardDto
import ru.secondmemory.dto.CardListDto
import ru.secondmemory.dto.CardWordDto
import ru.secondmemory.model.Card
import ru.secondmemory.model.CardList
import ru.secondmemory.model.CardWord

fun cardToCardDto(card: Card): CardDto {
    return CardDto(
        card.key,
        card.value
    )
}

fun cardListToCardListDto(card: CardList, short: Boolean): CardListDto {
    return CardListDto(
        card.key,
        card.value,
        card.type,
        extraToString(card.extra, short)
    )
}

fun cardWordToCardWordDto(card: CardWord): CardWordDto {
    return CardWordDto(
        card.key,
        card.transcript,
        card.translation,
        card.type
    )
}

fun extraToString(extra: List<String>, short: Boolean): String {
    val filtered = "[]"
    val maxCountStringExtra = 2
    val toIndex = if (extra.size < maxCountStringExtra) extra.size else maxCountStringExtra
    return if (short) {
        "${extra.subList(0, toIndex).toString().filterNot { filtered.indexOf(it) > -1 }}  .. "
    } else extra.toString().filterNot { filtered.indexOf(it) > -1 }
}