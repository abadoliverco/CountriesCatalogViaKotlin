package com.oliver.countriescatalogviakotlin.di.module

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.GsonBuilder
import com.oliver.countriescatalogviakotlin.BuildConfig
import com.oliver.countriescatalogviakotlin.di.scope.PerDataManager
import dagger.Module
import dagger.Provides
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

/**
 * Created by oliverabad on 21/7/18.
 */

@Module
class NetworkModule {

    private val TIMEOUT_DURATION = 30

    @Provides
    @PerDataManager
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    /**
     * Provides Cookie management instance.
     *
     * @return CookieManager cookie manager for OkHttpClient.
     */
    @Provides
    @PerDataManager
    fun provideCookieManager(): CookieManager {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        return cookieManager
    }

    @Provides
    @PerDataManager
    fun providesSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }


    /**
     * Provides OkHttp client.
     *
     * @return OkHttpClient OkHttp client instance.
     */
    @Provides
    @PerDataManager
    fun provideOkHttpClient(cookieManager: CookieManager): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cookieJar(JavaNetCookieJar(cookieManager))

        //Take 3 re-try just to be sure
        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request()
            var response = chain.proceed(request)

            var count = 1
            while (!response.isSuccessful && count < 3) {
                Timber.e("Request Retry - $count")
                count++
                response = chain.proceed(request)
            }
            response
        }


        /** enable logging on dev-only  */
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }

        return okHttpClientBuilder.build()
    }

    /**
     * Provides RxJava call adaptor instance.
     *
     * @return RxJavaCallAdapter RxJava call adapter instance.
     */
    @Provides
    @PerDataManager
    fun provideRxJavaCallAdapterFactory(): RxJavaCallAdapterFactory {
        return RxJavaCallAdapterFactory.create()
    }


    /**
     * Provides Retrofit instance for API call.
     *
     * @param client           OkHttpClient Inject OkHttpClient from dependcy tree.
     * @param converterFactory GsonConverterFactory Inject GsonConverterFactory from dependency
     * tree.
     * @param adapterFactory   RxJavaCallAdapterFactory Inject RxJava call adapter factory from
     * dependency tree.
     * @return Retrofit Retrofit client instance.
     */
    @Provides
    @PerDataManager
    fun provideRetrofit(
            client: OkHttpClient,
            converterFactory: GsonConverterFactory,
            adapterFactory: RxJavaCallAdapterFactory): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build()
    }
}