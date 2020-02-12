package com.example.testnajvanotification

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.max
import kotlin.math.min

class DrawView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    var bitmap: Bitmap? = null

    var color = Color.BLACK

    lateinit var paint: Paint

    lateinit var bitmatrix: Matrix

    var lastX = 0f
    var lastY = 0f

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

            drawLine(event.x, event.y)
            invalidate()

            if (event.action == MotionEvent.ACTION_UP) {
                lastY = 0f
                lastX = 0f
            }
            return@setOnTouchListener true
        }
        initPaint()
        bitmatrix = Matrix()
    }

    private fun drawLine(x: Float, y: Float) {
        if (lastX == 0f && lastY == 0f) {
            lastX = x
            lastY = y
            return
        }

        Log.d("Drawer", "x = $x y= $y lx=$lastX ly=$lastY")

        val rect = Rect(
            min(x, lastX).toInt(),
            min(y, lastY).toInt(),
            max(x, lastX).toInt(),
            max(y, lastY).toInt()
        )

        val pixels = IntArray(rect.width() * rect.height())
        bitmap?.getPixels(pixels, 0, rect.width(), rect.left, rect.top, rect.width(), rect.height())

        pixels.forEachIndexed { index, i -> pixels[index] = color }
        bitmap?.setPixels(pixels, 0, rect.width(), rect.left, rect.top, rect.width(), rect.height())

        lastX = x
        lastY = y
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