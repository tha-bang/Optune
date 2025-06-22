package com.example.optune.routes

import com.example.optune.dao.OfferDao
import com.example.optune.data.model.Offer
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.offerRoutes() {
    val dao = OfferDao()

    route("/offers") {
        get {
            call.respond(dao.getAll())
        }

        get("{id}") {
            val id = call.parameters["id"]
            val offer = id?.let { dao.getById(it) }
            if (offer != null) {
                call.respond(offer)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post {
            val offer = call.receive<Offer>()
            val newOffer = dao.insert(offer)
            call.respond(HttpStatusCode.Created, newOffer)
        }
    }
}
