package com.sib.picktrash.admin

data class LaporanModel(
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String,
    val alamat: String,
    val status: String
)