package com.example.sqlite.db.dto

data class MyProductEpisode(
    val id: Int = 0,
    val productId: Int = 0,
    val productEpisodeId: Int = 0,
    val totalPageInfo: Int = 0,
    val finishedPageInfo: Int = 0,
    val finishedPageExtendInfo: String = "",
    val lastUsedTicketRentType: Int = 0,
    val lastUsedTicketTypeId: Int = 0,
    val lastViewerStartedAt: String = "",
    val ticketRentStartedAt: String = "",
    val ticketRentExpiredAt: String = "",
    val episodeSaleType: String = "",
    val jsonText: String = "",
    val fileDownloadStatus: Int = 0,
    val status: Int = 0,
    val updatedAt: String = ""
)