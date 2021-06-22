package com.murilonerdx.blog.model.author.repository

import com.murilonerdx.blog.model.author.entity.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long>
