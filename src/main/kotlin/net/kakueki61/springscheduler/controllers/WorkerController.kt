package net.kakueki61.springscheduler.controllers

import net.kakueki61.springscheduler.AccountService
import net.kakueki61.springscheduler.forms.AccountCreateForm
import net.kakueki61.springscheduler.repositories.WorkerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("worker")
class WorkerController() {

    @Autowired
    lateinit var accountService: AccountService

    @GetMapping("index")
    fun index(): String {
        return "index"
    }

    @GetMapping("sign_up")
    fun signUp(): String {
        return "worker/sign_up"
    }

    @PostMapping("sign_up")
    fun create(@Validated form: AccountCreateForm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "hello"

        val userName = requireNotNull(form.userName)
        val password = requireNotNull(form.password)
        accountService.registerWorker(userName, password)
        return "login"
    }
}
