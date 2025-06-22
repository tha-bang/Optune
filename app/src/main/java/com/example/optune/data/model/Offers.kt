package com.example.optune.data.model

import org.jetbrains.exposed.sql.Table

object Offers : Table() {
    val id = varchar("id", 50)
    val title = varchar("title", 255)
    val description = text("description")
    val businessName = varchar("businessName", 100)

    override val primaryKey = PrimaryKey(id)
}
