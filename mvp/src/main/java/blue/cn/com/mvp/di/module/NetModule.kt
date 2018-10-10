package blue.cn.com.mvp.di.module

import android.content.Context
import blue.cn.com.mvp.net.ApiClient
import blue.cn.com.mvp.net.ApiStores
import blue.cn.com.mvp.net.Interceptors
import blue.cn.com.mvp.net.Url
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import retrofit2.Retrofit

/**
 * Created by heping on 2018/5/20.
 */
@Module
class NetModule(val context: Context) {
    @Provides
    fun provideRetrofit(): Retrofit {
        val apiClient = ApiClient()
        apiClient.b  = provideB()
        apiClient.u = provideU()
//        apiClient.converFactory = provideConverFactory()
        return apiClient.retrofit()
    }
    @Provides
    fun provideB() : Interceptors = object : Interceptors{
        override fun add(): List<Interceptor> {
//            val netWorkInterceptor = NetWorkInterceptor(context)
//            val interceptor = BlueInterceptor(null)
//            return listOf(netWorkInterceptor,interceptor)
            return listOf()
        }
    }
    @Provides
    fun provideU() : Url = object : Url {
        override fun getBaseUrl(): String {
            return "http://app.meeoh.cn/"
        }
    }
    @Provides
    fun provideApi(retrofit: Retrofit): ApiStores {
        return retrofit.create(ApiStores::class.java)
    }
}