package com.murilonerdx.blog.model.category.repository

import com.murilonerdx.blog.model.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
}