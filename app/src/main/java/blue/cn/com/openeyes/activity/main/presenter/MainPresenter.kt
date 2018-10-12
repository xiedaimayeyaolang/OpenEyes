package blue.cn.com.openeyes.activity.main.presenter

import android.view.View
import blue.cn.com.mvp.mvp.presenter.BasePresenter
import blue.cn.com.mvp.net.AndyInfo
import blue.cn.com.mvp.util.doSubscribe
import blue.cn.com.openeyes.activity.main.contract.IMainContract
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Description :
 * @author  heping
 * @date 2018/10/9  下午5:19
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class MainPresenter @Inject constructor(private val mView : IMainContract.View) : BasePresenter(),IMainContract.Presenter{
    override fun test() {
//        mView.onTestClick()
        mView.showLoading()
        apiStores.getHomeInfo().doSubscribe(object : DisposableObserver<AndyInfo>() {
            override fun onComplete() {

            }

            override fun onNext(t: AndyInfo) {
//                mView.showContent()
            }

            override fun onError(e: Throwable) {
//                mView.showNetError(View.OnClickListener { test() })
            }

        },mView.bindToLife())
    }
}

