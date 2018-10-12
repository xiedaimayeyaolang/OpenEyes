package blue.cn.com.mvp.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import blue.cn.com.mvp.R
import blue.cn.com.mvp.util.bindView


/**
 * Author:  andy.xwt
 * Date:    2018/2/11 17:44
 * Description:网络加载view
 */

class NetLoadingView : FrameLayout {

    private val mHeadInner by bindView<ImageView>(R.id.iv_head_inner)
    private lateinit var mRotationAnimator: ObjectAnimator

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_loading_view, this)
        doInnerEyeAnimator()
    }


    /**
     * 执行内部眼睛动画
     */
    private fun doInnerEyeAnimator() {
        mRotationAnimator = ObjectAnimator.ofFloat(mHeadInner, "rotation", 0f, 360f)
        mRotationAnimator.duration = 1000
        mRotationAnimator.repeatCount = -1
        mRotationAnimator.start()
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mRotationAnimator?.cancel()

    }

}