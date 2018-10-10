package blue.cn.com.openeyes.activity.splash


import android.os.Looper
import blue.cn.com.mvp.mvp.view.SimpleActivity
import blue.cn.com.openeyes.R
import blue.cn.com.openeyes.activity.main.view.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : SimpleActivity() {
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initDatas() {
    }

    override fun setupUI() {
    }

    override fun events() {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            startActivity<MainActivity>()
        },1500)
    }

}
