package com.example.hzxr.tellme.db.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

/**
 * Created by Hzxr on 2018/1/20.
 */
@Entity
data class Session(
        @Id
        var id: Long = 0,
        var sessionName: String,
        var updateData: String,
        var lastMsg: ToOne<Msg>? = null,
        var members: List<Member>? = null
) {
        constructor() : this(0, "", "", null, listOf())
}