package com.example.sqlite.db.pojo

data class MyProduct(
    val id: Int,
    val productId: Int,
    val lastProductEpisodeId: Int,
    val lastProductEpisodeTitle: String,
    val ticketTotalCount: Int,
    val waitFreeTicketStartChargedAt: String,
    val waitFreeTicketFinishChargedAt: String,
    val waitFreeEpisodeCount: Int,
    val waitFreeEpisodeReadCount: Int,
    val nowFreeNextMetaInfo: String,
    val nowFreeNextReadAt: String,
    val nowFreeOpenStatusFinishDays: Int,
    val isTicketConsumedMyProductFlag: Int,
    val lastPurchasedAt: String,
    val bookmarkedAt: String,
    val userConsumedTicketProductEpisodeInfoLastSyncTime: String,
    val productEpisodeListSortInfo: Int,
    val localPushStatus: Int,
    val localHistoryStatus: Int,
    val ticketConsumedMyProductStatus: Int,
    val jsonText: String,
    val status: Int,
    val sortValueListUpdatedAsc: Int,
    val flagFetchSortValueListUpdatedAsc: Int,
    val updatedAt: String
)