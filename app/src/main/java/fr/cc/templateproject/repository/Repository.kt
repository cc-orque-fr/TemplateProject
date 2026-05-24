package fr.cc.templateproject.repository

import fr.cc.templateproject.model.StoreItem

interface Repository {
    suspend fun getProducts(): List<StoreItem>
}