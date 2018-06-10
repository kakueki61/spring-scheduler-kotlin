package net.kakueki61.springscheduler.repositories

import net.kakueki61.springscheduler.entities.Account
import net.kakueki61.springscheduler.entities.Role

interface AccountRepository {
    fun create(userName: String, password: String, role: Role): Account
    fun update()
    fun findByName(name: String): Account?
    fun delete()
}
