package com.example.optune.data.model

import org.jetbrains.exposed.sql.Table

object Applications : Table() {
    val id = varchar("id", 50)
    val userId = varchar("userId", 50).references(Users.userId)
    val offerId = varchar("offerId", 50).references(Offers.id)
    val status = varchar("status", 20) // "Pending", "Accepted", "Rejected"
    val timestamp = long("timestamp")

    override val primaryKey = PrimaryKey(id)
}
