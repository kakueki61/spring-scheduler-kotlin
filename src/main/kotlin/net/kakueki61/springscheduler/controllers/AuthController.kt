package net.kakueki61.springscheduler.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController {
    @GetMapping("index")
    fun index(): String {
        return "index"
    }

    fun signUp(): String {
        return "worker/sign_up"
    }
}
