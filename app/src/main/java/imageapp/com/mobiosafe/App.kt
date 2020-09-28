package imageapp.com.mobiosafe

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App : Application() {

    companion object{
        public final var CHANNEL_ID = "exampleServiceChannel"
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Example Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        val manager = getSystemService(
            NotificationManager::class.java
        )
        manager.createNotificationChannel(serviceChannel)
        }
    }
}