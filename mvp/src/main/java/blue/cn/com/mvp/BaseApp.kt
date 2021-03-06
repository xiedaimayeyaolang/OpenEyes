package blue.cn.com.mvp

//import blue.cn.com.mvp.di.component.AppComponent
import android.app.Activity
import android.content.res.Resources
import android.support.multidex.MultiDexApplication
import blue.cn.com.mvp.di.component.AppComponent
import blue.cn.com.mvp.di.component.DaggerAppComponent
import blue.cn.com.mvp.di.module.AppModule
import blue.cn.com.mvp.util.Contants
import blue.cn.com.mvp.util.SpUtil
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import io.realm.Realm
import io.realm.RealmConfiguration
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.external.ExternalAdaptManager
import me.jessyan.autosize.internal.CustomAdapt
import me.jessyan.autosize.utils.LogUtils.isDebug

open class BaseApp : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(instance)).build()

        Utils.init(this)
//        LogUtils.getConfig().setGlobalTag("")
        SpUtil.init(this)
        initFresco()
        initAutoSize()
        initArouter()
    }
    private fun initFresco() {
        val config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, config)
    }
    open fun initArouter(){
        if (isDebug()) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog()     // Print log
            ARouter.openDebug()   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(instance)
    }
    /**
     * 初始化initAutoSize ，manifest文件中修改设计稿尺寸
     */
    private fun initAutoSize(){
        /**
         * 以下是 AndroidAutoSize 可以自定义的参数, {@link AutoSizeConfig} 的每个方法的注释都写的很详细
         * 使用前请一定记得跳进源码，查看方法的注释, 下面的注释只是简单描述!!!
         */
        AutoSizeConfig.getInstance()

                //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
                //如果没有这个需求建议不开启
                .setCustomFragment(true)//是否打印 AutoSize 的内部日志, 默认为 true, 如果您不想 AutoSize 打印日志, 则请设置为 false
        //                .setLog(false)
        //是否使用设备的实际尺寸做适配, 默认为 false, 如果设置为 false, 在以屏幕高度为基准进行适配时
        //AutoSize 会将屏幕总高度减去状态栏高度来做适配, 如果设备上有导航栏还会减去导航栏的高度
        //设置为 true 则使用设备的实际屏幕高度, 不会减去状态栏以及导航栏高度
        //                .setUseDeviceSize(true)
        //是否全局按照宽度进行等比例适配, 默认为 true, 如果设置为 false, AutoSize 会全局按照高度进行适配
        //                .setBaseOnWidth(false)
        //设置屏幕适配逻辑策略类, 一般不用设置, 使用框架默认的就好
        //                .setAutoAdaptStrategy(new AutoAdaptStrategy())
        //当 App 中出现多进程, 并且您需要适配所有的进程, 就需要在 App 初始化时调用 initCompatMultiProcess()
        //在 Demo 中跳转的三方库中的 DefaultErrorActivity 就是在另外一个进程中, 所以要想适配这个 Activity 就需要调用 initCompatMultiProcess()
//        AutoSize.initCompatMultiProcess(this)
//        customAdaptForExternal()
    }

    /**
     * 给外部的三方库 [Activity] 自定义适配参数, 因为三方库的 [Activity] 并不能通过实现
     * [CustomAdapt] 接口的方式来提供自定义适配参数 (因为远程依赖改不了源码)
     * 所以使用 [ExternalAdaptManager] 来替代实现接口的方式, 来提供自定义适配参数
     */
//    private fun customAdaptForExternal() {
        /**
         * [ExternalAdaptManager] 是一个管理外部三方库的适配信息和状态的管理类, 详细介绍请看 [ExternalAdaptManager] 的类注释
         */
//        AutoSizeConfig.getInstance().externalAdaptManager

                //加入的 Activity 将会放弃屏幕适配, 一般用于三方库的 Activity, 详情请看方法注释
                //如果不想放弃三方库页面的适配, 请用 addExternalAdaptInfoOfActivity 方法, 建议对三方库页面进行适配, 让自己的 App 更完美一点
                //                .addCancelAdaptOfActivity(DefaultErrorActivity.class)

                //为指定的 Activity 提供自定义适配参数, AndroidAutoSize 将会按照提供的适配参数进行适配, 详情请看方法注释
                //一般用于三方库的 Activity, 因为三方库的设计图尺寸可能和项目自身的设计图尺寸不一致, 所以要想完美适配三方库的页面
                //就需要提供三方库的设计图尺寸, 以及适配的方向 (以宽为基准还是高为基准?)
                //三方库页面的设计图尺寸可能无法获知, 所以如果想让三方库的适配效果达到最好, 只有靠不断的尝试
                //由于 AndroidAutoSize 可以让布局在所有设备上都等比例缩放, 所以只要您在一个设备上测试出了一个最完美的设计图尺寸
                //那这个三方库页面在其他设备上也会呈现出同样的适配效果, 等比例缩放, 所以也就完成了三方库页面的屏幕适配
                //即使在不改三方库源码的情况下也可以完美适配三方库的页面, 这就是 AndroidAutoSize 的优势
                //但前提是三方库页面的布局使用的是 dp 和 sp, 如果布局全部使用的 px, 那 AndroidAutoSize 也将无能为力
                //经过测试 DefaultErrorActivity 的设计图宽度在 380dp - 400dp 显示效果都是比较舒服的
//                .addExternalAdaptInfoOfActivity(DefaultErrorActivity::class.java!!, ExternalAdaptInfo(true, 400f))
//    }
    companion object {
        lateinit var instance: BaseApp
            private set
        lateinit var appComponent: AppComponent
//            private set
        fun getRealm(): Realm {
            return Realm.getInstance(provideRealmNormalConfig())
        }
        /**
         * 获取资源文件访问对象
         */
        @JvmStatic
        fun getResource(): Resources {
            return instance.resources
        }
        private fun provideRealmNormalConfig(): RealmConfiguration {
            return RealmConfiguration.Builder()
                    .name(Contants.REALM_NAME) //文件名
                    .schemaVersion(Contants.REALM_VERSION) //版本号
                    .deleteRealmIfMigrationNeeded()
                    .build()
        }
    }
}