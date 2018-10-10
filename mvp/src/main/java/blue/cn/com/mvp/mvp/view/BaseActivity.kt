package blue.cn.com.mvp.mvp.view

import android.os.Bundle
import blue.cn.com.mvp.BaseApp
import blue.cn.com.mvp.di.module.NetModule
import blue.cn.com.mvp.mvp.contract.IBaseContract
import blue.cn.com.mvp.mvp.presenter.BasePresenter
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

abstract class BaseActivity<P : BasePresenter> : RxAppCompatActivity(),IBaseContract.IView{
    @Inject
    lateinit var mPresenter: P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(getLayoutId())
        initDatas()
        setupUI()
        events()
        doNetWork()
    }
    abstract fun getLayoutId() : Int
    abstract fun initDatas()
    abstract fun setupUI()
    abstract fun doNetWork()
    abstract fun inject()
    abstract fun events()
}

