package com.iyushchuk.rekorderr.core.di.ui.fragments

import com.iyushchuk.rekorderr.features.ui.feed.FeedFragment
import com.iyushchuk.rekorderr.features.ui.splash.SplashFragment
import com.iyushchuk.rekorderr.features.ui.video.PhotoRekorderFragment
import com.iyushchuk.rekorderr.features.ui.video.RekordFormFragment
import com.iyushchuk.rekorderr.features.ui.video.VideoRekorderFragment
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(fragment: SplashFragment)
}

@Subcomponent(modules = [FeedModule::class])
interface FeedComponent {
    fun inject(fragment: FeedFragment)
}

@Subcomponent(modules = [VideoRekorderModule::class])
interface VideoRekorderComponent {
    fun inject(fragment: VideoRekorderFragment)
}

@Subcomponent(modules = [PhotoRekorderModule::class])
interface PhotoRekorderComponent {
    fun inject(fragment: PhotoRekorderFragment)
}

@Subcomponent(modules = [RekordFormModule::class])
interface RekordFormComponent {
    fun inject(fragment: RekordFormFragment)
}
