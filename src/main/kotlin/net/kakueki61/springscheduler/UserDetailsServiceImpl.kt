package net.kakueki61.springscheduler

import net.kakueki61.springscheduler.entities.Account
import net.kakueki61.springscheduler.entities.Role
import net.kakueki61.springscheduler.models.Worker
import net.kakueki61.springscheduler.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService : UserDetailsService {

    @Autowired
    lateinit var accountRepo: AccountRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null || username.isBlank()) {
            throw UsernameNotFoundException("username is empty")
        }

        val account = accountRepo.findByName(username)
        if (account == null) {
            throw UsernameNotFoundException("user not found: $username")
        } else if (!account.enabled) {
            throw UsernameNotFoundException("user not enabled: $username")
        } else {
            return getGrantedUser(account)
        }
    }

    private fun getGrantedUser(account: Account): UserDetails {
        return Worker(account, AuthorityUtils.createAuthorityList("WORKER"))
    }

    fun registerAdmin(userName: String, password: String) {
        accountRepo.create(userName, password, Role.ADMIN)
    }

    fun registerWorker(userName: String, password: String) {
        accountRepo.create(userName, password, Role.WORKER)
    }

    fun registerShop(userName: String, password: String) {
        accountRepo.create(userName, password, Role.SHOP)
    }
}
