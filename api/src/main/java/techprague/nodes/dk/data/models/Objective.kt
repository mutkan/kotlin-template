package techprague.nodes.dk.data.models

data class Objective(
    val key: String,
    val player_slot: Int,
    val slot: Int,
    val time: Int,
    val type: String,
    val unit: String
)