package fr.cc.templateproject.di

import fr.cc.templateproject.viewmodel.StoreItemsViewModel
import org.koin.dsl.module

val viewmodelModule = module {
    single {
        StoreItemsViewModel(get())
    }
}