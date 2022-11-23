package org.example.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.session.AuthContext;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Log4j2
public class AuthInterceptor implements HandlerInterceptor {
    private final AuthContext authContext;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) throws Exception {
        log.info("Trying to access to: [{}]", request.getRequestURI());

        if (authContext.isAuthorized()) {
            return true;
        }
        response.sendRedirect("accessDenied");
        return false;
    }
}
