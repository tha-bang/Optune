package com.example.optune

import com.example.optune.database.DatabaseFactory
import com.example.optune.routes.offerRoutes
import com.example.optune.routes.userRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing

fun Application.module() {
    configureSerialization()
    configureRouting()
    DatabaseFactory.init()
}

fun Application.configureRouting() {
    routing {
        userRoutes()
        offerRoutes()
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
