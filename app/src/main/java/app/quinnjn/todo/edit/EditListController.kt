package app.quinnjn.todo.edit

import android.content.Context
import app.quinnjn.todo.ListModel
import java.io.File
import java.io.File.separator

class EditListController {
    fun load(file: File): String {
        return file.readText()
    }

    fun save(file: File, models: String): String? {
        if (!file.exists()) {
            file.createNewFile()
        }

        // Verify that the models are valid

        if (!verify(models)) {
            return "Changes are not valid"
        }

        file.writeText(models)
        return null
    }

    private fun verify(models: String): Boolean {
        try {
            models.split("\n").forEach {
                ListModel.fromString(it)
            }
        } catch (e: Exception) {
            return false
        }

        return true
    }
}