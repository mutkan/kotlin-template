package techprague.nodes.dk.data.models

data class BuybackLog(
    val player_slot: Int,
    val slot: Int,
    val time: Int,
    val type: String
)