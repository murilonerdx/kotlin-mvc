package com.murilonerdx.blog

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/signup")
class SignUpController {

    @GetMapping
    fun form(): String{
        return "signup";
    }

    @PostMapping
    fun save(user: User, confirmPassword: String):String{
        print("form $user")
        return "redirect:/index";
    }

}