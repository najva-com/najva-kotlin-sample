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

    var color = Color.BLACK

    lateinit var paint: Paint

    var line: RectF? = null

    var lastX = -1f
    var lastY = -1f

    var currentX = -1f
    var currentY = -1f

    lateinit var offScreenCanvas: Canvas
    lateinit var bitmap: Bitmap

    lateinit var bitmatrix: Matrix


    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?) : this(context, null)

    init {
        viewTreeObserver.addOnGlobalLayoutListener {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            offScreenCanvas = Canvas(bitmap)

            setOnTouchListener { _, event ->
                if (event.x <= 0 || event.y <= 0) return@setOnTouchListener false
                if (event.x > width - 10 || event.y > height - 10) return@setOnTouchListener false

                drawLine(event.x, event.y)
                invalidate()

                if (event.action == MotionEvent.ACTION_UP) {
                    lastY = -1f
                    lastX = -1f
                }
                return@setOnTouchListener true
            }
        }

        initPaint()

        bitmatrix = Matrix()
    }

    private fun drawLine(x: Float, y: Float) {
        currentX = x
        currentY = y

        if (lastX >= 0f || lastY >= 0f) {
            line = RectF(currentX, currentY, lastX, lastY)
            Log.d("Drawer", "line added")
        }

        lastX = x
        lastY = y

        paint.color = color

        line?.apply {
            offScreenCanvas.drawLine(this.left, this.top, this.right, this.bottom, paint)
        }

    }


    private fun initPaint() {
        paint = Paint()
        paint.strokeWidth = 3f
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, bitmatrix, paint)

    }
}