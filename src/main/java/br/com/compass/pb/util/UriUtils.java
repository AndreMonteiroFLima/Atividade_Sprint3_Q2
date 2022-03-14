package br.com.compass.pb.util;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

public class UriUtils {

    public static URI getFullServletContextPath(ServletContext context,
                                                UriInfo uriInfo) {

        URI requestUri = uriInfo.getRequestUri();

        String contextPath = context.getContextPath();

        if (contextPath.trim().length() == 0) {
            String fullContextPath = requestUri.getScheme()
                    + "://" + requestUri.getRawAuthority();
            return URI.create(fullContextPath);
        } else {
            int contextPathSize = contextPath.length();
            String requestString = requestUri.toASCIIString();
            String fullContextPath = requestString.substring(
                    0, requestString.indexOf(contextPath) + contextPathSize);
            return URI.create(fullContextPath);
        }
    }
}