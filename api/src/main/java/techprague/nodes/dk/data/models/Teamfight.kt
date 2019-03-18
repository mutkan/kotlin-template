package techprague.nodes.dk.data.models

data class Teamfight(
    val deaths: Int,
    val end: Int,
    val last_death: Int,
    val players: List<Player>,
    val start: Int
)