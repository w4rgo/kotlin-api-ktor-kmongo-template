package controller

import com.google.gson.Gson
import repository.ItemRepository

class ItemListController {
    companion object {
        val itemRepository : ItemRepository = ItemRepository()
    }

    fun retrieveItemsList() : String {
        return Gson().toJson(itemRepository.retrieveAllItems())
    }
}
