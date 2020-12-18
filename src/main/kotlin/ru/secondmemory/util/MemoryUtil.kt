package ru.secondmemory.util

import ru.secondmemory.dto.CardDto
import ru.secondmemory.dto.CardListDto
import ru.secondmemory.dto.CardWordDto
import ru.secondmemory.model.*
import ru.secondmemory.model.CardType.*
import java.util.*

fun getEnumCardsTestData(): EnumMap<CardType, Cards> {
    val listCard = fillTestDataCardFile()
    val enumListCard: EnumMap<CardType, MutableList<Card>> = toEnumList(listCard)
    return toEnumMap(enumListCard)
}

fun fillTestDataCardFile(): MutableList<Card> {
    val cardFile: MutableList<Card> = ArrayList()
    cardFile.add(CardWord("resolve", "[riˈzɑlv]", "решить"))
    cardFile.add(CardWord("deprecated", "[dˈeprəkɛɪːtɪd]", "устаревший"))

    cardFile.add(Card("Кто изобретён паровой котёл ?", "Д. Папином", QUESTIONS))
    cardFile.add(Card("Когда изобретён паровой котёл ?", "1680 г.", QUESTIONS))

    cardFile.add(CardList("Крупнейшие города России", "Москва", ENUMERATION,
            listOf("Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань ..")))

    cardFile.add(Card("Москва", "12678", CITES))
    cardFile.add(Card("Санкт-Петербург", "5398", CITES))
    cardFile.add(Card("Новосибирск", "1626", CITES))
    cardFile.add(Card("Екатеринбург", "1494", CITES))
    cardFile.add(Card("Казань", "1257", CITES))

    cardFile.add(CardList("Cергей Есенин", "Заметался пожар голубой", VERSE,
            listOf("Заметался пожар голубой",
                    "Позабылись родимые дали",
                    "В первый раз я запел про любовь",
                    "В первый раз отрекаюсь скандалить.")))
    return cardFile
}

fun toEnumList(listCard: MutableList<Card>): EnumMap<CardType, MutableList<Card>> {
    val enumListCard: EnumMap<CardType, MutableList<Card>> = EnumMap(CardType::class.java)
    CardType.values().map {
        enumListCard[it] = listCard
                .filter { card -> card.type == it }
                .toMutableList()
    }
    return enumListCard
}

fun toEnumMap(enumListCard: EnumMap<CardType, MutableList<Card>>): EnumMap<CardType, Cards> {
    val enumMapCard: EnumMap<CardType, Cards> = EnumMap(CardType::class.java)
    enumListCard
            .keys
            .map { enumMapCard[it] = Cards(enumListCard[it]!!) }
    return enumMapCard
}

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

fun extraToString(extra: List<String>, short: Boolean): String {
    val filtered = "[]"
    val maxCountStringExtra = 2
    val toIndex = if (extra.size < maxCountStringExtra) extra.size else maxCountStringExtra
    return if (short) {
        "${extra.subList(0, toIndex).toString().filterNot { filtered.indexOf(it) > -1 }}  .. "
    } else extra.toString().filterNot { filtered.indexOf(it) > -1 }
}

fun cardWordToCardWordDto(card: CardWord): CardWordDto {
    return CardWordDto(
            card.key,
            card.transcript,
            card.translation,
            card.type
    )
}
