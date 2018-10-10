package blue.cn.com.mvp.util

import android.app.Activity
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import blue.cn.com.mvp.mvp.model.BaseModel
import com.trello.rxlifecycle2.LifecycleTransformer
import com.zhy.view.flowlayout.TagFlowLayout
import com.zhy.view.flowlayout.TagView
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

fun android.view.View.onClick(l: (v: android.view.View?) -> Unit) {
    setOnClickListener(l)
}
fun Activity.toast(msg: String = "this is a toast", duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(this,msg,duration).show()
}
fun Fragment.toast(msg: String = "this is a toast", duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(activity,msg,duration).show()
}
fun ImageView.setImg(drawable :  Int ){
    setImageResource(drawable)
    visibility = View.VISIBLE
}
fun TextView.t(string: String){
    text = string
    visibility = View.VISIBLE
}
fun TextView.t(string: String, @DrawableRes backgroundResource : Int ,@ColorRes color : Int){
    text = string
    visibility = View.VISIBLE
    this.setBackgroundResource(backgroundResource)
    this.setTextColor(ContextCompat.getColor(context,color))
}
fun TagFlowLayout.setChildChecked(position : Int,view : TagView){
    view.isChecked = true
    selectedList.add(position)
    adapter.onSelected(position, view.tagView)
}
fun String.fliter(predicate : (Char) -> Boolean) : String{
    val sb = StringBuilder()
    for (index in 0 until length){
        val element = get(index)
        if (predicate(element)) sb.append(element)//相当于element in 'a'..'z'
    }
    return sb.toString()
}
public fun test(){
    "a1bc".fliter { it -> it in 'a'..'z' }
    processTheAnswer { it + 1 }
    processTheAnswer { number -> number + 1 }
}
fun processTheAnswer(f : (Int) -> Int){
    print(f(42))
}
//fun <T : BaseModel> Observable<T>.mSubscribe(
//        onError : (String) -> Unit
//        , onSuccess: (T) -> Unit) {
//    this.compose(Rx.rxSchedulerHelper())
//            .subscribeWith(object : BlueSubscriber<T>(){
//                override fun onErrorT(error: String) {
//                    onError(error)
//                }
//                override fun onNextT(value: T) {
//                    onSuccess(value)
//                }
//            })
//}
fun <T : BaseModel> Observable<T>.mSubscribe(
        subscriber : DisposableObserver<T>,lifecycle : LifecycleTransformer<T>) {
    this.compose(Rx.rxSchedulerHelper())
            .compose(lifecycle)
            .subscribeWith(subscriber)
}

fun <T : BaseModel> Observable<T>.mSubscribeWith(
        subscriber : DisposableObserver<T>,lifecycle : LifecycleTransformer<T>) {
    this.compose(Rx.rxSchedulerHelper())
            .compose(lifecycle)
            .subscribeWith(subscriber)
}