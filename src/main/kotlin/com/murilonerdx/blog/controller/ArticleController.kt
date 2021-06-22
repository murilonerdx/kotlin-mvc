package com.murilonerdx.blog.controller

import com.murilonerdx.blog.model.article.entity.Article
import com.murilonerdx.blog.model.article.repository.ArticleRepository
import com.murilonerdx.blog.model.author.entity.Author
import com.murilonerdx.blog.model.author.repository.AuthorRepository
import org.apache.catalina.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/article")
class ArticleController(
        private val authorRepository: AuthorRepository,
        private val articleRepository: ArticleRepository
) {

    @GetMapping
    fun form(model: Model): String {
        model.addAttribute("article", Article())
        return "article"
    }

    @PostMapping
    fun save(article: Article, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        val currentUser = session.getAttribute("currentUser") as com.murilonerdx.blog.model.user.entity.User
        val authorOptional: Optional<Author> = authorRepository.findByUserId(currentUser.id)
        val author = if (authorOptional.isPresent) {
            authorOptional.get()
        } else {
            val author = Author(user = currentUser)
            authorRepository.save(author)
        }

        article.author = author
        article.date = LocalDateTime.now()
        articleRepository.save(article)
        val messageSuccess = "Artigo criado com sucesso!!!"
        redirectAttributes.addFlashAttribute("messageSuccess", messageSuccess)

        return "redirect:/"
    }

}