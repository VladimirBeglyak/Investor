package by.broker.http.filter;

import by.broker.http.dto.ClientDto;
import by.broker.http.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static by.broker.http.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, LOCALE, REGISTRATION, INDEX, STOCKS,NEWS);
    private static final Set<String> PRIVATE_PATH_FOR_USER = Set.of(LOGIN, REGISTRATION, STOCKS, STOCK, BUY, DEPOSIT,LOGOUT);
    private static final Set<String> PRIVATE_PATH_FOR_ADMIN = Set.of(ADD_STOCK, LOGIN, REGISTRATION, STOCK, STOCKS, ALL_CLIENTS,LOGOUT,ADD_NEWS);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();

//        if (isPublicPath(uri) || isUserLoggedInAsUser(servletRequest)) {
//            filterChain.doFilter(servletRequest, servletResponse);
        if (isPublicPath(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isPrivatePathForUser(uri) && isUserLoggedInAsUser(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isPrivatePathForAdmin(uri) && isUserLoggedInAsAdmin(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer"); //c какой страницы пришел пользователь
            ((HttpServletResponse) servletResponse).sendRedirect(/*prevPage != null ? prevPage : */INDEX);
        }

    }

    private boolean isUserLoggedInAsUser(ServletRequest servletRequest) {
        ClientDto client = (ClientDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("client");
        return client != null && client.getRole() == Role.USER.name();
    }

    private boolean isUserLoggedInAsAdmin(ServletRequest servletRequest) {
        ClientDto client = (ClientDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("client");
        return client != null && client.getRole() == Role.ADMIN.name();
    }


    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(path -> uri.startsWith(path));
    }

    private boolean isPrivatePathForUser(String uri) {
        return PRIVATE_PATH_FOR_USER.stream().anyMatch(path -> uri.startsWith(path));
    }

    private boolean isPrivatePathForAdmin(String uri) {
        return PRIVATE_PATH_FOR_ADMIN.stream().anyMatch(path -> uri.startsWith(path));
    }

}
