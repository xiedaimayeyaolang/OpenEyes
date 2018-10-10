package blue.cn.com.mvp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 提供常用方法 实例
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    internal fun provideAppContext(): Context = app

//    @Provides
//    fun providePd() = CustomPD()
//    @Provides
//    fun providerDbOperator(context: Context) = DbOperator(context)
}