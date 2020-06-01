package com.example.pixbydemo.network


import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import java.util.concurrent.TimeUnit

object RetrofitUtil {
    fun apiService(
            baseUrl: String = "https://pixabay.com/api/",
            connectionTimeOutInSec: Long = 15,
            readTimeOutInSec: Long = 90,
            writeTimeOutInSec: Long = 240

    ): ApiService {
        return getRetrofitClient(
            getokhttpClientBuilder(
                connectionTimeOutInSec,
                readTimeOutInSec,
                writeTimeOutInSec
            ), baseUrl
        ).create(ApiService::class.java)
    }

    fun getRetrofitClient(okHttpClientBuilder: OkHttpClient.Builder, baseUrl: String) = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClientBuilder.build())//
            .baseUrl(baseUrl)
            .build()

    fun getokhttpClientBuilder(connectTimeoutInSec: Long, readTimeoutInSec: Long, writeTimeoutInSec: Long): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        okHttpClientBuilder.connectTimeout(connectTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(readTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(writeTimeoutInSec, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }
}
