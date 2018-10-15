package blue.cn.com.openeyes.activity.main.view

import android.app.Activity
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import blue.cn.com.mvp.BaseApp
import blue.cn.com.mvp.di.module.NetModule
import blue.cn.com.mvp.mvp.view.BaseActivity
import blue.cn.com.mvp.util.RouterMap
import blue.cn.com.mvp.util.bindView
import blue.cn.com.mvp.util.toast
import blue.cn.com.openeyes.R
import blue.cn.com.openeyes.Test
import blue.cn.com.openeyes.activity.main.contract.IMainContract
import blue.cn.com.openeyes.activity.main.presenter.MainPresenter
import blue.cn.com.openeyes.di.DaggerActivityComponent
//import blue.cn.com.openeyes.di.DaggerActivityComponent
import blue.cn.com.openeyes.di.MainModule
import blue.cn.com.openeyes.fragment.TextFragment
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import org.jetbrains.anko.find
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), IMainContract.View, TabLayout.OnTabSelectedListener {


    private val main_tab: TabLayout by bindView(R.id.main_tab)
    private val tab_main_center : ImageButton by bindView(R.id.tab_main_center)
    private var tabSelectedPosition : Int = 0
    private val tabTexts = listOf("首页", "关注", "", "通知", "我的")
    private val tabIcons = listOf(R.drawable.main_tab_home,
            R.drawable.main_tab_follow,
            R.drawable.ic_main_dot,
            R.drawable.main_tab_notification,
            R.drawable.main_tab_profile)
   private lateinit var fragments: MutableList<Fragment>
   private var currentFragment: Fragment? = null

    //    val text : TextView by bindView(R.id.text)
    override fun onTestClick() {
//        text.text = "点击了"
    }

    @Inject
    lateinit var test: Test

    override fun <T> bindToLife(): LifecycleTransformer<T> = bindUntilEvent(ActivityEvent.DESTROY)

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initDatas() {
//        Log.e("heping",test.toString())
//        text.onClick { mPresenter.test() }
        fragments = mutableListOf()
        fragments.add(getHomeFragment())

    }
    private fun getHomeFragment() : Fragment{
        return ARouter.getInstance().build(RouterMap.HOME_FRAGMENT).withString("value","首页").navigation() as Fragment
    }
    override fun setupUI() {

        for (i in tabIcons.indices) {
            val tab = main_tab.newTab()
            val view = layoutInflater.inflate(R.layout.tab_custom, null)
            tab.customView = view
            val tab_text = view.find<TextView>(R.id.tv_tab)
            if (tabTexts[i].isEmpty()) {
                tab_text.visibility = View.GONE
            } else {
                view.find<TextView>(R.id.tv_tab).text = tabTexts[i]
            }
            view.find<ImageView>(R.id.img_tab).setImageResource(tabIcons[i])
            tab.tag = tabTexts[i]
            main_tab.addTab(tab)
            fragments.add(TextFragment.newInstance(tabTexts[i], ""))
        }
        switchFragment(0)
    }

    override fun doNetWork() {
        mPresenter.test()
    }

    override fun inject(){
        DaggerActivityComponent.builder()
                .appComponent(BaseApp.appComponent)
                .netModule(NetModule(this))
                .build().plus(MainModule(this)).inject(this)
    }
//    =
//            DaggerActivityComponent
//                    .builder()
//                    .appComponent(BaseApp.appComponent)
//                    .netModule(NetModule(this))
//                    .build().plus(MainModule(this)).inject(this)

    override fun events() {
        main_tab.addOnTabSelectedListener(this)
//        tab_main_center.setOnClickListener { startActivity<TempActivity>() }
        tab_main_center.setOnClickListener {
            ARouter.getInstance().build(RouterMap.MAIN_ACTIVITY)
                    .withString("name","this is value")
                    .navigation(this,100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                100 ->{
                    if (data != null) {
                        toast(data.getStringExtra("result"))
                    }
                }
                else -> {

                }
            }
        }


    }
    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        if (tab.position == 2) {
            main_tab.getTabAt(tabSelectedPosition)?.select()
            return
        }else{
            tab?.let {
                tabSelectedPosition = it.position
            }
        }
        switchFragment(tab.position)
    }
    private fun switchFragment(position: Int) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = fragments[position]
        if (currentFragment == null) {
            transaction.add(R.id.main_container, fragment).commit()
            currentFragment = fragment
            return
        }
        // 先判断是否被add过
        if (!fragment.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(currentFragment)
                    .add(R.id.main_container, fragment).commit()
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(currentFragment).show(fragment)
                    .commit()
        }
        currentFragment = fragment
    }
    override fun onDestroy() {
        Log.e("heping","onDestory")
        super.onDestroy()
    }
}
