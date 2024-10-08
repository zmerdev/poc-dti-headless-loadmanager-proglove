package com.dotfoods.headlessproglove
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import de.proglove.sdk.PgManager
import de.proglove.sdk.scanner.BarcodeScanResults
import de.proglove.sdk.scanner.IScannerOutput
import java.util.logging.Logger

class ProGloveService : Service() , IScannerOutput {
    private val logger = Logger.getLogger("sample-logger")
    val pgManager: PgManager = PgManager(logger)

    override fun onCreate() {
        super.onCreate()

        val result = pgManager.ensureConnectionToService(this.applicationContext)

        if(!result) throw Exception("AHHH")
        pgManager.subscribeToScans(this)
        Log.d("ProGloveService", "Service started")

        // Start the service in the foreground with a notification
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE)
        val notification = Notification.Builder(this)
            .setContentTitle("ProGlove Service")
            .setContentText("Running in the background")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Service running in the background
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // No binding since it's headless
    }

    override fun onDestroy() {
        super.onDestroy()


        Log.d("ProGloveService", "Service destroyed")
    }

    override fun onBarcodeScanned(barcodeScanResults: BarcodeScanResults) {
        TODO("Not yet implemented")
        Log.d("Barcode Scanned", "Barcode Scanned ${barcodeScanResults.barcodeContent}")
    }

    override fun onScannerConnected() {
        TODO("Not yet implemented")
        Log.d("Scanner Connected", "Scanner Connected")
    }

    override fun onScannerDisconnected() {
        TODO("Not yet implemented")
        Log.d("Scanner Connected", "Scanner Disconnected")
    }
}
