package com.murilonerdx.blog.controller

import com.murilonerdx.blog.model.article.entity.Article
import com.murilonerdx.blog.model.article.repository.ArticleRepository
import com.murilonerdx.blog.model.author.entity.Author
import com.murilonerdx.blog.model.author.repository.AuthorRepository
import com.murilonerdx.blog.model.category.repository.CategoryRepository
import org.apache.catalina.User
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
        private val articleRepository: ArticleRepository,
        private val categoryRepository: CategoryRepository
) {


    @GetMapping
    fun form(model: Model, session: HttpSession): String {
        if(session.getAttribute("currentUser") != null){
            model.addAttribute("article", Article())
            model.addAttribute("categories", categoryRepository.findAll());
            return "article"
        }
        return "redirect:/"

    }

    @PostMapping
    fun save(article: Article, session: HttpSession, redirectAttributes: RedirectAttributes): String {
        if(session.getAttribute("currentUser") != null){
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
        return "redirect:/"

    }

    @GetMapping("/list")
    fun list(model: Model, session: HttpSession): String {
        if(session.getAttribute("currentUser") != null){
            model.addAttribute("articles", articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")))
            model.addAttribute("categories", categoryRepository.findAll())
            return "article-list"
        }
        return "redirect:/"
    }

    @GetMapping("/list/user/{idUser}")
    fun listByAuthor(@PathVariable idUser: Long, model: Model, session: HttpSession): String {
        val currentUser = session.getAttribute("currentUser")
        if(currentUser != null){
            val sort = Sort.by(Sort.Direction.DESC, "id")
            model.addAttribute("articles", articleRepository.findByAuthorUserId(idUser, sort))
            model.addAttribute("categories", categoryRepository.findAll())
            return "article-list"
        }
        return "redirect:/"

    }

    @GetMapping("/list/category/{idCategory}")
    fun listByCategory(@PathVariable idCategory: Long, model: Model, session: HttpSession): String {
        val currentUser = session.getAttribute("currentUser")
        if(currentUser != null){
            val sort = Sort.by(Sort.Direction.DESC, "id")
            model.addAttribute("articles", articleRepository.findByCategoryId(idCategory, sort))
            model.addAttribute("categories", categoryRepository.findAll())
            return "article-list"
        }
        return "redirect:/"

    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model, session: HttpSession): String {
        val currentUser = session.getAttribute("currentUser")
        if(currentUser != null){
            model.addAttribute("article", articleRepository.findById(id).get())
            model.addAttribute("categories", categoryRepository.findAll())
            return "article"
        }
        return "redirect:/"
    }


    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long, model: Model, session: HttpSession): String {
        val currentUser = session.getAttribute("currentUser")
        if(currentUser != null){
            articleRepository.deleteById(id);
            return "article-list"
        }
        return "redirect:/"
    }

}