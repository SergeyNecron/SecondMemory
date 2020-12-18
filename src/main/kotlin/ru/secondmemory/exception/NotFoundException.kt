package ru.secondmemory.exception

import ru.secondmemory.model.CardType

class NotFoundTypeException(type: CardType) : CardServiceException("Unknown (type='$type')")

class NotFoundKeyException(type: CardType, key: String) : CardServiceException("Unknown (type='$type' key='$key')")