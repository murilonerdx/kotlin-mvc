package com.murilonerdx.blog.controller

import com.murilonerdx.blog.model.article.repository.ArticleRepository
import com.murilonerdx.blog.model.category.repository.CategoryRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class IndexController(private val categoryRepository: CategoryRepository, private val articleRepository: ArticleRepository) {

    @RequestMapping("/",method=[RequestMethod.GET])
    fun index(model: Model):String{
        model.addAttribute("categories",categoryRepository.findAll())
        model.addAttribute("articles",articleRepository.findAll())
        return "index";
    }
}