package com.example.plugins

import com.example.routes.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {


    routing {
        randomRabbit()
        allRabbits()
        // Static plugin. Try to access `/static/index.html`
        resources("static")
    }
}
