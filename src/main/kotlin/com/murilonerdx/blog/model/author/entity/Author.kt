package com.murilonerdx.blog.model.author.entity

import com.murilonerdx.blog.model.user.entity.User
import javax.persistence.*

@Entity
data class Author (
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id: Long = 0,
        var about: String = "",
        @OneToOne
        var user: User = User(),

        )