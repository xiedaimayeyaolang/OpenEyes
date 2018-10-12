package blue.cn.com.openeyes.activity.splash


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import blue.cn.com.mvp.mvp.view.SimpleActivity
import blue.cn.com.mvp.util.SpUtil
import blue.cn.com.mvp.util.TimeUtils
import blue.cn.com.mvp.util.UIHelper
import blue.cn.com.mvp.util.bindView
import blue.cn.com.mvp.widgets.CustomFontTextView
import blue.cn.com.openeyes.R
import blue.cn.com.openeyes.activity.main.view.MainActivity
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.startActivity
import java.util.*

class SplashActivity : SimpleActivity() {
    private val mIvBackground by bindView<SimpleDraweeView>(R.id.iv_background)
    private val mLoadingContainer by bindView<RelativeLayout>(R.id.rl_loading_container)
    private val mMoveContainer by bindView<RelativeLayout>(R.id.ll_move_container)
    private val mHeadOuter by bindView<ImageView>(R.id.iv_head_outer)
    private val mHeadInner by bindView<ImageView>(R.id.iv_head_inner)
    private val mName by bindView<CustomFontTextView>(R.id.tv_name)

    private val mForToday by bindView<CustomFontTextView>(R.id.tv_today)
    private val mDate by bindView<CustomFontTextView>(R.id.tv_date)
    private val mTodayChose by bindView<CustomFontTextView>(R.id.tv_today_chose)
    override fun getLayoutId(): Int = R.layout.activity_splash
    companion object {
        const val KEY_USER_LOGIN = "USER_LOGIN"
        const val KEY_FIRST_LOGIN = "FIRST_LOGIN"
    }
    override fun initDatas() {
        if (SpUtil.getBoolean(KEY_USER_LOGIN)) {
            doUpAnimator()
            doBackgroundAnimator()
        } else {
            doScaleAnimator()
        }

    }

    override fun setupUI() {
    }

    override fun events() {
//        android.os.Handler(Looper.getMainLooper()).postDelayed({
//            startActivity<MainActivity>()
//        },1500)
    }
    /**
     * 执行上升动画
     */
    private fun doUpAnimator() {
        val moveY = UIHelper.dip2px(this, 100f)
        val upAnimator = ObjectAnimator.ofFloat(mMoveContainer, "translationY", 0f, -moveY.toFloat())
        upAnimator.addUpdateListener {
            if (it.currentPlayTime in 600..1500) {
                mHeadOuter.setImageResource(R.drawable.ic_eye_white_outer)
                mHeadInner.setImageResource(R.drawable.ic_eye_white_inner)
                mName.setTextColor(ContextCompat.getColor(this, R.color.gray_B7B9B8))

            } else if (it.currentPlayTime in 1500..2000) {
                mHeadOuter.setImageResource(R.drawable.ic_eye_black_outer)
                mHeadInner.setImageResource(R.drawable.ic_eye_black_inner)
                mName.setTextColor(ContextCompat.getColor(this, R.color.black_444444))
            }

        }
        upAnimator.duration = 2000
        upAnimator.start()
    }

    /**
     * 执行背景动画
     */
    private fun doBackgroundAnimator() {
        val backgroundAnimator = ValueAnimator.ofArgb(0, 0xffffffff.toInt())
        backgroundAnimator.addUpdateListener {
            mLoadingContainer.setBackgroundColor(it.animatedValue as Int)
        }
        backgroundAnimator.doOnEnd { doTextAnimator() }
        backgroundAnimator.duration = 2000
        backgroundAnimator.start()
    }

    /**
     * 显示 today 文字与精选
     */
    private fun doTextAnimator() {
        val alphaAnimator = ValueAnimator.ofArgb(0, 0xff444444.toInt())
        alphaAnimator.addUpdateListener {
            val color = it.animatedValue as Int
            setTextColor(mForToday, color)
            setTextColor(mDate, color)
            setTextColor(mTodayChose, color)
            mDate.text = TimeUtils.getDateString(Date(), "- yyyy/MM/dd -")
        }
        alphaAnimator.duration = 1000
        alphaAnimator.doOnEnd { doInnerEyeAnimator() }

        alphaAnimator.start()
    }

    private fun setTextColor(textView: TextView, color: Int) {
        textView.visibility = View.VISIBLE
        textView.setTextColor(color)
    }

    /**
     * 执行内部眼睛动画
     */
    private fun doInnerEyeAnimator() {
        val rotationAnimator = ObjectAnimator.ofFloat(mHeadInner, "rotation", 0f, 360f)
        rotationAnimator.doOnEnd {
            startActivity<MainActivity>()
            SpUtil.setBoolean(KEY_USER_LOGIN,true)
            finish()
        }
        rotationAnimator.duration = 1000
        rotationAnimator.start()
    }

    /**
     * 执行背景缩放动画
     */
    private fun doScaleAnimator() {
        val scaleX = ObjectAnimator.ofFloat(mIvBackground, "scaleX", 1f, 1.08f)
        val scaleY = ObjectAnimator.ofFloat(mIvBackground, "scaleY", 1f, 1.08f)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.doOnEnd {
            startActivity<MainActivity>()
            SpUtil.setBoolean(KEY_USER_LOGIN,true)
            finish()
        }
        animatorSet.duration = 2000
        animatorSet.start()
    }
}
