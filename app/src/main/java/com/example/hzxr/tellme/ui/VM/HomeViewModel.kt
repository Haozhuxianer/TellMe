package com.example.hzxr.tellme.ui.VM

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v7.widget.Toolbar
import android.util.Log

import com.example.hzxr.tellme.R
import com.example.hzxr.tellme.Util.ActivitysUtil
import com.example.hzxr.tellme.Util.SharePreferencesManager
import com.example.hzxr.tellme.databinding.ActivityHomeBinding
import com.example.hzxr.tellme.net.ConnectManager
import io.objectbox.BoxStore
import org.jivesoftware.smack.StanzaListener
import org.jivesoftware.smack.filter.AndFilter
import org.jivesoftware.smack.filter.PacketFilter
import org.jivesoftware.smack.filter.StanzaTypeFilter
import org.jivesoftware.smack.packet.Presence
import org.jivesoftware.smack.packet.Stanza

/**
 * Created by Hzxr on 2018/2/5.
 */
class HomeViewModel(activity: Activity, binding: ActivityHomeBinding) : BaseViewModel<ActivityHomeBinding>(activity, binding) {

    val onMenuItemClickListener: Toolbar.OnMenuItemClickListener
        get() = Toolbar.OnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_add_friend) {
                Log.d("TAG", "action add friend")
                ActivitysUtil.startActivityToAddFriend(activity)
            }
            true
        }
//所有的这些事件的监听不应该放在主界面里，因为，即使主界面销毁，应用依然需要监听这些事件的发生，所以应该把这些监听放在一个服务内，在成功登陆后开始。暂时放在主界面测试

    init {
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    Log.d("TAG", "navigation profile")
                    ActivitysUtil.startActivityToProfile(activity)
                }
                R.id.navigation_settings -> {
                    Log.d("TAG", "navigation settings")
                }
                R.id.navigation_debug -> {
                    ActivitysUtil.startActivityToMainDebug(activity)
                }
                R.id.navigation_cancel -> {
                    ConnectManager.getConnect()?.instantShutdown()
                    ConnectManager.disConnect()
                    SharePreferencesManager.removeInfo(activity)
                    ActivitysUtil.startActivityToLogin(activity)
                    activity.finish()
                }
            }
            true
        }
    }


// 在使用databinding binding 这个监听的时候总是报错，类型问题，目前没有找到好的解决办法，先暂时这样写。
//    val onNavigationItemSelectedListener: NavigationView.OnNavigationItemSelectedListener
//        get() = NavigationView.OnNavigationItemSelectedListener {
//            item ->
//            when(item.itemId) {
//                R.id.navigation_profile -> {
//                    Log.d("TAG", "navigation profile")
//                }
//                R.id.navigation_settings -> {
//                    Log.d("TAG", "navigation settings")
//                }
//            }
//            true
//        }

}