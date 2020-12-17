package ru.secondmemory.model

/**
 *
 * @author Sergey Muratkin
 * Date: 2020-12-02
 */

enum class CardType(val title: String) {
    WORDS("Слова"),
    QUESTIONS("Вопрос - ответ"),
    ENUMERATION("Перечисления"),
    CITES("Население города (тыщ. человек)"),
    VERSE("Стихи"),
    CARD("По умолчанию");
}