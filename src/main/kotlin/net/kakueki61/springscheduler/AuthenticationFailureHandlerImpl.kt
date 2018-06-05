package net.kakueki61.springscheduler

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFailureHandlerImpl : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
        var errorId = ""
        if (exception is BadCredentialsException) {
            errorId = "001"
        }
        response.sendRedirect(request.contextPath + "/"
                        + request.getParameter("identifier") + "/index?error=$errorId")
    }
}
