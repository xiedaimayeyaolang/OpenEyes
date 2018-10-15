package blue.cn.com.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.SupportActivity
import blue.cn.com.arouter.R

@SuppressLint("RestrictedApi")
class HomeActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
