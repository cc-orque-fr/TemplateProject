package fr.cc.templateproject.repository

import fr.cc.templateproject.model.StoreItem
import fr.cc.templateproject.service.TemplateService

class Repository(val templateService: TemplateService) : RepositoryImpl {
    override suspend fun getProducts(): List<StoreItem> {
        return templateService.getProducts()
    }
}