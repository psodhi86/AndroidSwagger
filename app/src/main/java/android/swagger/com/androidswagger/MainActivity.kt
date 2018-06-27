package android.swagger.com.androidswagger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.swagger.client.ApiClient
import io.swagger.client.api.UserApi
import io.swagger.client.model.User
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val apiClient: ApiClient
    apiClient = ApiClient()
    val userApi = apiClient.createService(UserApi::class.java)
    val userResponseCall = userApi.getUserByName("user1")

    userResponseCall.enqueue(object : retrofit2.Callback<User> {
      override fun onFailure(call: Call<User>?, t: Throwable?) {
        Log.d("ErrorResoponse",t?.message);
      }

      override fun onResponse(call: Call<User>?, response: Response<User>?) {
        if(response?.isSuccessful!!){
          Log.d("Response",""+response.body().toString());
        }else{
          Log.d("Response",""+response.errorBody().toString());
        }
      }
    })
  }
}
