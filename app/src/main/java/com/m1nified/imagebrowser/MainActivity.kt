package com.m1nified.imagebrowser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var imageItems : MutableList<ImageItem> = mutableListOf<ImageItem>()

    init{
        imageItems.add(ImageItem("Obrazek 1", "https://www.w3schools.com/howto/img_fjords.jpg"))
        imageItems.add(ImageItem("Obrazek 2", "https://www.w3schools.com/howto/img_fjords.jpg"))
        imageItems.add(ImageItem("Obrazek 3", "https://www.w3schools.com/howto/img_fjords.jpg"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
