package net.kakueki61.springscheduler.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity?) {
        super.configure(web)
        web?.ignoring()?.antMatchers(
                "/images/**",
                "/css/**",
                "/javascript/**",
                "/webjars/**"
        )
    }

    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()

        http.authorizeRequests()
                .antMatchers("/", "/index", "/home", "/worker/sign_up").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()

        // ログイン設定
        http.formLogin()
                .loginPage("/login")            // ログインフォームのパス
                .permitAll()

        // ログアウト設定
        http.logout()
                .permitAll()
    }

//    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("user").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN")
//    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        println("##########################")
        println("userDetailsService()")
        println("##########################")
        val user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build()
        return InMemoryUserDetailsManager(user)
    }

//    class AuthenticationConfiguration : GlobalAuthenticationConfigurerAdapter() {
//        @Autowired
//        lateinit var userDetailsService: UserDetailsService
//
//        override fun init(auth: AuthenticationManagerBuilder) {
//            auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder())
//        }
//    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
