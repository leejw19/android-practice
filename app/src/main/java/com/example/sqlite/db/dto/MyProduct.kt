package com.example.sqlite.db.dto

data class MyProduct(
    var id: Int = 0,
    var productId: Int = 0,
    var lastProductEpisodeId: Int = 0,
    var lastProductEpisodeTitle: String = "",
    var ticketTotalCount: Int = 0,
    var waitFreeTicketStartChargedAt: String = "",
    var waitFreeTicketFinishChargedAt: String = "",
    var waitFreeEpisodeCount: Int = 0,
    var waitFreeEpisodeReadCount: Int = 0,
    var nowFreeNextMetaInfo: String = "",
    var nowFreeNextReadAt: String = "",
    var nowFreeOpenStatusFinishDays: Int = 0,
    var isTicketConsumedMyProductFlag: Int = 0,
    var lastPurchasedAt: String = "",
    var bookmarkedAt: String = "",
    var userConsumedTicketProductEpisodeInfoLastSyncTime: String = "",
    var productEpisodeListSortInfo: Int = 0,
    var localPushStatus: Int = 0,
    var localHistoryStatus: Int = 0,
    var ticketConsumedMyProductStatus: Int = 0,
    var jsonText: String = "",
    var status: Int = 0,
    var sortValueListUpdatedAsc: Int = 0,
    var flagFetchSortValueListUpdatedAsc: Int = 0,
    var updatedAt: String = ""
)