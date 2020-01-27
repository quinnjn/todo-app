package app.quinnjn.todo

import android.app.Application
import android.content.Context

class App: Application() {
    init { INSTANCE = this }

    companion object {
        lateinit var INSTANCE: App
            private set

        val applicationContext: Context get() { return INSTANCE.applicationContext }
    }
}