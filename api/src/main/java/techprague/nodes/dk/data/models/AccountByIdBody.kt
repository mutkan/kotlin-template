package techprague.nodes.dk.data.models

data class AccountByIdBody(
    val competitive_rank: Int,
    val leaderboard_rank: Any?,
    val mmr_estimate: MmrEstimate,
    val profile: Profile,
    val rank_tier: Any?,
    val solo_competitive_rank: Int,
    val tracked_until: String
)