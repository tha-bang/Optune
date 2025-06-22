package com.example.optune.dao

import com.example.optune.data.model.User
import com.example.optune.data.model.Users
import com.example.optune.database.DatabaseFactory.dbQuery
import com.example.optune.utils.jsonToList
import com.example.optune.utils.listToJson
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

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
    // Get user by ID
    suspend fun getById(id: String): User? = dbQuery {
        Users.select { Users.userId eq id }
            .mapNotNull {
                User(
                    userId = it[Users.userId],
                    name = it[Users.name],
                    email = it[Users.email],
                    signUpMethod = it[Users.signUpMethod],
                    education = it[Users.education],
                    cv = it[Users.cv],
                    skills = it[Users.skills]?.let(::jsonToList) ?: emptyList(),
                    interests = it[Users.interests]?.let(::jsonToList) ?: emptyList()
                )
            }.singleOrNull()
    }

    // Update user
    suspend fun update(id: String, updatedUser: User): Boolean = dbQuery {
        Users.update({ Users.userId eq id }) {
            it[name] = updatedUser.name
            it[email] = updatedUser.email
            it[signUpMethod] = updatedUser.signUpMethod
            it[education] = updatedUser.education
            it[cv] = updatedUser.cv
            it[skills] = listToJson(updatedUser.skills)
            it[interests] = listToJson(updatedUser.interests)
        } > 0
    }

    // Delete user
    suspend fun delete(id: String): Boolean = dbQuery {
        Users.deleteWhere { Users.userId eq id } > 0
    }

}
