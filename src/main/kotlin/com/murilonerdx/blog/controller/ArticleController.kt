package com.murilonerdx.blog.controller

import com.murilonerdx.blog.model.article.entity.Article
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/article")
class ArticleController {


    @GetMapping
    fun form(model: Model):String{
        model.addAttribute("article", Article())
        return "article"
    }
}