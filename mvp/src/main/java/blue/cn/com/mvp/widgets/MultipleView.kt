package blue.cn.com.mvp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import blue.cn.com.mvp.R

class MultipleView : RelativeLayout{
    private var mEmptyView: View? = null
    private var mNetErrorView: View? = null
    private var mLoadingView: View? = null
    private var mContentViews: MutableList<View> = mutableListOf()
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        super.addView(child, params)
        child?.let {
            if (child.tag != MutipleState.LOADING && child.tag != MutipleState.EMPTY && child.tag != MutipleState.NET_ERROR) {
                mContentViews.add(child)
            }
        }
    }
    fun showLoading() {
        switchContent(MutipleState.LOADING)
    }


    fun showEmpty(onClickListener: OnClickListener) {
        switchContent(MutipleState.EMPTY, onClickListener)
    }

    fun showNetError(onClickListener: OnClickListener) {
        switchContent(MutipleState.NET_ERROR, onClickListener)
    }

    fun showContent() {
        switchContent(MutipleState.CONTENT)
    }

    /**
     * 设置空数据view
     */
    private fun setEmptyView(onClickListener: OnClickListener) {
        if (mEmptyView == null) {
            mEmptyView = LayoutInflater.from(context).inflate(R.layout.layout_loading_message, null)
            mEmptyView?.tag = MutipleState.EMPTY
            val imageView = mEmptyView?.findViewById<ImageView>(R.id.iv_image)
            val errorText = mEmptyView?.findViewById<TextView>(R.id.tv_message_info)
            imageView?.setImageResource(R.drawable.ic_eye_black_error)
            errorText?.setText(R.string.empty_message)
            mEmptyView?.setOnClickListener(onClickListener)
            addStateView(mEmptyView)
        } else {
            mEmptyView?.visibility = View.VISIBLE
        }
    }

    /**
     * 显示空数据View
     */
    private fun showEmptyView(onClickListener: OnClickListener) {
        setEmptyView(onClickListener)
        hideLoadingView()
        hideNetErrorView()
        setContentViewVisible(false)
    }


    /**
     * 隐藏空数据view
     */
    private fun hideEmptyView() {
        mEmptyView?.let { it.visibility = View.GONE }
    }


    /**
     * 设置网络异常view
     */
    private fun setNetErrorView(onClickListener: OnClickListener) {
        if (mNetErrorView == null) {
            mNetErrorView = LayoutInflater.from(context).inflate(R.layout.layout_loading_message, null)
            mNetErrorView?.tag = MutipleState.NET_ERROR
            val imageView = mNetErrorView?.findViewById<ImageView>(R.id.iv_image)
            val errorText = mNetErrorView?.findViewById<TextView>(R.id.tv_message_info)
            imageView?.setImageResource(R.drawable.ic_eye_black_error)
            errorText?.setText(R.string.net_error_message)
            mNetErrorView?.setOnClickListener(onClickListener)
            addStateView(mNetErrorView)
        } else {
            mNetErrorView?.visibility = View.VISIBLE
        }
    }

    /**
     * 显示网络异常view
     */
    private fun showNetErrorView(onClickListener: OnClickListener) {
        setNetErrorView(onClickListener)
        hideLoadingView()
        hideEmptyView()
        setContentViewVisible(false)
    }

    /**
     * 隐藏网络异常view
     */
    private fun hideNetErrorView() {
        mNetErrorView?.let { it.visibility = View.GONE }
    }

    /**
     * 设置加载中view
     */
    private fun setLoadingView(onClickListener: OnClickListener) {
        if (mLoadingView == null) {
            mLoadingView = NetLoadingView(context)
            mLoadingView?.tag = MutipleState.LOADING
            mLoadingView?.setOnClickListener(onClickListener)
            addStateView(mLoadingView)
        } else {
            mLoadingView?.visibility = View.VISIBLE
        }
    }

    /**
     * 显示加载view
     */
    private fun showLoadingView(onClickListener: OnClickListener) {
        setLoadingView(onClickListener)
        hideEmptyView()
        hideNetErrorView()
        setContentViewVisible(false)
    }

    /**
     * 隐藏加载view
     */
    private fun hideLoadingView() {
        mLoadingView?.let { it.visibility = View.GONE }
    }


    /**
     * 设置内容界面是否显示
     */
    private fun setContentViewVisible(isVisible: Boolean) {
        for (mContentView in mContentViews) {
            mContentView.visibility = if (isVisible) View.VISIBLE else View.GONE
        }

    }

    /**
     * 显示内容视图
     */
    private fun showContentView() {
        hideEmptyView()
        hideNetErrorView()
        hideLoadingView()
        setContentViewVisible(true)
    }

    /**
     * 添加状态布局
     */
    private fun addStateView(view: View?) {
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.addRule(CENTER_IN_PARENT)
        addView(view, layoutParams)
    }

    /**
     * 切换布局
     */
    private fun switchContent(state: MutipleState, onClickListener: OnClickListener = OnClickListener { }) {
        when (state) {
            MutipleState.EMPTY -> showEmptyView(onClickListener)
            MutipleState.LOADING -> showLoadingView(onClickListener)
            MutipleState.NET_ERROR -> showNetErrorView(onClickListener)
            MutipleState.CONTENT -> showContentView()
        }
    }

}