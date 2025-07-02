package com.example.dependencyinjection.di

import com.example.dependencyinjection.service.GithubService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

// STEP 4) `AppModules`에 `@Module` 어노테이션과
// `@InstallIn(SingletonComponent::class)` 어노테이션을 추가합니다.

// 모듈 : 의존성 주입을 하고 있는 프로바이더가 있는 공간
@InstallIn(SingletonComponent::class)
@Module
class AppModules {
    // STEP 5) 아래 프로파이더를 만듭시다.
    @Singleton // 한 번만 생성되게 하겠다
    @Provides
    @Named("API_URI")
    fun provideWebAPI(): String = "https://api.github.com/"

    // Gson: Google이 만든 Json 라이브러리
    // JSON <-> 코틀린 객체, 자바 객체
    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create() // 상대방이 보내는 json이 lower_underscore일 수 있음

    // Gson과 Retrofit을 연결해주는 컨버터
    @Singleton
    @Provides
    fun provideConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("API_URI") apiUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideGithubService(
        retrofit: Retrofit
    ): GithubService = retrofit.create(GithubService::class.java)
}