package ru.secondmemory.model

import java.time.LocalDateTime


abstract class BaseEntity {

    val id: Long = -1
    val createDate: LocalDateTime = LocalDateTime.now()
    var modifyDate: LocalDateTime = LocalDateTime.now()
}