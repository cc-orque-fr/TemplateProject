package fr.cc.templateproject.service

import fr.cc.templateproject.model.StoreItem
import retrofit2.http.GET

interface TemplateService {
    @GET("/products")
    suspend fun getProducts(): List<StoreItem>
}