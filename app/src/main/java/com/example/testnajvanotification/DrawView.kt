package com.example.testnajvanotification

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class DrawView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    var bitmap: Bitmap? = null

    var color = Color.BLACK

    lateinit var paint: Paint

    lateinit var bitmatrix: Matrix

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?) : this(context, null)

    init {
        viewTreeObserver.addOnGlobalLayoutListener {
            initBitmap()
        }

        setOnTouchListener { _, event ->
            if (bitmap == null) {
                return@setOnTouchListener false
            }
            if (event.x <= 0 || event.y <= 0) return@setOnTouchListener false
            if (event.x > width - 10 || event.y > height - 10) return@setOnTouchListener false

            bitmap!!.setPixels(IntArray(25).apply {
                forEachIndexed { index, _ -> set(index, color) }
            }, 0, 5, event.x.toInt(), event.y.toInt(), 5, 5)
            invalidate()
            return@setOnTouchListener true
        }
        initPaint()
        bitmatrix = Matrix()
    }

    private fun initBitmap() {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    }

    private fun initPaint() {
        paint = Paint()
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (bitmap == null) return

        canvas?.drawBitmap(bitmap!!, bitmatrix, paint)
    }
}