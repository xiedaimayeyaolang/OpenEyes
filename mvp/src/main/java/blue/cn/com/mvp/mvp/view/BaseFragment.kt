package blue.cn.com.mvp.mvp.view

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import blue.cn.com.mvp.mvp.contract.IBaseContract
import blue.cn.com.mvp.mvp.presenter.BasePresenter
import com.trello.rxlifecycle2.components.RxFragment
import javax.inject.Inject

abstract class BaseFragment<P : BasePresenter> : RxFragment(), IBaseContract.IView {
    val toolBar: Toolbar? = null
    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    var mView: View? = null

    protected var isPrepared: Boolean = false
    protected var isFirst: Boolean = true
    protected var isUserVisible: Boolean = false
//    override val loggerTag: String
//        get() = resources.getString(R.string.anko_tag)
    @Inject
    lateinit var mPresenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(getLayoutId(), null)
        return mView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isUserVisible = true
            onVisible()
            lazyLoad()
        } else {
            isUserVisible = false
            onUnvisible()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden){
            onUnvisible()
        }else{
            onVisible()
        }
    }
    //fragment 不可见
    open fun onUnvisible() {

    }
    open fun onVisible(){

    }
    private fun lazyLoad() {
        if (!isPrepared || !isUserVisible || !isFirst) {
            return
        }

        initDatas()
        setupUI()
        events()
        doNetWork()
        isFirst = false

    }


    /**
     * 生命周期onStar之前执行，网络请求比较好
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true
        inject()
    }


    override fun onResume() {
        super.onResume()
        if (userVisibleHint) {
            userVisibleHint = true
        }
    }


//    inline fun <reified T : View> getView(@IdRes id: Int) : T {
//
//        return mView.find(id)
//    }
    /**
     * 布局layout
     */
    abstract fun getLayoutId(): Int
    //    abstract fun initViews()
    /**
     * 初始化数据
     */
    abstract fun initDatas()

    /**
     * 初始化监听事件
     */
    abstract fun events()

    /**
     * 注入方法 通过getBaseCompenent()获取AppCompatActivity对象，然后调用方法注入
     */
    abstract fun inject()

    open fun doNetWork() {

    }

    open fun setupUI() {

    }

    fun setToolbar(toolBarTitle: String) {
        toolBar?.title = toolBarTitle
    }

    //    abstract fun doNetWork();
    override fun onDestroy() {
        super.onDestroy()
    }
}