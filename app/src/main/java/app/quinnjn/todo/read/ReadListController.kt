package app.quinnjn.todo.read

import app.quinnjn.todo.ListModel
import java.io.File

class ReadListController {
    fun load(file: File): List<ListModel> {
        val list = mutableListOf<ListModel>()

        if (!file.exists()) {
            return list
        }

        file.forEachLine {
            list.add(ListModel.fromString(it))
        }

        return list
    }

    fun save(file: File, models: List<ListModel>) {
        if (!file.exists()) {
            file.createNewFile()
        }

        val results = models.map{
            " ".repeat(it.depth) + "- [${if(it.checked) "X" else " "}] ${it.description}"
        }.joinToString("\n")

        file.writeText(results)
    }
}