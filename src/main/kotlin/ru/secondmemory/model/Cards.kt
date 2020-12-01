package ru.secondmemory.model

import java.util.concurrent.ConcurrentHashMap

/**
 *
 * @author Sergey Muratkin
 * Date: 2020-12-01
 */

data class Cards(
        val groups: MutableMap<String, MutableMap<String, Card>> = ConcurrentHashMap(),
        val cites: MutableMap<String, Card> = ConcurrentHashMap(),
        val questions: MutableMap<String, Card> = ConcurrentHashMap(),
        val enumeration: MutableMap<String, Card> = ConcurrentHashMap(),
        val verse: MutableMap<String, Card> = ConcurrentHashMap(),
        val words: MutableMap<String, Card> = ConcurrentHashMap()
)