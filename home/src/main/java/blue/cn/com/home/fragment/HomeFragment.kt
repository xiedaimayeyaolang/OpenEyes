package blue.cn.com.openeyes.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import blue.cn.com.arouter.R
import blue.cn.com.mvp.util.RouterMap
import blue.cn.com.mvp.util.bindView
import blue.cn.com.mvp.util.onClick
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle2.components.support.RxFragment
import org.jetbrains.anko.find


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TextFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TextFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
@Route(path = RouterMap.HOME_FRAGMENT)
class HomeFragment : RxFragment() {
//    val fragment_text : TextView by bindView(R.id.fragment_text)
    @Autowired(name = "value")
    @JvmField var name: String? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val bundle = arguments
        param1 = bundle!!.getString(ARG_PARAM1)
        Log.e("heping","$param1 onAttach")
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.homefragment_text, container, false)
        view.find<TextView>(R.id.fragment_text).apply {
            text = name
            onClick {
                ARouter.getInstance().build(RouterMap.MAIN_ACTIVITY)
                        .withString("name","this is intent from HomeFramgnet")
                        .navigation(this@HomeFragment.activity,100)
            }
        }
        Log.e("heping","$param1 onCreateView")
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        Log.e("heping","$param1 onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("heping","$param1 onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.e("heping","$param1 onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("heping","$param1 onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("heping","$param1 onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("heping","$param1 onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("heping","$param1 onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("heping","$param1 onDetach")
    }

    /**
     * 事物 show、hint调用生命周期
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("onHiddenChanged","$param1 onHiddenChanged $hidden")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("setUserVisibleHint","$param1 setUserVisibleHint $isVisibleToUser")
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TextFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
