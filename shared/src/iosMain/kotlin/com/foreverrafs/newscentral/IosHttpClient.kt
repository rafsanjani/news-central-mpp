package com.foreverrafs.newscentral

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.LogLevel.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal fun IosHttpClient(withLog: Boolean) = HttpClient(Darwin) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
    if (withLog) install(Logging) {
        level = HEADERS
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(tag = "IosHttpClient", message = message)
            }
        }
    }
}