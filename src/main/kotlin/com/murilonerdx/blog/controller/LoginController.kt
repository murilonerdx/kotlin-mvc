package com.murilonerdx.blog.controller

import com.murilonerdx.blog.model.user.entity.User
import com.murilonerdx.blog.model.user.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/login")
class LoginController(private val repository: UserRepository) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun form(model: Model): String {
        model.addAttribute("user", User())
        return "login"
    }

    @PostMapping
    fun login(user:User, model : Model): String{
        val optional = repository.findByEmail(user.email)
        if(optional.isEmpty){
            model.addAttribute("messageError","Usuário não localizado")
            return "login"
        }

        val userDatabase = optional.get()
        if(user.password != userDatabase.password){
            model.addAttribute("messageError","Senha invalida")
            return "login"
        }
        return "redirect:/"
    }
}