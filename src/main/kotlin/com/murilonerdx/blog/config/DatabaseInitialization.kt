package com.murilonerdx.blog.config

import com.murilonerdx.blog.model.category.entity.Category
import com.murilonerdx.blog.model.category.repository.CategoryRepository
import com.murilonerdx.blog.model.user.entity.User
import com.murilonerdx.blog.model.user.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoader(
        private val userRepository: UserRepository,
        private val categoryRepository: CategoryRepository
) : CommandLineRunner {


    override fun run(vararg args: String?) {
        loadUser()
        loadCategories()
    }

    private fun loadCategories() {
        if (categoryRepository.count() == 0L) {
            listOf(
                    Category(name = "World"),
                    Category(name = "U.S."),
                    Category(name = "Technology"),
                    Category(name = "Design"),
                    Category(name = "Culture"),
                    Category(name = "Business"),
                    Category(name = "Politics"),
                    Category(name = "Opinion"),
                    Category(name = "Science"),
                    Category(name = "Health"),
                    Category(name = "Style"),
                    Category(name = "Travel")
            ).also { categoryRepository.saveAll(it) }
        }
    }

    private fun loadUser() {
        if (userRepository.count() == 0L) {
            val user = User(
                    name = "Administrator",
                    email = "admin@blog.com",
                    password = "admin"
            )
            userRepository.save(user)
        }
    }
}
