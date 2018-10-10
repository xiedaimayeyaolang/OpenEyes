package blue.cn.com.mvp.di.component

import android.content.Context
import blue.cn.com.mvp.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * AppComponent
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent : AppComponentProvides

interface AppComponentProvides {
    fun appContext(): Context
}