package com.example.supertraining.view.dest

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentRetrofitTestBinding
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.db.network_db.sample.dataholder.User
import com.example.supertraining.db.network_db.thewalker.dataholder.request.*
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.toastShortShow
import com.example.supertraining.viewmodel.NetworkViewModel
import com.example.supertraining.viewmodel.TheWalkerViewModel
import com.example.supertraining.viewmodel.factory.ViewModelFactory
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import android.view.View as View

class RetroFitTestFragment :
    BaseFragment<FragmentRetrofitTestBinding>(R.layout.fragment_retrofit_test) {

    private val networkViewModel by viewModels<NetworkViewModel> {
        val database = TestDataBase.getInstance(requireContext())
        val factory = ViewModelFactory(database.dataSource)
        factory
    }
    private val theWalkerViewModel by viewModels<TheWalkerViewModel> {
        val database = TestDataBase.getInstance(requireContext())
        val factory = ViewModelFactory(database.dataSource)
        factory
    }

    override fun FragmentRetrofitTestBinding.setDataBind() {

        setData()
        setObserver()
    }

    override fun FragmentRetrofitTestBinding.setClickListener() {}


    private fun FragmentRetrofitTestBinding.setData() {
        thisFragment = this@RetroFitTestFragment
        netWorkVM = networkViewModel
        theWalkerVM = theWalkerViewModel
    }


    private fun FragmentRetrofitTestBinding.setObserver() {
        networkViewModel.userData.observe(viewLifecycleOwner, {
            textViewUserDataCheck.text =
                getString(R.string.retrofitTest_textUserData, it.userId, it.email)
        })
        theWalkerViewModel.userData.observe(viewLifecycleOwner, {
            context?.toastShortShow(it.idToken)
            Log.d("asdfasdf", "${it.signedUp}")
        })

        theWalkerViewModel.walkCourseList.observe(viewLifecycleOwner, {
            Log.d("asdfasdf", it.walkList.size.toString())
        })

        theWalkerViewModel.getMyProfile.observe(viewLifecycleOwner,{
            Log.d("asdfasdf", it.userData._id!!)
        })

        theWalkerViewModel.getNoticeList.observe(viewLifecycleOwner,{
            Log.d("asdfasdf", it.noticeList!!.size.toString())
        })
        theWalkerViewModel.getQuestionList.observe(viewLifecycleOwner,{
            Log.d("asdfasdf", it.questionList!!.size.toString())
        })

        theWalkerViewModel.getSearchList.observe(viewLifecycleOwner,{
            Log.d("asdfasdf", it.walkList.size.toString())
        })

        theWalkerViewModel.getSpotList.observe(viewLifecycleOwner,{
            Log.d("asdfasdf", it.spotList!!.size.toString())
        })
    }

/*
나중에 쓸일있을때 참고
 */
//    val jsonCallback = object : Callback<JsonElement> {
//        override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
//            Log.d("registerCheck", "회원 가입 성공 ")
//        }
//
//        override fun onFailure(call: Call<JsonElement>, t: Throwable) {
//            Log.d("registerCheck", "네트워크 문제로 회원 가입 실패 ")
//        }
//    }

    fun setUserData(): User {
        binding.apply {
            return User(
                editTextIdSignUp.text.toString(),
                editTextPasswordSignUp.text.toString(),
                editTextEmailSignUp.text.toString()
            )
        }
    }

    fun loginTest(v: View) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("TAG", "로그인 실패", error)

            } else if (token != null) {
                 theWalkerViewModel.snsLogin("kakao",
                    Login(
                        null,
                        Push("kakao", token.toString()),
                        "1.5"
                    )
                )
            }
        }

        if (LoginClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            LoginClient.instance.loginWithKakaoTalk(requireContext(), callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    fun setContactData() = ContactAdd("문의사항 테스트","asdasd@asdasd.com","test","테스트")

    fun setKeyWord() = "정동"

    fun setFeedBackData() = FeedBackAdd("1231","4")

    fun setBookMarkData() = BookMarkAdd("34213412")

    fun setScrapData() = ScrapAdd("34213412")

    fun setSpotListSearchId() = "34213412"
}