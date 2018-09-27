package com.zmf.service;

import org.springframework.stereotype.Component;

@Component("interceptorService")
public class InterceptorService {
    public boolean isToIntercept(String uri) {
        if (uri.equals("/")) {
            return false;
        } else if (uri.equals("/user/login")) {
            return false;
        } else if (uri.equals("/user/register")) {
            return false;
        } else if (uri.contains("/user/confirm/")) {
            return false;
        } else if (uri.contains("/statics/")) {
            return false;
        } else if (uri.equals("/user/seekPassword")) {
            return false;
        } else if (uri.equals("/user/alterPassword")) {
            return false;
        } else if (uri.contains("/user/seek/")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean permissionInterception(String uri, int permissionCode) {
        return false;
    }
}
