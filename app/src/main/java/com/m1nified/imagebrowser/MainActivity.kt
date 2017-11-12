package com.m1nified.imagebrowser

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.m1nified.helloworld.DownloadImageTask
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var imageItems : MutableList<ImageItem> = mutableListOf<ImageItem>()
    var activeIndex: Int = 0

    init{
        imageItems.add(ImageItem("Obrazek 1", "https://www.w3schools.com/howto/img_fjords.jpg"))
        imageItems.add(ImageItem("Obrazek 2", "https://www.w3schools.com/howto/img_fjords.jpg"))
        imageItems.add(ImageItem("Obrazek 3", "https://www.w3schools.com/howto/img_fjords.jpg"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.updateView()

    }

    fun updateView(){
        if(this.imageItems.count() > 0 && this.activeIndex >= 0 && this.activeIndex <= this.imageItems.count()){
            val imageItem = this.imageItems[activeIndex]
            editTextTitle.setText(imageItem.title)
            editTextUrl.setText(imageItem.srcUrl)
            DownloadImageTask(imageView).execute(imageItem.srcUrl)
        }
    }
}
