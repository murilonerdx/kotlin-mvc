package com.murilonerdx.blog.model.category.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Category(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id: Long = 0L,
        var name: String = ""
)