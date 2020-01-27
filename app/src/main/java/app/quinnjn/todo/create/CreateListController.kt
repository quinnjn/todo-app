package app.quinnjn.todo.create

import app.quinnjn.todo.IO

class CreateListController {
    fun save(name: String, type: IO.Companion.LIST_TYPES, templatesToAdd: List<String>, contents: String) {
        val file = IO.createListFile(name, type)
        var results = templatesToAdd.map {
            IO.listNameFile(it)
        }.map {
            it.readText().trim()
        }.joinToString(separator = "\n")

        results += "\n" + contents

        file.writeText(results.trim())
    }
}