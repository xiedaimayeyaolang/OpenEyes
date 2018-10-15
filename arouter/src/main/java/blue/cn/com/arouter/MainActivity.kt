package blue.cn.com.arouter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import blue.cn.com.mvp.mvp.view.SimpleActivity
import blue.cn.com.mvp.util.RouterMap
import blue.cn.com.mvp.util.bindView
import blue.cn.com.mvp.util.onClick
import blue.cn.com.mvp.util.toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter


@Route(path = RouterMap.MAIN_ACTIVITY)
class MainActivity : SimpleActivity() {
    override fun getLayoutId(): Int  = R.layout.activity_arouter

    override fun initDatas() {
        ARouter.getInstance().inject(this)
        name?.let { toast(it) }
        text.text = name
    }

    override fun setupUI() {
    }

    override fun events() {
        text.onClick {
            val intent =  Intent()
            intent.putExtra("result", "返回了")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    val text : TextView by bindView(R.id.text)

    @Autowired(name = "name")
    @JvmField var name: String? = null

    override fun onDestroy() {
        Log.e("heping","onDestory")
        super.onDestroy()
    }
}
