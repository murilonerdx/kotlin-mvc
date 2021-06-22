package com.murilonerdx.blog.model.article.entity

import com.murilonerdx.blog.model.author.entity.Author
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Article(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        @Column(length = 255, nullable = true)
        var title: String = "",
        @Column(length = 255, nullable = true)
        var subTitle: String = "",
        @Column(length = 8000, nullable = true)
        var content: String = "",
        var date:LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        var author: Author = Author(),
)