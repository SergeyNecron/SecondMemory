package ru.secondmemory.model

import java.util.*

data class User(
        val email: String,
        var password: String,
        var enabled: Boolean,
        val roles: Set<Role>,
        val cardFile: EnumMap<CardType, Cards>
) : BaseEntity()
