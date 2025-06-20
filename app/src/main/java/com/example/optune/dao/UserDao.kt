package com.example.optune.dao

import com.example.optune.data.model.Users
import com.example.optune.database.DatabaseFactory.dbQuery
import com.example.optune.data.model.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserDao {
    suspend fun getAll(): List<User> = dbQuery {
        Users.selectAll().map {
            User(
                userId = it[Users.userId],
                name = it[Users.name],
                email = it[Users.email],
                signUpMethod = it[Users.signUpMethod],
                education = it[Users.education],
                cv = it[Users.cv]
                // skills and interests not handled yet
            )
        }
    }

    suspend fun insert(user: User): User = dbQuery {
        Users.insert {
            it[userId] = user.userId ?: java.util.UUID.randomUUID().toString()
            it[name] = user.name
            it[email] = user.email
            it[signUpMethod] = user.signUpMethod
            it[education] = user.education
            it[cv] = user.cv
        }

        user.copy(userId = user.userId ?: "generated") // return updated User object
    }
}
