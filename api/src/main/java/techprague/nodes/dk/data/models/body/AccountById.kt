package techprague.nodes.dk.data.models.body

import techprague.nodes.dk.data.models.MmrEstimate
import techprague.nodes.dk.data.models.Profile

/**
 *
 * @param tracked_until tracked_until
 * @param solo_competitive_rank solo_competitive_rank
 * @param competitive_rank competitive_rank
 * @param rank_tier rank_tier
 * @param leaderboard_rank leaderboard_rank
 * @param mmr_estimate
 * @param profile
 */
data class AccountById(
    val competitive_rank: Int,
    val leaderboard_rank: Any?,
    val mmr_estimate: MmrEstimate,
    val profile: Profile,
    val rank_tier: Any?,
    val solo_competitive_rank: Int,
    val tracked_until: String
)