package com.example.database.sqlite.dto

data class MyProductEpisode(
//    val id: Int = 0,
    var productId: Int? = null,
    var productEpisodeId: Int? = null,
    var totalPageInfo: Int? = null,
    var finishedPageInfo: Int? = null,
    var finishedPageExtendInfo: String? = null,
    var lastUsedTicketRentType: Int? = null,
    var lastUsedTicketTypeId: Int? = null,
    var lastViewerStartedAt: String? = null,
    var ticketRentStartedAt: String? = null,
    var ticketRentExpiredAt: String? = null,
    var episodeSaleType: String? = null,
    var jsonText: String? = null,
    var fileDownloadStatus: Int? = null,
    var status: Int? = null,
    var updatedAt: String? = null
//)    val productId: Int = 0,
//    val productEpisodeId: Int = 0,
//    val totalPageInfo: Int = 0,
//    val finishedPageInfo: Int = 0,
//    val finishedPageExtendInfo: String = "",
//    val lastUsedTicketRentType: Int = 0,
//    val lastUsedTicketTypeId: Int = 0,
//    val lastViewerStartedAt: String = "0000-00-00 00:00:00",
//    val ticketRentStartedAt: String = "0000-00-00 00:00:00",
//    val ticketRentExpiredAt: String = "0000-00-00 00:00:00",
//    val episodeSaleType: String = "",
//    val jsonText: String = "",
//    val fileDownloadStatus: Int = 0,
//    val status: Int = 0,
//    val updatedAt: String = ""
)