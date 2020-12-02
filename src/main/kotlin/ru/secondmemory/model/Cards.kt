package ru.secondmemory.model

import java.util.concurrent.ConcurrentHashMap
import ru.secondmemory.model.CardType.*

/**
 *
 * @author Sergey Muratkin
 * Date: 2020-12-01
 */

data class Cards(
        val groups: MutableMap<CardType, MutableMap<String, Card>> = ConcurrentHashMap(),
        val cites: MutableMap<String, Card> = ConcurrentHashMap(),
        val questions: MutableMap<String, Card> = ConcurrentHashMap(),
        val enumeration: MutableMap<String, Card> = ConcurrentHashMap(),
        val verse: MutableMap<String, Card> = ConcurrentHashMap(),
        val words: MutableMap<String, Card> = ConcurrentHashMap()
){
     fun init ():MutableMap<CardType, MutableMap<String, Card>> {
        groups[WORDS] = words
        groups[QUESTIONS] = questions
        groups[ENUMERATION] = enumeration
        groups[CITES] = cites
        groups[VERSE] = verse
        return groups
    }
}