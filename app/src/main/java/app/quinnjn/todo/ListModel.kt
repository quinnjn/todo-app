package app.quinnjn.todo

data class ListModel (
    val description: String,
    val depth: Int,
    var checked: Boolean
) {
    companion object {
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
