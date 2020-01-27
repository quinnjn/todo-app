package app.quinnjn.todo

import java.io.File
import java.lang.RuntimeException

class IO() {
    companion object {
        enum class LIST_TYPES {
            LIST,
            TEMPLATE
        }

        private val dir by lazy {
            val f = File(App.applicationContext.getExternalFilesDir(null), "")

            f.mkdir()

            f
        }

        private val templates by lazy {
            val templates = File(dir, "templates")
            templates.mkdir()

            templates
        }

        private val lists by lazy {
            val lists = File(dir, "lists")
            lists.mkdir()

            lists
        }

        private fun getNames(f: File): List<String> {
            val list = mutableListOf<String>()
            val fList = f.list()

            if (fList == null || fList.isEmpty()) {
                return list
            }

            list.addAll(f.list())

            return list.map {
                it.replace(".yaml", "")
            }
        }

        fun getTemplateNames(): List<String> = getNames(templates)
        fun getListNames(): List<String> = getNames(lists)
        fun createListFile(name: String, type: LIST_TYPES): File {
            val file = when (type) {
                Companion.LIST_TYPES.TEMPLATE -> File(templates, name + ".yaml")
                Companion.LIST_TYPES.LIST -> File(lists, name + ".yaml")
            }

            file.createNewFile()
            return file
        }

        fun listNameFile(fileName: String): File {
            if (getListNames().contains(fileName)) {
                return File(lists, fileName + ".yaml")
            } else {
                return File(templates, fileName + ".yaml")
            }
        }

        fun deleteList(name: String): Boolean =
            IO.listNameFile(name).delete()
    }
}
