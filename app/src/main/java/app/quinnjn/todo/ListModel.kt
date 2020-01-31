package app.quinnjn.todo

import java.lang.Exception

data class ListModel (
    val description: String,
    val depth: Int,
    var checked: Boolean
) {
    companion object {
        fun valid(model: String): Boolean {
            try {
                model.split("\n").forEach {
                    fromString(it)
                }

            } catch (e: Exception) {
                return false
            }
            return true
        }
        fun fromString(model: String): ListModel {
            val index = model.indexOf('-')
            val checkedIdx = index + 3
            val descriptionIdx = index + 6

            val depth = index
            val checked = model.get(checkedIdx) != ' '
            val description = model.substring(descriptionIdx until model.length)

            return ListModel(description, depth, checked)
        }
    }
}
