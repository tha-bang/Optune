package com.example.optune.database

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import kotlinx.coroutines.Dispatchers
import com.example.optune.data.model.Users

object DatabaseFactory {
    fun init() {
        val url = "jdbc:postgresql://localhost:5432/postgres"
        val driver = "org.postgresql.Driver"
        val user = "postgres"
        val password = "Optune"

        Database.connect(url, driver, user, password)

        transaction {
            SchemaUtils.create(Users) // Add other tables here
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
