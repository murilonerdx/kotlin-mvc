package com.murilonerdx.blog.model.user.entity

import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        var id: Long = 0,
        var name:String = "",
        @Column(unique=true)
        var email: String = "",
        var password:String = "")
