package blue.cn.com.openeyes.activity.main.contract

import blue.cn.com.mvp.mvp.contract.IBaseContract

/**
 * Description :
 * @author  heping
 * @date 2018/10/9  下午5:19
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IMainContract {
    interface View : IBaseContract.IView {
        fun onTestClick()
    }
    interface Presenter{
       fun test()
    }
}
