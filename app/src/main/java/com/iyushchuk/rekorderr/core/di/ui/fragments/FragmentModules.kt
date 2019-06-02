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

@Module
class RekordFormModule(private val rekord: Rekord) {
    @Provides
    internal fun provideRekord(): Rekord = rekord
}

@Module
class PhotoViwerModule(private val photo: Rekord) {
    @Provides
    internal fun providePhoto(): Rekord = photo
}

@Module
class RekordPlayerModule(private val rekord: Rekord) {
    @Provides
    internal fun provideRekord(): Rekord = rekord
}

@Module
class AudioRekorderModule(private val audio: Rekord) {
    @Provides
    internal fun provideAudio(): Rekord = audio
}