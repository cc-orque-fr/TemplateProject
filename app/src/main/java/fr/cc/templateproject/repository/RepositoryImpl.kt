package fr.cc.templateproject.repository

import fr.cc.templateproject.model.StoreItem

interface RepositoryImpl {
    suspend fun getProducts(): List<StoreItem>
}