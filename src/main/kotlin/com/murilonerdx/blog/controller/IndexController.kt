package com.murilonerdx.blog.controller

import com.murilonerdx.blog.model.article.repository.ArticleRepository
import com.murilonerdx.blog.model.author.repository.AuthorRepository
import com.murilonerdx.blog.model.category.repository.CategoryRepository
import com.murilonerdx.blog.model.user.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


@Controller
@RequestMapping("/")
class IndexController(
        private val categoryRepository: CategoryRepository,
        private val articleRepository: ArticleRepository,private val authorRepository: AuthorRepository
) {

    @GetMapping
    fun index(@RequestParam(name = "page", required = false, defaultValue = "0") page: Int, model: Model): String {
        model.addAttribute("categories", categoryRepository.findAll())
        val articles = articleRepository.findAll(PageRequest.of(page, 1))
        if(authorRepository.findAll().size != 0){
            val nextPage = if (page >= articles.totalElements - 1) page else page + 1
            val priorPage = if (page <= 0) 0 else page - 1
            model.addAttribute("notFoundAuthor", true)
            model.addAttribute("older", nextPage)
            model.addAttribute("newer", articles.totalElements)
            model.addAttribute("articles", articles)
            model.addAttribute("nextPage", nextPage)
            model.addAttribute("priorPage", priorPage)
            model.addAttribute("isFirst", articles.isFirst)
            model.addAttribute("isLast", articles.isLast)
            model.addAttribute("author", articles.content.get(0).author)
            return "index"
        }else{
            model.addAttribute("notFoundAuthor", false)
            return "index"
        }
    }

}