package com.example.kotlinstandardapplication.Canvaspage

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityCanvasDrawBinding

class CanvasDrawActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCanvasDrawBinding
    private lateinit var bitmap: Bitmap/*blackboard or container*/
    private lateinit var canvas: Canvas/*what stores all shapes or drawables*/
    private lateinit var paint: Paint/*pen*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_canvas_draw)*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas_draw)

        createBitmap()
        drawShape()
        drawCircle()
        drawLine()
    }

    private fun createBitmap(){
        /*Step 1: initialize a bitmap with some width and height*/
        bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)

        /*assign it to a Canvas*/
        canvas = Canvas(bitmap)
        canvas.drawARGB(255, 78, 168, 186);
    }

    private fun drawShape(){
        /*Then we draw a Rectangle and a Oval to the Canvas object.*/
        var shapeDrawable: ShapeDrawable?


        /*draw rectangle shape to canvas*/
        shapeDrawable = ShapeDrawable(RectShape())

        /*bounds are coordinate*/
        shapeDrawable.setBounds( 0, 0, 50, 90)
        shapeDrawable.paint.color = Color.parseColor("#009944")
        shapeDrawable.draw(canvas)

        /*draw oval shape to canvas*/
        shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.setBounds( 0, 100, 300, 200)
        shapeDrawable.paint.color = Color.parseColor("#009191")
        shapeDrawable.draw(canvas)

        /*Now the Bitmap object holds the pixels with rectangle and oval drawn.*/


        /*assign the bitmap as a background of ImageView mentioned in the layout xml file.*/
        binding.image.background = BitmapDrawable(resources, bitmap)
    }

    private fun drawCircle(){
        /*configure the pen*/
        paint = Paint()
        paint.color = Color.parseColor("#FFFFFF")
        paint.strokeWidth = 30F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.isDither = true


        // get device dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        // circle center
        println("Width : "+displayMetrics.widthPixels)
        /*var center_x = (displayMetrics.widthPixels/2).toFloat()
        var center_y = (displayMetrics.heightPixels/2).toFloat()*/

        var center_x = 400f
        var center_y = 600f
        var radius = 100F

        // draw circle
        canvas.drawCircle(center_x, center_y, radius, paint)
        // now bitmap holds the updated pixels

        /*assign the bitmap as a background of ImageView mentioned in the layout xml file.*/
        binding.image.background = BitmapDrawable(resources, bitmap)
    }

    private fun drawLine(){
        canvas.drawLine(450F, 100F, 450F, 400F, paint)
    }
}