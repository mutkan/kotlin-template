package techprague.nodes.dk.data.models

data class Benchmarks(
    val gold_per_min: GoldPerMin,
    val hero_damage_per_min: HeroDamagePerMin,
    val hero_healing_per_min: HeroHealingPerMin,
    val kills_per_min: KillsPerMin,
    val last_hits_per_min: LastHitsPerMin,
    val lhten: Lhten,
    val stuns_per_min: StunsPerMin,
    val tower_damage: TowerDamage,
    val xp_per_min: XpPerMin
)