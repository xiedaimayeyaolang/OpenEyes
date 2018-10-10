package blue.cn.com.mvp

import android.annotation.SuppressLint
import android.app.Application
import android.support.multidex.MultiDexApplication
import blue.cn.com.mvp.di.component.AppComponent
import blue.cn.com.mvp.di.component.DaggerAppComponent
import blue.cn.com.mvp.di.module.AppModule
//import blue.cn.com.mvp.di.component.AppComponent
import blue.cn.com.mvp.util.Contants
import io.realm.Realm
import io.realm.RealmConfiguration

class BaseApp : MultiDexApplication(){

    @SuppressLint("MissingSuperCall")
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(instance)).build()
    }
    companion object {
        lateinit var instance: BaseApp
            private set
        lateinit var appComponent: AppComponent
//            private set
        fun getRealm(): Realm {
            return Realm.getInstance(provideRealmNormalConfig())
        }

        fun provideRealmNormalConfig(): RealmConfiguration {
            return RealmConfiguration.Builder()
                    .name(Contants.REALM_NAME) //文件名
                    .schemaVersion(Contants.REALM_VERSION) //版本号
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }
    }
}