package com.auth.assignment.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class CommonUtils {

    private CommonUtils() {
    }

    public static boolean isNull(Object obj){
        return obj == null;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }



}
