package com.example.routes

import com.example.data.model.Rabbit
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.request.*


private const val BASE_URL = "http://192.168.56.1:8080"
private val rabbits = mutableListOf<Rabbit>(
    Rabbit("1", "Sergey", "A cute brown rabbit", "$BASE_URL/rabbits/rabbit1.webp"),
    Rabbit("2", "Ivan", "Likes to eat apples", "$BASE_URL/rabbits/rabbit2.webp"),
    Rabbit("3", "Nikita", "Always hungry", "$BASE_URL/rabbits/rabbit3.webp"),
    Rabbit("4", "Vasy", "Likes to climb mountains", "$BASE_URL/rabbits/rabbit4.webp"),
    Rabbit("5", "Rustlan", "Very beauty", "$BASE_URL/rabbits/rabbit5.webp"),

    )
fun Route.randomRabbit() {
    get("/randomrabbit") {
        call.respond(
            HttpStatusCode.OK,
            rabbits.random()
        )
    }
}

fun Route.allRabbits() {
    get("/allrabbits") {
        if (rabbits.isNotEmpty()) {
            call.respond(rabbits)
        } else {
            call.respondText("No rabbits found", status = HttpStatusCode.NotFound)
        }
    }
    get("{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val rabbit =
            rabbits.find { it.id == id } ?: return@get call.respondText(
                "No rabbit with id $id",
                status = HttpStatusCode.NotFound
            )
        call.respond(rabbit)
    }
    post {
        val rabbit = call.receive<Rabbit>()
        rabbits.add(rabbit)
        call.respondText("Rabbit stored correctly", status = HttpStatusCode.Created)
    }
    delete("{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        if (rabbits.removeIf { it.id == id }) {
            call.respondText("Rabbit removed correctly", status = HttpStatusCode.Accepted)
        } else {
            call.respondText("Not Found", status = HttpStatusCode.NotFound)
        }
    }
}