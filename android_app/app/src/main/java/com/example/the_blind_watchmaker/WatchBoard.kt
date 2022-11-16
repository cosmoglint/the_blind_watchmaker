package com.example.the_blind_watchmaker

import android.util.AttributeSet
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.widget.AppCompatImageView
import android.util.Log
import android.view.MotionEvent
import androidx.core.content.res.ResourcesCompat

class WatchBoard @JvmOverloads constructor(context: Context, attrs: AttributeSet ?= null , defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {
    private lateinit var watchCanvas: Canvas
    private lateinit var config: Config
    private var canvasWidth: Int = 0
    private var canvasHeight: Int = 0
    private var watchWidth: Int = 0
    private var watchHeight: Int = 0
    private var angle: Float = 60.toFloat()
    private lateinit var testWatch: Watch

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.canvasWidth = w
        this.canvasHeight = h
    }

    override fun onDraw(canvas: Canvas) {
        this.watchCanvas = canvas
        super.onDraw(this.watchCanvas)
        handleTouch(canvas)
        testFun(xpp, ypp)
    }

    var xpp: Float = 500.toFloat()
    var ypp: Float = 500.toFloat()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var touchXPos : Float? = event?.x
        var touchYPos : Float? = event?.y
        when (event?.action){
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_UP -> {
            }
        }
        xpp = touchXPos!!.toFloat()
        ypp = touchYPos!!.toFloat()
        invalidate()
//        handleTouch()
        return super.onTouchEvent(event)
    }

    private fun setupWatchboard() {
        val minWatchSize = 300
        // TODO: make sure the x and y watch counts are scalable and fit the screen
        // TODO: in the future make sure the user can move around on the screen on a infinite scale
        val xWatchCount = this.canvasWidth / minWatchSize
        val yWatchCount = this.canvasHeight / minWatchSize
        watchWidth = minWatchSize
        watchHeight = minWatchSize

    }

    private fun handleTouch(canvas: Canvas) {
        if (!this::testWatch.isInitialized){
            Log.d("H1","INIT BRO")
            this.testWatch = Watch(Point(0.toFloat(),0.toFloat()), this.canvasWidth, this.canvasWidth)
            this.testWatch.watchCanvas = canvas
        }
        else{
            this.testWatch.watchCanvas = canvas
        }
        this.testWatch.show()
        setupWatchboard()
        Log.d("TEST","running bro")
    }

    fun testFun(x: Float, y: Float){
        var circlePaint = Paint()
        circlePaint.setAntiAlias(true)
        circlePaint.setColor(Color.BLUE)
        circlePaint.setStyle(Paint.Style.STROKE)
        circlePaint.setStrokeJoin(Paint.Join.MITER)
        circlePaint.setStrokeWidth(4f)

        watchCanvas.drawCircle(x, y, 10.toFloat(), circlePaint)
    }
}


