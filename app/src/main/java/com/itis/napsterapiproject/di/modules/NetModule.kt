package com.itis.napsterapiproject.di.modules

import com.itis.napsterapiproject.BuildConfig
import com.itis.napsterapiproject.data.api.NapsterApi
import com.itis.napsterapiproject.di.qualifiers.ApiKeyHeaderInterceptor
import com.itis.napsterapiproject.di.qualifiers.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_HEADER = "apikey"
private const val API_KEY = BuildConfig.API_KEY
private const val BASE_URL = "https://api.napster.com/v2.2/"

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
    }

    @Provides
    @ApiKeyHeaderInterceptor
    fun apiKeyHeaderInterceptor(): Interceptor = Interceptor {
        val original = it.request()
        it.proceed(
            original.newBuilder()
                .header(
                    API_HEADER, API_KEY
                )
                .build()
        )
    }

    @Provides
    fun provideOkhttp(
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @ApiKeyHeaderInterceptor apiKeyHeaderInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyHeaderInterceptor)
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideApi(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): NapsterApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(NapsterApi::class.java)
}
