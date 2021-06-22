package com.murilonerdx.blog.config

import com.murilonerdx.blog.model.user.entity.User
import com.murilonerdx.blog.model.user.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseInitialization(private val userRepository: UserRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (userRepository.count().equals(0)) {
            val user = User(
                    name = "Administrador",
                    email = "admin@blog.com",
                    password = "admin"
            )
            userRepository.save(user);
        }
    }
}