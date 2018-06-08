package net.kakueki61.springscheduler.repositories

import net.kakueki61.springscheduler.entities.Account

interface AccountRepository {
    fun create()
    fun update()
    fun findByName(name: String): Account?
    fun delete()
}
