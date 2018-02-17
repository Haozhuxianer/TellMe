package com.example.hzxr.tellme.net

import android.util.Log
import com.example.hzxr.tellme.net.model.User
import com.example.hzxr.tellme.net.model.Users
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.simpleframework.xml.core.Validate

/**
 * Created by Hzxr on 2018/2/16.
 */
object ApiClient {
    private val retrofit = RetrofitManager.getInstance()
    private val service = retrofit?.create(ApiService::class.java)


    var listener: ((List<User>) -> Unit)? = null

    fun getAllUserInServer() : Observable<Users>?{
//        var userList: List<User>? = null
        return service?.getAllUser()?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    users ->
//                    userList = users.getUserList()
////                    Log.d("TAG", userList.toString())
////                    listener?.invoke(users.getUserList())
//                }
//        Log.d("TAG","apiClient userList: " + userList.toString())
//        return userList
    }


}