package com.murilonerdx.blog.model.user.repository

import com.murilonerdx.blog.model.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
}