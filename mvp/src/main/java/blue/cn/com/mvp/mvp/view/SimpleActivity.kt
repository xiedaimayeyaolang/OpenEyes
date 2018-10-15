package blue.cn.com.mvp.mvp.view

import android.os.Bundle
import blue.cn.com.mvp.R
import blue.cn.com.mvp.util.TransitionMode
import com.alibaba.android.arouter.launcher.ARouter

abstract class SimpleActivity : MvpActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDatas()
        setupUI()
        events()
    }

}