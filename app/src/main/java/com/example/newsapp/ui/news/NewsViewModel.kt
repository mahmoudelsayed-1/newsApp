package com.example.newsapp.ui.news

import android.content.DialogInterface
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.SourcesResponse
import com.example.newsapp.api.model.ApiConstant
import com.example.newsapp.api.model.ApiManager
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.api.model.newsResponse.NewsResponse
import com.example.newsapp.api.model.sourcesResponse.Source
import com.example.newsapp.ui.showDialog
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    // handle logic
    // hold data

    val sourceLiveData = MutableLiveData<List<Source?>?>() // use live data to send data from viewmodel to view
    val showLoading = MutableLiveData<Boolean>()
    val messageDialogLiveData = MutableLiveData<String>()
    val newsLiveData = MutableLiveData<List<News?>?>()



    fun getSources() {
        //ApiManager.getApis().getSources(ApiConstant.apiKey)
        //   .execute()

       showLoading.postValue(true)  // عشان تفضل تلف لغايت م يجيب السيرفر رد
        ApiManager.getApis().getSources(ApiConstant.apiKey)

            .enqueue(object  : retrofit2.Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showLoading.postValue(false)
                    messageDialogLiveData.postValue(t.message)

                }


                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {

                    showLoading.postValue(false)
                    if (response.isSuccessful) {

                        sourceLiveData.postValue(response.body()?.sources)


                    } else {

                        messageDialogLiveData.postValue("Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.")
                    }
                    }
                })
    }

    //private fun getNews(id: String?) {  // call api
//        viewBinding.progressBar.isVisible = true
//        ApiManager.getApis().getNews(ApiConstant.apiKey ,id?: "")
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                    viewBinding.progressBar.isVisible = false
//                    showDialog(message = t.message?:"Something went wrong" // general error
//                        , posActionName = "try again",
//                        posAction = DialogInterface.OnClickListener{dialog: DialogInterface?, which: Int ->
//                            dialog?.dismiss()
//                            getNews(id)
//                        } , NegActionName = "cancel" ,
//                        NegAction = DialogInterface.OnClickListener{dialog: DialogInterface?, which: Int ->
//                            dialog?.dismiss()  // finish Dialog
//                        }
//                    )
//                }
//
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    viewBinding.progressBar.isVisible = false
//                    if (response.isSuccessful) {
//                        // show News
//                        adapter.showNews(response.body()?.articles)  // return data / News / create RecyclerView
//                    } else {
//                        val errorBodyString = response.errorBody()?.string()
//                        val response = Gson().fromJson(errorBodyString, SourcesResponse::class.java)
//                        showDialog(message = "Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.",
//                            posActionName = "try again",
//                            posAction = DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                                dialog?.dismiss()
//                                getNews(id)
//                            },
//                            NegActionName = "cancel",
//                            NegAction = DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
//                                dialog?.dismiss()  // finish Dialog
//                            }
//                        )
//                    }
//
//
//                }    })
//
//    }  //  before apply mvvm

    fun getNews(id: String?) {  // call api
    showLoading.postValue(true)
    ApiManager.getApis().getNews(ApiConstant.apiKey ,id?: "")
        .enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                showLoading.postValue(false)
                messageDialogLiveData.postValue(t.localizedMessage)

            }

            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                showLoading.postValue(false)
                if (response.isSuccessful) {
                    // show News
                    newsLiveData.postValue(response.body()?.articles)  // return data / hold data
                } else {

                    messageDialogLiveData.postValue("Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.")

                }


            }    })

}
}

















