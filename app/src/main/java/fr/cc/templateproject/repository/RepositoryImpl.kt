package fr.cc.templateproject.repository

import fr.cc.templateproject.model.StoreItem
import fr.cc.templateproject.service.TemplateService

class RepositoryImpl(val templateService: TemplateService) : Repository {
    override suspend fun getProducts(): List<StoreItem> {
        return templateService.getProducts()
    }
}