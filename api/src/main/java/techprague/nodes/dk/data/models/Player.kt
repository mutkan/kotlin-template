package techprague.nodes.dk.data.models

data class Player(
    val ability_targets: AbilityTargets,
    val ability_uses: Map<String, Int>,
    val buybacks: Int,
    val damage: Int,
    val deaths: Int,
    val deaths_pos: DeathsPos,
    val gold_delta: Int,
    val healing: Int,
    val item_uses: ItemUses,
    val killed: Killed,
    val xp_delta: Int,
    val xp_end: Int,
    val xp_start: Int
)