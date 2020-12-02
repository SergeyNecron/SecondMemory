package ru.secondmemory.model

import java.util.concurrent.ConcurrentMap

data class User(
        val email: String,
        var password: String = "",
        var enabled: Boolean = true,
        val roles: Set<Role> = HashSet(),
        val cardFile: ConcurrentMap<String, Cards>

) : BaseEntity()
