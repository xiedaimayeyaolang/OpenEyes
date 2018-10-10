package blue.cn.com.openeyes.di

import blue.cn.com.mvp.di.component.AppComponent
import blue.cn.com.mvp.di.module.NetModule
import com.blueooo.miao.base.di.scope.ActivityScope
import dagger.Component


/**
 * dagger activity Compenent
 * Created by heping on 2018/5/19.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(NetModule::class))
interface ActivityComponent {
    fun plus(module: MainModule): MainComponent
}
