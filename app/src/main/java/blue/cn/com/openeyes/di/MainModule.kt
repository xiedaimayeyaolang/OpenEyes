package blue.cn.com.openeyes.di

import blue.cn.com.openeyes.Test
import blue.cn.com.openeyes.activity.main.contract.IMainContract
import dagger.Module
import dagger.Provides

/**
 * Created by heping on 2018/5/20.
 * 提供MainView实例
 */
@Module
class MainModule(private val mView: IMainContract.View) {
    @Provides
    fun getView() = mView
    @Provides
    fun getTest(): Test = Test("123")
//    @Provides
//    @Singleton
//    fun provideMainFragments(context: Context): List<Fragment> = listOf(HomeFragment.newInstance(context.resources.getString(R.string.tabhome)),
//            MomentFragment.newInstance(context.resources.getString(R.string.tabmoment)),
//            Fragment(),
//            ConversationFragment.newInstance(context.resources.getString(R.string.tabchat)),
//            MoreFragment.newInstance(context.resources.getString(R.string.tabmore)))

}