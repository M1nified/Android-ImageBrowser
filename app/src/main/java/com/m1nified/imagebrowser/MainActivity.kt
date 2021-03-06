package com.m1nified.imagebrowser

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import com.m1nified.helloworld.DownloadImageTask
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.max
import java.lang.Integer.min

class MainActivity : AppCompatActivity() {

    var imageItems : MutableList<ImageItem> = mutableListOf<ImageItem>()
    var activeIndex: Int = 0

    init{
        imageItems.add(ImageItem("Obrazek 1", "https://www.w3schools.com/howto/img_fjords.jpg"))
        imageItems.add(ImageItem("Obrazek 2", "https://www.w3schools.com/w3css/img_lights.jpg"))
        imageItems.add(ImageItem("Obrazek 3", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsXUtxNPqBET8CdLgZ-ByWd6pa9AQioyOl-Drf2G7dhaC65irp6Q"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.updateView()

        imageButtonNext.setOnClickListener{
            this.showNext()
        }

        imageButtonPrev.setOnClickListener{
            this.showPrev()
        }

        imageButtonSave.setOnClickListener{
            this.saveChanges()
        }

        imageButtonAdd.setOnClickListener {
            this.addImage()
        }

        imageButtonTakePicture.setOnClickListener {
            if(this.imageItems.count() > 0 && this.activeIndex >= 0 && this.activeIndex <= this.imageItems.count()) {
                val imageItem = this.imageItems[activeIndex]
                if(imageItem.srcUrl == "" && imageItem.image == null ){

                }
                this.capturePhoto()
            }
        }

    }

    fun updateView(){
        if(this.imageItems.count() > 0 && this.activeIndex >= 0 && this.activeIndex <= this.imageItems.count()){
            val imageItem = this.imageItems[activeIndex]
            editTextTitle.setText(imageItem.title)
            editTextUrl.setText(imageItem.srcUrl)
            if(imageItem.image != null){
                imageView.setImageBitmap(imageItem.image)
            }else if(imageItem.srcUrl != null && imageItem.srcUrl != "") {
                DownloadImageTask(imageView).execute(imageItem.srcUrl)
            }else{
                imageView.setImageDrawable(null)
            }
            textViewPosition.text = "${this.activeIndex + 1}/${this.imageItems.count()}"
        }
    }

    fun showNext(){
        this.activeIndex = min(this.activeIndex + 1, this.imageItems.count() - 1)
        this.updateView()
    }

    fun showPrev(){
        this.activeIndex = max(this.activeIndex-1, 0)
        this.updateView()
    }

    fun saveChanges(){
        if(this.imageItems.count() > 0 && this.activeIndex >= 0 && this.activeIndex <= this.imageItems.count()) {
            val imageItem = this.imageItems[activeIndex]
            imageItem.srcUrl = editTextUrl.text.toString()
            imageItem.title = editTextTitle.text.toString()
            this.updateView()
        }
    }

    fun addImage(){
        this.imageItems.add(ImageItem("",""))
        this.activeIndex = this.imageItems.count() - 1
        this.updateView()
    }

    val REQUEST_IMAGE_CAPTURE: Int = 1

    private fun capturePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Log.d("onActivityResult", requestCode.toString())
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val photo: Bitmap = data.getParcelableExtra("data")
            if(this.imageItems.count() > 0 && this.activeIndex >= 0 && this.activeIndex <= this.imageItems.count()) {
                val imageItem = this.imageItems[activeIndex]
                imageItem.image = photo
                this.updateView()
            }
        }
    }
}
