package com.example.optune.data.model

import org.jetbrains.exposed.sql.Table

object Notifications : Table() {
    val id = varchar("id", 50)
    val userId = varchar("userId", 50).references(Users.userId)
    val message = text("message")
    val read = bool("read").default(false)
    val timestamp = long("timestamp")

    override val primaryKey = PrimaryKey(id)
}
