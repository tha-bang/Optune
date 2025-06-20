package com.example.optune.data.model

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val userId = varchar("userId", 50) //
    val name = varchar("name", 100).nullable()
    val email = varchar("email", 100).nullable()
    val signUpMethod = varchar("signUpMethod", 50).nullable()
    val education = varchar("education", 255).nullable()
    val cv = text("cv").nullable()

    override val primaryKey = PrimaryKey(userId)

}
