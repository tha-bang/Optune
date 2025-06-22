package com.example.optune.dao

import com.example.optune.data.model.Offer
import com.example.optune.data.model.Offers
import com.example.optune.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.UUID

class OfferDao {
    suspend fun getAll(): List<Offer> = dbQuery {
        Offers.selectAll().map {
            Offer(
                id = it[Offers.id],
                title = it[Offers.title],
                description = it[Offers.description],
                businessName = it[Offers.businessName]
            )
        }
    }
    suspend fun insert(offer: Offer): Offer = dbQuery {
        val newId = offer.id ?: UUID.randomUUID().toString()
        Offers.insert {
            it[id] = newId
            it[title] = offer.title
            it[description] = offer.description
            it[businessName] = offer.businessName
        }
        offer.copy(id = newId)
    }
    suspend fun getById(id: String): Offer? = dbQuery {
        Offers.select { Offers.id eq id }.mapNotNull {
            Offer(
                id = it[Offers.id],
                title = it[Offers.title],
                description = it[Offers.description],
                businessName = it[Offers.businessName]
            )
        }.singleOrNull()
    }
}
