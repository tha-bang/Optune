package com.example.optune.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import com.example.optune.data.model.User
import com.example.optune.dao.UserDao

fun Route.userRoutes() {
    val dao = UserDao()

    route("/users") {
        get {
            call.respond(dao.getAll())
        }

        post {
            val user = call.receive<User>()
            val newUser = dao.insert(user.name)
            call.respond(newUser)
        }
    }
}
