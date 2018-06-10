package net.kakueki61.springscheduler.repositories

import net.kakueki61.springscheduler.entities.Account
import net.kakueki61.springscheduler.entities.Role
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository

@Repository
class JdbcAccountRepository(private val jdbcTemplate: JdbcTemplate) : AccountRepository{

    private val rowMapper = RowMapper<Account> { rs, rowNum ->
        Account(rs.getLong("id"),
                rs.getString("user_name"),
                rs.getString("password"),
                Role.values().first { it.name == rs.getString("role") },
                rs.getBoolean("enabled"))
    }

    override fun create(userName: String, password: String, role: Role): Account {
        jdbcTemplate.update(
                """INSERT INTO accounts(
                    user_name, password, role, enabled
                    ) VALUES(?)""".trimMargin(), userName, password, role, true)

        val id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Long::class.java)
        val notNullId = requireNotNull(id)
        return Account(notNullId, userName, password, role, true)
    }

    override fun update() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByName(name: String): Account? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}