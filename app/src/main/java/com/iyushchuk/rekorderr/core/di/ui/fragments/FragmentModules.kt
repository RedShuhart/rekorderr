package com.iyushchuk.rekorderr.core.di.ui.fragments


import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import dagger.Module
import dagger.Provides

@Module
class SplashModule

@Module
class FeedModule(private val feed: MutableList<Rekord>) {
    @Provides
    internal fun provideFeed(): MutableList<Rekord> = feed
}

@Module
class VideoRekorderModule(private val video: Rekord) {
    @Provides
    internal fun provideVideo(): Rekord = video
}

@Module
class PhotoRekorderModule(private val photo: Rekord) {
    @Provides
    internal fun providePhoto(): Rekord = photo
}