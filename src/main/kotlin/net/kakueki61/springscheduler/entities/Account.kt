package net.kakueki61.springscheduler.entities

data class Account(
        val id: Long,
        val userName: String,
        val password: String,
        val role: Role,
        val isEnabled: Boolean)

enum class Role {
    WORKER, SHOP, ADMIN
}
