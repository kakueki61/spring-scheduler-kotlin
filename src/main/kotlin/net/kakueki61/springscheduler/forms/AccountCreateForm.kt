package net.kakueki61.springscheduler.forms

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class AccountCreateForm {
    @NotBlank
    @Size(max = 20)
    var userName: String? = null

    @NotBlank
    @Size(min = 8, max = 20)
    var password: String? = null
}
