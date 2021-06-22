package com.murilonerdx.blog.model.article.repository

import com.murilonerdx.blog.model.article.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long>
