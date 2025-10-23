package net.holosen.onlineshopapp.config

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.holosen.onlineshopapp.config.session.SessionManager
import javax.inject.Inject

@HiltAndroidApp
class ShopApp : Application() {

    @Inject
    lateinit var sessionManager: SessionManager
}