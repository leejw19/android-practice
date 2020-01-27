package com.example.sqlite.db.dto

data class MyProduct(
//    var id: Int = 0,
    var productId: Int = 0,
    var lastProductEpisodeId: Int = 0,
    var lastProductEpisodeTitle: String = "",
    var ticketTotalCount: Int = 0,
    var waitFreeTicketStartChargedAt: String = "0000-00-00 00:00:00",
    var waitFreeTicketFinishChargedAt: String = "0000-00-00 00:00:00",
    var waitFreeEpisodeCount: Int = -1,
    var waitFreeEpisodeReadCount: Int = -2,
    var nowFreeNextMetaInfo: String = "",
    var nowFreeNextReadAt: String = "0000-00-00 00:00:00",
    var nowFreeOpenStatusFinishDays: Int = 0,
    var isTicketConsumedMyProductFlag: Int = 0,
    var lastPurchasedAt: String = "0000-00-00 00:00:00",
    var bookmarkedAt: String = "",
    var userConsumedTicketProductEpisodeInfoLastSyncTime: String = "1999-01-01 00:00:00",
    var productEpisodeListSortInfo: Int = 1,
    var localPushStatus: Int = 0,
    var localHistoryStatus: Int = 0,
    var ticketConsumedMyProductStatus: Int = 0,
    var jsonText: String = "",
    var status: Int = 0,
    var sortValueListUpdatedAsc: Int = 0,
    var flagFetchSortValueListUpdatedAsc: Int = 0,
    var updatedAt: String = "0000-00-00 00:00:00"
)