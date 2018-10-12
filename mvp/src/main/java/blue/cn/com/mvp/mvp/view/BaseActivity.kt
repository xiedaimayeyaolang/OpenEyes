package blue.cn.com.mvp.mvp.view

import android.os.Bundle
import android.view.View
import blue.cn.com.mvp.BaseApp
import blue.cn.com.mvp.R
import blue.cn.com.mvp.di.module.NetModule
import blue.cn.com.mvp.mvp.contract.IBaseContract
import blue.cn.com.mvp.mvp.presenter.BasePresenter
import blue.cn.com.mvp.util.TransitionMode
import blue.cn.com.mvp.widgets.MultipleView
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * Description :
 * @author  heping
 * @date 2018/10/9  下午2:50
 * 								 - generate by MvpAutoCodePlus plugin.
 */

abstract class BaseActivity<P : BasePresenter> : MvpActivity(),IBaseContract.IView{
    @Inject
    lateinit var mPresenter: P
    protected lateinit var mMultipleStateView: MultipleView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(getLayoutId())
        mMultipleStateView = MultipleView(this)
        initDatas()
        setupUI()
        events()
        doNetWork()

    }

    /**
     * 网络请求
     */
    abstract fun doNetWork()

    /**
     * dagger注入
     */
    abstract fun inject()
    override fun showLoading() {
        mMultipleStateView.showLoading()
    }

    override fun showNetError(onClickListener: View.OnClickListener) {
        mMultipleStateView.showNetError(onClickListener)
    }

    override fun showEmpty(onClickListener: View.OnClickListener) {
        mMultipleStateView.showEmpty(onClickListener)
    }

    override fun showContent() {
        mMultipleStateView.showContent()
    }
}

