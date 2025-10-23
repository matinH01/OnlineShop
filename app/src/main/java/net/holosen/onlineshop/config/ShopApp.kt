package net.holosen.onlineshop.config

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.holosen.onlineshop.config.session.SessionManager
import javax.inject.Inject

@HiltAndroidApp
class ShopApp : Application() {

    @Inject
    lateinit var sessionManager: SessionManager
}