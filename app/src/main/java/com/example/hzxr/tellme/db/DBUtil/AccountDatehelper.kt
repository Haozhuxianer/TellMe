package com.example.hzxr.tellme.db.DBUtil

import android.content.Context
import com.example.hzxr.tellme.db.model.Account
import com.example.hzxr.tellme.db.model.Account_
import com.example.hzxr.tellme.db.model.Member
import io.objectbox.BoxStore
import io.objectbox.relation.ToMany

/**
 * Created by Hzxr on 2018/1/21.
 */
object AccountDatehelper {

    fun add(boxStore: BoxStore, map: Map<String, Any>): Boolean {
        if (map.isEmpty()) return false
        val accountBox = boxStore.boxFor(Account::class.java)
        accountBox.put(mapToAccountObject(map))
        return true
    }

    fun delete(boxStore: BoxStore, username: String) {
        val accountBox = boxStore.boxFor(Account::class.java)
        val account = queryAccountById(boxStore, username)?: return
        accountBox.remove(account)
    }

    private fun queryAccountById(boxStore: BoxStore, username: String): Account? {
        val accountBox = boxStore.boxFor(Account::class.java)
        val result = accountBox.query().equal(Account_.username, username).build().findFirst()
        return result
    }

    private fun mapToAccountObject(map: Map<String, Any>): Account {
        val username = map.getValue("username") as String
        val nickname = map.getValue("nickname") as String?
        val email = map.getValue("email") as String
        val role = map.getValue("role") as String
        val friends = map.getValue("friends") as ToMany<Member>?
        return Account(username = username, nickname = nickname, email = email, role = role, friends = friends)
    }
}