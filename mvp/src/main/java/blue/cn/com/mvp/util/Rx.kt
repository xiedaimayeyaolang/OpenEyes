package blue.cn.com.mvp.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.ObservableTransformer



/**
 * Created by heping on 2018/5/20.
 */
object Rx{
    fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {    //compose简化线程
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}