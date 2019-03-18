package techprague.nodes.dk.data.models

data class PicksBan(
    val hero_id: Int,
    val is_pick: Boolean,
    val order: Int,
    val team: Int
)