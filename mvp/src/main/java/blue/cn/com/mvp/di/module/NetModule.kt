package blue.cn.com.mvp.di.module

import android.content.Context
import blue.cn.com.mvp.net.*
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
            val interceptor = RequestInterceptor()
//            val netWorkInterceptor = NetWorkInterceptor(context)
//            val interceptor = BlueInterceptor(null)
//            return listOf(netWorkInterceptor,interceptor)
            return listOf(interceptor)
        }
    }
    @Provides
    fun provideU() : Url = object : Url {
        override fun getBaseUrl(): String {
            return "http://baobab.kaiyanapp.com/"
        }
    }
    @Provides
    fun provideApi(retrofit: Retrofit): ApiStores {
        return retrofit.create(ApiStores::class.java)
    }
}