package com.example.kotlinminiprojects.cropimage

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import com.example.kotlinminiprojects.R
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_crop_image.*

class CropMyImageActivity : AppCompatActivity() {

    private val cropActivityResultContract =
        object : ActivityResultContract<Any?, Uri?>(){
            override fun createIntent(context: Context, input: Any?): Intent {
                return CropImage.activity()
                    .setAspectRatio(16, 9)
                    .getIntent(this@CropMyImageActivity)
            }

            override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
                return CropImage.getActivityResult(intent)?.uri
            }
        }

    private lateinit var  cropAtivityResultLauncher: ActivityResultLauncher<Any?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)

        cropAtivityResultLauncher = registerForActivityResult(cropActivityResultContract){
            it?.let { uri ->
                iv_cropped_image.setImageURI(uri)
            }
        }

        btn_choose_image.setOnClickListener {
            cropAtivityResultLauncher.launch(null)
        }


    }
}