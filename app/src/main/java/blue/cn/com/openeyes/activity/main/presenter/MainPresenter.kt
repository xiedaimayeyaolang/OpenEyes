package blue.cn.com.openeyes.activity.main.presenter

import blue.cn.com.mvp.mvp.presenter.BasePresenter
import blue.cn.com.openeyes.activity.main.contract.IMainContract
import javax.inject.Inject

/**
 * Description :
 * @author  heping
 * @date 2018/10/9  下午5:19
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class MainPresenter @Inject constructor(private val mView : IMainContract.View) : BasePresenter(),IMainContract.Presenter{
    override fun test() {
        mView.onTestClick()
    }
}

