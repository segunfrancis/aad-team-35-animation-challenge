package com.example.aad_team_35_animation_challenge.auth.customs

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

import android.graphics.Paint.Align.CENTER
import android.graphics.Paint.Style.FILL
import android.view.MotionEvent.ACTION_UP

import com.example.aad_team_35_animation_challenge.R
import com.example.aad_team_35_animation_challenge.auth.animations.BounceInterpolator
import com.example.aad_team_35_animation_challenge.auth.listeners.OnButtonSwitchedListener
import com.example.aad_team_35_animation_challenge.auth.listeners.OnSignInListener
import com.example.aad_team_35_animation_challenge.auth.listeners.OnSignUpListener
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min
import kotlin.math.tan

class LoginButton : View {
    private var dWidth: Int = 0
    private var dHeight: Int = 0

    private var buttonTop: Int = 0
    private var buttonBottom: Int = 0

    private var signInButtonPaint: Paint? = null
    private var signUpButtonPaint: Paint? = null

    private val loginButtonPath = Path()
    private val signUpButtonPath = Path()

    private val r = Rect()

    private var startRight: Int = 0
    private var currentY: Float = 0.toFloat()
    private var buttonCenter: Int = 0
    private var currentX: Float = 0.toFloat()
    private var currentRight: Float = 0.toFloat()
    private var currentBottomY: Float = 0.toFloat()
    private var currentBottomX: Float = 0.toFloat()
    private var currentArcY: Int = 0
    private var currentArcX: Float = 0.toFloat()

    private var paint2: Paint? = null
    private var signInPaint: Paint? = null
    private var orPaint: Paint? = null
    private var signUpPaint: Paint? = null

    private var currentLoginX: Float = 0.toFloat()
    private var currentSignUpTextX: Float = 0.toFloat()
    private var largeTextSize: Float = 0.toFloat()
    private var smallTextSize: Float = 0.toFloat()
    private var currentLoginY: Float = 0.toFloat()
    private var currentLeft: Float = 0.toFloat()
    private var signUpOrX: Float = 0.toFloat()
    private var isLogin = true
    private var currentSignUpTextY: Float = 0.toFloat()
    private var currentSignUpX: Float = 0.toFloat()
    private var currentBottomSignUpX: Float = 0.toFloat()
    private var startLeft: Int = 0

    private var callback: OnButtonSwitchedListener? = null

    private var startSignUpTextX: Float = 0.toFloat()
    private var startSignUpTextY: Float = 0.toFloat()
    private var startLoginX: Float = 0.toFloat()
    private var startLoginY: Float = 0.toFloat()
    private var loginOrX: Float = 0.toFloat()
    private var loginButtonOutline: Rect? = null
    private var signUpButtonOutline: Rect? = null

    private var onSignUpListener: OnSignUpListener? = null
    private var onSignInListener: OnSignInListener? = null
    private var loginTextOutline: Rect? = null
    private var signUpTextOutline: Rect? = null

    private val buttonHeight: Int get() = resources.getDimensionPixelOffset(R.dimen.bottom_height)

    private val bottomMargin: Int get() = resources.getDimensionPixelOffset(R.dimen.bottom_margin)

    private val startButtonRight: Float
        get() = resources.getDimensionPixelOffset(R.dimen.bottom_width).toFloat()


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        signInButtonPaint = Paint()
        signInButtonPaint!!.color = ContextCompat.getColor(context, R.color.colorAccent)
        signInButtonPaint!!.style = FILL

        signUpButtonPaint = Paint()
        signUpButtonPaint!!.color = ContextCompat.getColor(context, R.color.colorPrimary)
        signUpButtonPaint!!.style = FILL

        paint2 = Paint()
        paint2!!.color = Color.parseColor("#ffffff")
        paint2!!.style = FILL

        signInPaint = Paint()
        signInPaint!!.color = ContextCompat.getColor(context, R.color.text)
        signInPaint!!.textAlign = CENTER
        signInPaint!!.textSize = dpToPixels(16)

        orPaint = Paint()
        orPaint!!.color = ContextCompat.getColor(context, R.color.text_two)
        orPaint!!.textSize = dpToPixels(16)

        signUpPaint = Paint()
        signUpPaint!!.color = ContextCompat.getColor(context, R.color.text)
        signUpPaint!!.textSize = dpToPixels(34)
        signUpPaint!!.textAlign = CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        dWidth = w
        dHeight = h
        buttonTop = dHeight - bottomMargin - buttonHeight
        buttonBottom = dHeight - bottomMargin
        startRight = startButtonRight.toInt()

        buttonCenter = (buttonBottom - buttonTop) / 2 + buttonTop

        currentSignUpX = dWidth.toFloat()
        currentBottomSignUpX = dWidth.toFloat()

        loginOrX = dpToPixels(32)

        currentY = buttonCenter.toFloat()
        currentBottomY = buttonBottom.toFloat()
        currentRight = startRight.toFloat()
        currentLeft = (dWidth - startRight).toFloat()
        startLeft = dWidth - startRight

        signInPaint!!.getTextBounds(getString(R.string.sign_up), 0, 7, r)

        currentLoginX = dpToPixels(92)
        val signUpWidth = r.right
        currentSignUpTextX = dWidth.toFloat() - (signUpWidth / 2).toFloat() - dpToPixels(32)

        signInPaint!!.getTextBounds(getString(R.string.sign_in), 0, 5, r)

        loginTextOutline = Rect()
        signUpTextOutline = Rect()
        signUpPaint!!.getTextBounds(getString(R.string.sign_in), 0, 5, loginTextOutline)
        signUpPaint!!.getTextBounds(getString(R.string.sign_up), 0, 7, signUpTextOutline)

        loginTextOutline!!.offset(dWidth / 2 - (loginTextOutline!!.right
                + loginTextOutline!!.left) / 2, dpToPixels(457).toInt())

        signUpTextOutline!!.offset(dWidth / 2 - (signUpTextOutline!!.right
                + signUpTextOutline!!.left) / 2, dpToPixels(457).toInt())

        val loginWidth = r.right
        orPaint!!.getTextBounds(context.getString(R.string.or), 0, 2, r)
        val margin = currentLoginX - loginWidth / 2 - dpToPixels(32) - r.right.toFloat()
        signUpOrX = dWidth.toFloat() - signUpWidth.toFloat() -
                dpToPixels(32) - r.right.toFloat() - margin

        currentLoginY = buttonCenter + dpToPixels(8)
        currentSignUpTextY = buttonCenter + dpToPixels(8)
        largeTextSize = dpToPixels(34)
        smallTextSize = dpToPixels(16)

        startLoginX = currentLoginX
        startLoginY = currentLoginY
        startSignUpTextX = currentSignUpTextX
        startSignUpTextY = currentSignUpTextY

        loginButtonPath.moveTo(0f, buttonBottom.toFloat())
        loginButtonPath.lineTo(currentRight, buttonBottom.toFloat())
        loginButtonPath.lineTo(currentRight, buttonTop.toFloat())
        loginButtonPath.lineTo(0f, buttonTop.toFloat())
        loginButtonPath.close()

        signUpButtonPath.moveTo(dWidth.toFloat(), buttonBottom.toFloat())
        signUpButtonPath.lineTo(currentLeft, buttonBottom.toFloat())
        signUpButtonPath.lineTo(currentLeft, buttonTop.toFloat())
        signUpButtonPath.lineTo(dWidth.toFloat(), buttonTop.toFloat())
        signUpButtonPath.close()

        loginButtonOutline = Rect(
                0,
                buttonTop,
                currentRight.toInt() + buttonHeight / 2,
                buttonBottom)

        signUpButtonOutline = Rect(
                (dWidth.toFloat() - currentRight - (buttonHeight / 2).toFloat()).toInt(),
                buttonTop,
                dWidth,
                buttonBottom)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isLogin) {
            canvas.drawText(getString(R.string.sign_up), (dWidth / 2).toFloat(),
                    dpToPixels(457), signUpPaint!!)
        } else {
            canvas.drawText(getString(R.string.sign_in), (dWidth / 2).toFloat(),
                    dpToPixels(457), signInPaint!!)
        }

        if (isLogin) {
            canvas.drawPath(loginButtonPath, signInButtonPaint!!)
            canvas.drawArc(
                    currentRight - buttonHeight / 2 + currentArcX,
                    buttonTop.toFloat(),
                    currentRight + buttonHeight / 2 - currentArcX,
                    buttonBottom.toFloat(),
                    0f,
                    360f,
                    false,
                    signInButtonPaint!!)

            canvas.drawText(getString(R.string.or),
                    loginOrX, buttonCenter + dpToPixels(8), orPaint!!)
            canvas.drawText(getString(R.string.sign_in),
                    currentLoginX, currentLoginY, signInPaint!!)
        } else {
            canvas.drawPath(signUpButtonPath, signUpButtonPaint!!)
            canvas.drawArc(
                    currentLeft - buttonHeight / 2 + currentArcX,
                    buttonTop.toFloat(),
                    currentLeft + buttonHeight / 2 - currentArcX,
                    buttonBottom.toFloat(),
                    0f,
                    360f,
                    false,
                    signUpButtonPaint!!)

            canvas.drawText(getString(R.string.or), signUpOrX,
                    buttonCenter + dpToPixels(8), orPaint!!)

            canvas.drawText(getString(R.string.sign_up), currentSignUpTextX,
                    currentSignUpTextY, signUpPaint!!)
        }
    }

    fun startAnimation() {
        val start = startButtonRight
        val animator = ObjectAnimator.ofFloat(0f, 1f)
        //        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener { animation ->
            val fraction = animation.animatedValue as Float
            val currentAngle = fraction * (PI.toFloat() / 2) // in radians

            val gone = (dWidth - start) * fraction
            currentRight = start + gone
            currentLeft = startLeft - gone

            // fade out sign up text to 0
            if (isLogin) {
                signUpPaint!!.alpha = (255 - 255 * fraction).toInt() // fade out sign up large text
            } else {
                signInPaint!!.alpha = (255 - 255 * fraction).toInt() // fade out signIn large text
            }

            if (orPaint!!.alpha != 0) {
                orPaint!!.alpha = 0
            }

            // move signIn text to center and scale
            if (isLogin) {
                currentLoginX = startLoginX + (dWidth / 2 - startLoginX) * fraction
                currentLoginY = startLoginY - (startLoginY -
                        this@LoginButton.dpToPixels(457)) * fraction
                signInPaint!!.textSize = smallTextSize +
                        (largeTextSize - smallTextSize) * fraction
            } else {
                currentSignUpTextX = startSignUpTextX - (startSignUpTextX - dWidth / 2) * fraction
                currentSignUpTextY = startSignUpTextY - (startSignUpTextY -
                        this@LoginButton.dpToPixels(457)) * fraction
                signUpPaint!!.textSize = smallTextSize + (largeTextSize - smallTextSize) * fraction
            }

            currentArcY = (fraction * this@LoginButton.dpToPixels(28)).toInt()
            currentArcX = (fraction * this@LoginButton.dpToPixels(37)).toInt().toFloat()

            val y = tan(currentAngle.toDouble()) * currentRight
            val realY = (buttonTop - y).toFloat()
            currentY = max(0f, realY)


            val realBottomY = (buttonBottom + y).toFloat()
            currentBottomY = min(dHeight.toFloat(), realBottomY)


            if (currentY == 0f) { // if reached top, start moving to the right
                val cot = 1.0f / tan(currentAngle.toDouble())
                currentX = ((y - buttonTop) * cot).toFloat()
                currentSignUpX = dWidth - currentX
            }

            if (currentBottomY == dHeight.toFloat()) {
                val cot = 1.0f / tan(currentAngle.toDouble())
                currentBottomX = ((y - this@LoginButton.bottomMargin) * cot).toFloat()
                currentBottomSignUpX = dWidth - currentBottomX
            }

            if (currentAngle == PI.toFloat() / 2) {
                currentX = currentRight
                currentBottomX = currentRight
                currentY = 0f
                currentBottomY = dHeight.toFloat()
            }

            if (isLogin) {
                loginButtonPath.reset()
                loginButtonPath.moveTo(0f, buttonBottom.toFloat())
                loginButtonPath.lineTo(currentRight, buttonBottom.toFloat())
                loginButtonPath.lineTo(currentRight, buttonTop.toFloat())

                loginButtonPath.lineTo(currentX, currentY)
                loginButtonPath.lineTo(0f, currentY)

                // bottom reveal
                loginButtonPath.lineTo(0f, currentBottomY)
                loginButtonPath.lineTo(currentBottomX, currentBottomY)
                loginButtonPath.lineTo(currentRight, buttonBottom.toFloat())
            } else {
                signUpButtonPath.reset()
                signUpButtonPath.moveTo(dWidth.toFloat(), buttonBottom.toFloat())
                signUpButtonPath.lineTo(currentLeft, buttonBottom.toFloat())
                signUpButtonPath.lineTo(currentLeft, buttonTop.toFloat())

                signUpButtonPath.lineTo(currentSignUpX, currentY)
                signUpButtonPath.lineTo(dWidth.toFloat(), currentY)

                // bottom reveal
                signUpButtonPath.lineTo(dWidth.toFloat(), currentBottomY)
                signUpButtonPath.lineTo(currentBottomSignUpX, currentBottomY)
                signUpButtonPath.lineTo(currentLeft, buttonBottom.toFloat())
            }

            currentX = 0f
            currentSignUpX = dWidth.toFloat()
            currentBottomX = 0f
            currentBottomSignUpX = dWidth.toFloat()
            this@LoginButton.invalidate()
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                orPaint!!.alpha = 125
                signUpPaint!!.alpha = 255
                signUpPaint!!.textSize = dpToPixels(16)
                currentArcX = 0f
                currentArcY = 0

                currentRight = startButtonRight.toInt().toFloat()
                currentLeft = (dWidth - startButtonRight.toInt()).toFloat()

                isLogin = !isLogin

                if (isLogin) {
                    currentLoginX = startLoginX
                    currentLoginY = startLoginY
                    signInPaint!!.alpha = 255
                    signInPaint!!.textSize = dpToPixels(16)
                    signUpPaint!!.alpha = 255
                    signUpPaint!!.textSize = dpToPixels(34)
                }

                currentSignUpTextX = startSignUpTextX
                currentSignUpTextY = startSignUpTextY

                val hideButton = startRight + buttonHeight / 2
                if (!isLogin) {
                    currentLeft += hideButton.toFloat()
                } else {
                    currentRight -= hideButton.toFloat()
                }


                //move texts
                if (!isLogin) {
                    signUpOrX += hideButton.toFloat()
                    currentSignUpTextX += hideButton.toFloat()
                } else {
                    loginOrX -= hideButton.toFloat()
                    currentLoginX -= hideButton.toFloat()
                }
                val hiddenButtonLeft = currentLeft
                val hiddenButtonRight = currentRight
                val endSignUpOrX = signUpOrX
                val endSignUpTextX = currentSignUpTextX
                val endLoginOrX = loginOrX
                val endLoginTextX = currentLoginX

                // reset paths
                signUpButtonPath.reset()
                signUpButtonPath.moveTo(dWidth.toFloat(), buttonBottom.toFloat())
                signUpButtonPath.lineTo(currentLeft, buttonBottom.toFloat())
                signUpButtonPath.lineTo(currentLeft, buttonTop.toFloat())
                signUpButtonPath.lineTo(dWidth.toFloat(), buttonTop.toFloat())
                signUpButtonPath.close()

                loginButtonPath.reset()
                loginButtonPath.moveTo(0f, buttonBottom.toFloat())
                loginButtonPath.lineTo(currentRight, buttonBottom.toFloat())
                loginButtonPath.lineTo(currentRight, buttonTop.toFloat())
                loginButtonPath.lineTo(0f, buttonTop.toFloat())
                loginButtonPath.close()

                callback!!.onButtonSwitched(isLogin)

                val buttonBounce = ObjectAnimator.ofInt(0, hideButton).setDuration(500)
                buttonBounce.startDelay = 300
                buttonBounce.interpolator = BounceInterpolator(.2, 7.0)
                buttonBounce.addUpdateListener { a ->
                    val v = a.animatedValue as Int

                    if (!isLogin) {
                        currentLeft = hiddenButtonLeft - v

                        signUpOrX = endSignUpOrX - v
                        currentSignUpTextX = endSignUpTextX - v

                        signUpButtonPath.reset()
                        signUpButtonPath.moveTo(dWidth.toFloat(), buttonBottom.toFloat())
                        signUpButtonPath.lineTo(currentLeft, buttonBottom.toFloat())
                        signUpButtonPath.lineTo(currentLeft, buttonTop.toFloat())
                        signUpButtonPath.lineTo(dWidth.toFloat(), buttonTop.toFloat())
                        signUpButtonPath.close()
                    } else {
                        currentRight = hiddenButtonRight + v

                        loginOrX = endLoginOrX + v
                        currentLoginX = endLoginTextX + v

                        loginButtonPath.reset()
                        loginButtonPath.moveTo(0f, buttonBottom.toFloat())
                        loginButtonPath.lineTo(currentRight, buttonBottom.toFloat())
                        loginButtonPath.lineTo(currentRight, buttonTop.toFloat())
                        loginButtonPath.lineTo(0f, buttonTop.toFloat())
                        loginButtonPath.close()
                    }
                    invalidate()
                }
                buttonBounce.start()

            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })

        animator.start()
    }

    private fun dpToPixels(dp: Int): Float {
        return resources.displayMetrics.density * dp
    }

    fun setOnButtonSwitched(callback: OnButtonSwitchedListener) {
        this.callback = callback
    }

    override fun setOnClickListener(l: View.OnClickListener?) {
        setOnTouchListener { v, event ->
            val x = event.x.toInt()
            val y = event.y.toInt()
            if (isLogin && loginButtonOutline!!.contains(x, y)) {
                if (event.action == ACTION_UP) {
                    l!!.onClick(v)
                }
                true
            } else if (!isLogin && loginTextOutline!!.contains(x, y)) {
                if (event.action == ACTION_UP) {
                    onSignInListener!!.signIn()
                }
                true
            } else if (isLogin && signUpTextOutline!!.contains(x, y)) {
                if (event.action == ACTION_UP) {
                    onSignUpListener!!.signUp()
                }
                true
            } else {
                if (!isLogin && signUpButtonOutline!!.contains(x, y)) {
                    if (event.action == ACTION_UP) {
                        l!!.onClick(v)
                    }
                    true
                } else {
                    false
                }
            }
        }
    }

    fun setOnSignUpListener(listener: OnSignUpListener) {
        onSignUpListener = listener
    }

    fun setOnLoginListener(listener: OnSignInListener) {
        onSignInListener = listener
    }

    private fun getString(@StringRes stringId: Int): String {
        return context.getString(stringId)
    }

}
