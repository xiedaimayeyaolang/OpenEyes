package blue.cn.com.openeyes.di

import blue.cn.com.openeyes.activity.main.view.MainActivity
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by heping on 2018/5/20.
 * MainActivity 追加 Subcomponent
 */
@Singleton
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mianActivity: MainActivity)
}

