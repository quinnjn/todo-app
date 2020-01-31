package app.quinnjn.todo.create

import app.quinnjn.todo.IO
import app.quinnjn.todo.ListModel

class CreateListController {
    fun save(name: String, type: IO.Companion.LIST_TYPES, templatesToAdd: List<String>, contents: String): String? {
        if (!ListModel.valid(contents)) {
            return "Contents not valid"
        }

        val file = IO.createListFile(name, type)
        var results = templatesToAdd.map {
            IO.listNameFile(it)
        }.map {
            it.readText().trim()
        }.joinToString(separator = "\n")

        results += "\n" + contents
        results = results.trim()


        file.writeText(results)
        return null
    }
}