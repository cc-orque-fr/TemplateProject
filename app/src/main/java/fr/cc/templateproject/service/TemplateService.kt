package fr.cc.templateproject.service

import fr.cc.templateproject.model.StoreItem
import retrofit2.http.GET

interface TemplateService {
    @GET("/product")
    suspend fun getProducts(): List<StoreItem>
}