package com.m1nified.helloworld

import android.graphics.Bitmap
import android.os.AsyncTask
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView


/**
 * Created by m1nified on 12.11.2017.
 */
public class DownloadImageTask(internal var bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val urldisplay = urls[0]
        var mIcon11: Bitmap? = null
        try {
            val `in` = java.net.URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        return mIcon11
    }

    override fun onPostExecute(result: Bitmap) {
        bmImage.setImageBitmap(result)
    }
}

// DownloadImageTask(imageView).execute("https://www.w3schools.com/howto/img_fjords.jpg");