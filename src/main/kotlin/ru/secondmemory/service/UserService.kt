package ru.secondmemory.service

import ru.secondmemory.model.User
import ru.secondmemory.repository.UserRepository
import ru.secondmemory.util.checkNotFound
import ru.secondmemory.util.checkNotFoundWithId


class UserService() {

    private val repository: UserRepository = TODO()

    fun create(user: User): User {
        return repository.save(user)
    }

    fun update(user: User) {
        checkNotFoundWithId(repository.save(user), user.id)
    }

    fun delete(id: Int) {
        checkNotFoundWithId(repository.delete(id), id)
    }

    operator fun get(id: Int): User {
        return checkNotFoundWithId(repository.get(id), id)
    }

    fun getByEmail(email: String): User {
        return checkNotFound(repository.getByEmail(email), "email=$email")
    }

    fun getAll(): List<User> {
        return repository.getAll()
    }

}