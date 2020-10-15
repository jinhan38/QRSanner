package com.qrsanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.journeyapps.barcodescanner.CaptureManager
import kotlinx.android.synthetic.main.activity_custom_barcode_reader.*

class CustomBarcodeReader : AppCompatActivity() {

    private lateinit var captureManager: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_barcode_reader)

        captureManager = CaptureManager(this, zxing_barcode_scanner)
        captureManager.initializeFromIntent(intent, savedInstanceState)
        captureManager.decode()

    }

    override fun onResume() {
        super.onResume()
        captureManager.onResume()
    }

    override fun onPause() {
        super.onPause()
        captureManager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        captureManager.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        captureManager.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        captureManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}