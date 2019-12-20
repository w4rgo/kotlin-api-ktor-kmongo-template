package context

import com.mongodb.client.MongoDatabase
import controller.ItemListController
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import model.Item
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection


class Context {

    companion object {
        private val client = KMongo.createClient("localhost", 27017)
        val database: MongoDatabase = client.getDatabase("eft-items")

        @JvmStatic
        fun main(args: Array<String>) {
            prepareDummyData()
            val itemListController = ItemListController()

            embeddedServer(Netty, 8080) {
                routing {
                    get("/") {

                        call.respondText(itemListController.retrieveItemsList(), ContentType.Application.Json)
                    }
                }
            }.start(wait = true)
        }

        private fun prepareDummyData() {
            database.getCollection<Item>().insertOne(Item("bolts"))
            database.getCollection<Item>().insertOne(Item("screw nuts"))
        }
    }

}

