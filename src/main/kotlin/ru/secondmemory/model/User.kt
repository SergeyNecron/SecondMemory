package ru.secondmemory.model

import java.util.concurrent.ConcurrentMap

data class User(
        val email: String,
        var password: String,
        var enabled: Boolean,
        val roles: Set<Role>,
        val cardFile: ConcurrentMap<CardType, MutableMap<String, Card>>
) : BaseEntity()
