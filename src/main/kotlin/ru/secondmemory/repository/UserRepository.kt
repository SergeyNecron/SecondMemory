package ru.secondmemory.repository

import ru.secondmemory.model.User

interface UserRepository {
    // null if not found, when updated
    fun save(user: User): User

    // false if not found
    fun delete(id: Int): Boolean

    // null if not found
    fun get(id: Int): User?

    // null if not found
    fun getByEmail(email: String): User?

    fun getAll(): List<User>
}