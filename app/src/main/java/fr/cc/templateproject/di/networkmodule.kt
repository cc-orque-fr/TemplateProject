package fr.cc.templateproject.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import fr.cc.templateproject.repository.RepositoryImpl
import fr.cc.templateproject.repository.Repository
import fr.cc.templateproject.service.TemplateService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


val networkModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Json {
            ignoreUnknownKeys = true // Ignore extra fields from server
            isLenient = true         // Be lenient with JSON
            encodeDefaults = true
        }
    }


    single {
          val contentType = "application/json".toMediaType()
          Retrofit.Builder()
              .baseUrl("") // Replace with your API base URL
              .client(get())
              .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
              .build()
      }

      single<TemplateService> {
          get<Retrofit>().create(TemplateService::class.java)
      }

      single<Repository> {
          RepositoryImpl(get())
      }

}