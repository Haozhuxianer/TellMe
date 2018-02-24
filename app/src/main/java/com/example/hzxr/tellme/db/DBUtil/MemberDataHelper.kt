package com.example.hzxr.tellme.db.DBUtil

import com.example.hzxr.tellme.db.model.Member
import com.example.hzxr.tellme.db.model.Member_
import io.objectbox.BoxStore

/**
 * Created by Hzxr on 2018/2/24.
 */
object MemberDataHelper {

    fun add(boxStore: BoxStore, data: Map<String, String?>): Boolean {
        if (data.isEmpty()) return false
        val memberBox = boxStore.boxFor(Member::class.java)
        memberBox.put(mapToMemberObject(data))
        return true
    }

    fun delete(boxStore: BoxStore, username: String) {
        val memberBox = boxStore.boxFor(Member::class.java)
        val member = queryMemberByName(boxStore, username)?: return
        memberBox.remove(member)
    }

    fun queryMemberByName(boxStore: BoxStore, username: String): Member? {
        val memberBox = boxStore.boxFor(Member::class.java)
        return memberBox.query().equal(Member_.username, username).build().findFirst()
    }

    private fun mapToMemberObject(data: Map<String, String?>): Member {
        return Member(username = data["username"]!!,
                nickname = data["nickname"],
                parentId = data["parentId"]!!)
    }
}