package com.qrsanner

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startQRcodeReader(view: View) {
        IntentIntegrator(this).initiateScan()
    }

    fun startSecondQRCodeReader(view : View){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("QR 코드를 스캔하세요")
        //0은 후면, 1은 전면 카메라
        integrator.setCameraId(0)
        //효과음
        integrator.setBeepEnabled(false)
        //스캔한 이미지를 비트맵으로 받을지 안받을지
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null){
                Toast.makeText(this, "scanned : ${result.contents}, format : ${result.formatName}", Toast.LENGTH_SHORT ).show()
            }

            if (result.barcodeImagePath != null){
                val bitmap = BitmapFactory.decodeFile(result.barcodeImagePath)
                imageView.setImageBitmap(bitmap)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    fun moveToCustomActivity(view : View){
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        //효과음
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)
        integrator.captureActivity = CustomBarcodeReader::class.java
        integrator.initiateScan()
    }
}