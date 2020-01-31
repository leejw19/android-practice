package com.example.database.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class MyProduct() : RealmObject() {

    @PrimaryKey
    var productId: Int = 0
    var lastProductEpisodeId: Int = 0
    var lastProductEpisodeTitle: String = ""
    var ticketTotalCount: Int = 0
    @Required
    var waitFreeTicketStartChargedAt: String = "0000-00-00 00:00:00"
    @Required
    var waitFreeTicketFinishChargedAt: String = "0000-00-00 00:00:00"
    var waitFreeEpisodeCount: Int = -1
    var waitFreeEpisodeReadCount: Int = -2
    var nowFreeNextMetaInfo: String = ""
    @Required
    var nowFreeNextReadAt: String = "0000-00-00 00:00:00"
    var nowFreeOpenStatusFinishDays: Int = 0
    var isTicketConsumedMyProductFlag: Int = 0
    @Required
    var lastPurchasedAt: String = "0000-00-00 00:00:00"
    @Required
    var bookmarkedAt: String = "0000-00-00 00:00:00"
    @Required
    var userConsumedTicketProductEpisodeInfoLastSyncTime: String = "1999-01-01 00:00:00"
    var productEpisodeListSortInfo: Int = 1
    var localPushStatus: Int = 1
    var localHistoryStatus: Int = 0
    var ticketConsumedMyProductStatus: Int = 0
    var jsonText: String = ""
    var status: Int = 0
    var sortValueListUpdatedAsc: Int = 0
    var flagFetchSortValueListUpdatedAsc: Int = 0
    @Required
    var updatedAt: String = "0000-00-00 00:00:00"

    constructor(
        productId: Int,
        lastProductEpisodeId: Int,
        lastProductEpisodeTitle: String,
        ticketTotalCount: Int,
        waitFreeTicketStartChargedAt: String,
        waitFreeTicketFinishChargedAt: String,
        waitFreeEpisodeCount: Int,
        waitFreeEpisodeReadCount: Int,
        nowFreeNextMetaInfo: String,
        nowFreeNextReadAt: String,
        nowFreeOpenStatusFinishDays: Int,
        isTicketConsumedMyProductFlag: Int,
        lastPurchasedAt: String,
        bookmarkedAt: String,
        userConsumedTicketProductEpisodeInfoLastSyncTime: String,
        productEpisodeListSortInfo: Int,
        localPushStatus: Int = 1,
        localHistoryStatus: Int,
        ticketConsumedMyProductStatus: Int,
        jsonText: String,
        status: Int,
        sortValueListUpdatedAsc: Int,
        flagFetchSortValueListUpdatedAsc: Int,
        updatedAt: String
    ) : this() {
        this.productId = productId
        this.lastProductEpisodeId = lastProductEpisodeId
        this.lastProductEpisodeTitle = lastProductEpisodeTitle
        this.ticketTotalCount = ticketTotalCount
        this.waitFreeTicketStartChargedAt = waitFreeTicketStartChargedAt
        this.waitFreeTicketFinishChargedAt = waitFreeTicketFinishChargedAt
        this.waitFreeEpisodeCount = waitFreeEpisodeCount
        this.waitFreeEpisodeReadCount = waitFreeEpisodeReadCount
        this.nowFreeNextMetaInfo = nowFreeNextMetaInfo
        this.nowFreeNextReadAt = nowFreeNextReadAt
        this.nowFreeOpenStatusFinishDays = nowFreeOpenStatusFinishDays
        this.isTicketConsumedMyProductFlag = isTicketConsumedMyProductFlag
        this.lastPurchasedAt = lastPurchasedAt
        this.bookmarkedAt = bookmarkedAt
        this.userConsumedTicketProductEpisodeInfoLastSyncTime =
            userConsumedTicketProductEpisodeInfoLastSyncTime
        this.productEpisodeListSortInfo = productEpisodeListSortInfo
        this.localPushStatus = localPushStatus
        this.localHistoryStatus = localHistoryStatus
        this.ticketConsumedMyProductStatus = ticketConsumedMyProductStatus
        this.jsonText = jsonText
        this.status = status
        this.sortValueListUpdatedAsc = sortValueListUpdatedAsc
        this.flagFetchSortValueListUpdatedAsc = flagFetchSortValueListUpdatedAsc
        this.updatedAt = updatedAt
    }
}