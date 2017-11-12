package com.m1nified.imagebrowser

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    }

    fun updateView(){
        if(this.imageItems.count() > 0 && this.activeIndex >= 0 && this.activeIndex <= this.imageItems.count()){
            val imageItem = this.imageItems[activeIndex]
            editTextTitle.setText(imageItem.title)
            editTextUrl.setText(imageItem.srcUrl)
            DownloadImageTask(imageView).execute(imageItem.srcUrl)
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
}
