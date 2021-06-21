package com.murilonerdx.blog.model.user.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        var id: Long = 0,
        var name:String = "",
        var email: String = "",
        var password:String = "")
