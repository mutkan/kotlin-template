package techprague.nodes.dk.data.models

data class Chat(
    val key: String,
    val player_slot: Int,
    val slot: Int,
    val time: Int,
    val type: String,
    val unit: String
)