package com.example.supertraining.view.dest

import android.content.Intent
import android.util.Log
import android.view.View
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import org.json.JSONObject
import java.util.*


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

        theWalkerViewModel.getMyProfile.observe(viewLifecycleOwner, {
            Log.d("asdfasdf", it.userData._id!!)
        })

        theWalkerViewModel.getNoticeList.observe(viewLifecycleOwner, {
            Log.d("asdfasdf", it.noticeList!!.size.toString())
        })
        theWalkerViewModel.getQuestionList.observe(viewLifecycleOwner, {
            Log.d("asdfasdf", it.questionList!!.size.toString())
        })

        theWalkerViewModel.getSearchList.observe(viewLifecycleOwner, {
            Log.d("asdfasdf", it.walkList.size.toString())
        })

        theWalkerViewModel.getSpotList.observe(viewLifecycleOwner, {
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
        val callback: (OAuthToken?, Throwable?) -> Unit = { oauth, error ->
            if (error != null) {
                Log.e("TAG", "로그인 실패", error)

            } else if (oauth != null) {

//                val dataHashMap = hashMapOf<String, Any>()
//                val push = Push("kakao", oauth.accessToken)
//                val oauthClass = OAuth(oauth.accessToken,1599019277,3600)
//
//
//                val gson = Gson()
//                val json = gson.toJson(oauth)
//
//                val body = JsonParser.parseString(json.toString()) as JSONObject
//
//                dataHashMap["push"] = push
//                dataHashMap["oauth"] = json
//                dataHashMap["version"] = "1.5"

                Log.d("acToken", oauth.accessToken)
                Log.d("actimeToken", oauth.accessTokenExpiresAt.time.toString())
                Log.d("reToken", oauth.refreshToken)
                Log.d("reTimeToken", oauth.refreshTokenExpiresAt?.time.toString())

//                theWalkerViewModel.snsLogin(
//                    "kakao",
//                    Login(
//                        "kakao", OAuthKaKao(
//                            oauth.accessToken,
//                            oauth.accessTokenExpiresAt.time,
//                            oauth.refreshToken,
//                            oauth.refreshTokenExpiresAt!!.time,
//                            oauth.scopes
//                        )
//                    )
//                )

            }

        }

        if (LoginClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            LoginClient.instance.loginWithKakaoTalk(requireContext(), callback = callback)
        } else {
            LoginClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    fun setGoogleLoginTest(v: View) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())

        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun setContactData() = ContactAdd("문의사항 테스트", "asdasd@asdasd.com", "test", "테스트")

    fun setKeyWord() = "정동"

    fun setFeedBackData() = FeedBackAdd("1231", "4")

    fun setBookMarkData() = BookMarkAdd("34213412")

    fun setScrapData() = ScrapAdd("34213412")

    fun setSpotListSearchId() = "34213412"

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val dataHashMap = hashMapOf<String, Any>()
            val push = Push("kakao", account?.idToken)


//            completedTask.addOnSuccessListener {
//                dataHashMap["push"] = push
//                dataHashMap["oauth"] = it.idToken!!
//                    dataHashMap["version"] = "1.5"
//                theWalkerViewModel.snsLogin(
//                    "kakao",
//                    dataHashMap
//                )
//            }

        } catch (e: ApiException) {

        }
    }

    companion object {
        const val RC_SIGN_IN = 5555
    }
}