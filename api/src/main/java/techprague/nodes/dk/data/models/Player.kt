package techprague.nodes.dk.data.models

data class Player(
    val ability_targets: Any,
    val ability_uses: Any,
    val buybacks: Int,
    val damage: Any,
    val deaths: Int,
    val deaths_pos: Any,
    val gold_delta: Int,
    val healing: Int,
    val item_uses: ItemUses,
    val killed: Killed,
    val xp_delta: Int,
    val xp_end: Int,
    val xp_start: Int
)