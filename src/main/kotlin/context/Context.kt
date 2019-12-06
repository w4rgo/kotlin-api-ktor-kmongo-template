package context

import com.mongodb.client.MongoDatabase
import controller.ItemListController
import model.Item
import org.litote.kmongo.*
import spark.kotlin.*

class Context {

    companion object {
        private val client = KMongo.createClient("localhost", 27017)
        val database: MongoDatabase = client.getDatabase("eft-items")

        @JvmStatic
        fun main(args: Array<String>) {
            prepareDummyData()
            val itemListController = ItemListController()
            val http: Http = ignite()
            http.get("/retrieveItems") { itemListController.retrieveItemsList() }
        }

        private fun prepareDummyData() {
            database.getCollection<Item>().insertOne(Item("bolts"))
            database.getCollection<Item>().insertOne(Item("screw nuts"))
        }
    }

}

