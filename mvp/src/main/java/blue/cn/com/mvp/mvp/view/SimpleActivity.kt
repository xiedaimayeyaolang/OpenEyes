package blue.cn.com.mvp.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class SimpleActivity : AppCompatActivity(){
    abstract fun getLayoutId() : Int
    abstract fun initDatas()
    abstract fun setupUI()
    abstract fun events()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDatas()
        setupUI()
        events()
    }
}