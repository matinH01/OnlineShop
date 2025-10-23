package net.holosen.onlineshopapp.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.holosen.onlineshopapp.api.customer.UserApi
import net.holosen.onlineshopapp.api.invoices.InvoiceApi
import net.holosen.onlineshopapp.api.invoices.TransactionApi
import net.holosen.onlineshopapp.api.products.ProductApi
import net.holosen.onlineshopapp.api.products.ProductCategoryApi
import net.holosen.onlineshopapp.api.site.SliderApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder()
            .baseUrl("https://onlineshop.holosen.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return provideApi().create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }
}