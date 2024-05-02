package com.example.newsuktechtest.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinDTO(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Long,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "is_active") val isActive: Boolean,
    val type: String,
    val logo: String,
    val tags: List<TagDTO>? = null,
    val team: List<TeamDTO>? = null,
    val description: String? = null,
    val message: String? = null,
    @Json(name = "open_source") val openSource: Boolean,
    @Json(name = "started_at") val startedAt: String? = null,
    @Json(name = "development_status") val developmentStatus: String? = null,
    @Json(name = "hardware_wallet") val hardwareWallet: Boolean,
    @Json(name = "proof_type") val proofType: String? = null,
    @Json(name = "org_structure") val orgStructure: String? = null,
    @Json(name = "hash_algorithm") val hashAlgorithm: String? = null,
    val links: LinksDTO,
    @Json(name = "links_extended") val linksExtended: List<LinksExtendedDTO>,
    val whitepaper: WhitepaperDTO,
    @Json(name = "first_data_at") val firstDataAt: String? = null,
    @Json(name = "last_data_at") val lastDataAt: String? = null
)

@JsonClass(generateAdapter = true)
data class TagDTO(
    val id: String? = null,
    val name: String? = null,
    @Json(name = "coin_counter") val coinCounter: Long,
    @Json(name = "ico_counter") val icoCounter: Long
)

@JsonClass(generateAdapter = true)
data class TeamDTO(
    val id: String? = null,
    val name: String? = null,
    val position: String? = null
)

@JsonClass(generateAdapter = true)
data class LinksDTO(
    val explorer: List<String>? = null,
    val facebook: List<String>? = null,
    val reddit: List<String>? = null,
    @Json(name = "source_code") val sourceCode: List<String>? = null,
    val website: List<String>? = null,
    val youtube: List<String>? = null
)

@JsonClass(generateAdapter = true)
data class LinksExtendedDTO(
    val url: String? = null,
    val type: String? = null,
    val stats: StatsDTO? = null
)

@JsonClass(generateAdapter = true)
data class StatsDTO(
    val subscribers: Long? = null,
    val contributors: Long? = null,
    val stars: Long? = null,
    val followers: Long? = null
)

@JsonClass(generateAdapter = true)
data class WhitepaperDTO(
    val link: String? = null,
    val thumbnail: String? = null
)
