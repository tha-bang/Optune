package com.example.optune.dao

import com.example.optune.data.model.Users
import com.example.optune.database.DatabaseFactory.dbQuery
import com.example.optune.data.model.User
import com.example.optune.utils.*
import org.jetbrains.exposed.sql.*

class UserDao {

    suspend fun getAll(): List<User> = dbQuery {
        Users.selectAll().map {
            User(
                userId = it[Users.userId],
                name = it[Users.name],
                email = it[Users.email],
                signUpMethod = it[Users.signUpMethod],
                education = it[Users.education],
                cv = it[Users.cv],
                skills = it[Users.skills]?.let { json -> jsonToList(json) } ?: emptyList(),
                interests = it[Users.interests]?.let { json -> jsonToList(json) } ?: emptyList()
            )
        }
    }

    suspend fun insert(user: User): User = dbQuery {
        val idToUse = user.userId ?: java.util.UUID.randomUUID().toString()

        Users.insert {
            it[Users.userId] = idToUse
            it[Users.name] = user.name
            it[Users.email] = user.email
            it[Users.signUpMethod] = user.signUpMethod
            it[Users.education] = user.education
            it[Users.cv] = user.cv
            it[Users.skills] = user.skills.takeIf { it.isNotEmpty() }?.let { listToJson(it) }
            it[Users.interests] = user.interests.takeIf { it.isNotEmpty() }?.let { listToJson(it) }
        }

        user.copy(userId = idToUse)
    }
}
