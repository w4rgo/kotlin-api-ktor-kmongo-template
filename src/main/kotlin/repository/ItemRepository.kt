
package repository

import context.Context
import model.Item
import org.litote.kmongo.*

class ItemRepository {
    companion object{
        val collection = Context.database.getCollection<Item>()
    }

    fun retrieveAllItems() : List<Item> {
        return collection.find().toList()
    }
}
