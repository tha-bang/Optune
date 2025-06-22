package com.example.optune.routes

import com.example.optune.dao.UserDao
import com.example.optune.data.model.User
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route


fun Route.userRoutes() {
    val dao = UserDao()

    route("/users") {
        get {
            call.respond(dao.getAll())
        }

        post {
            val user = call.receive<User>()
            val newUser = dao.insert(user)
            call.respond(newUser)
        }
    }
}
