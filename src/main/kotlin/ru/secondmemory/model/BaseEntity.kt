package ru.secondmemory.model

import java.time.LocalDateTime


abstract class BaseEntity {

    var id: Int = -1
    val createDate: LocalDateTime = LocalDateTime.now()
    var modifyDate: LocalDateTime = LocalDateTime.now()

    open fun isNew(): Boolean {
        return id == -1
    }
}