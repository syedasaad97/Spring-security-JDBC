package com.auth.assignment.util;

public final class Constants {

    private Constants() {
    }

    public static final String USER_ROLE = "ROLE_USER";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    public static final String IS_ADMIN = Constants.HAS_ROLE_PRE + Constants.ADMIN_ROLE + Constants.HAS_ROLE_POST;

    public static final String IS_USER = Constants.HAS_ROLE_PRE + Constants.USER_ROLE + Constants.HAS_ROLE_POST;

    public static final String IS_USER_OR_ADMIN = Constants.HAS_ANY_ROLE_PRE + Constants.USER_ROLE + Constants.HAS_ROLE_OR + Constants.ADMIN_ROLE + Constants.HAS_ROLE_POST;

    public static final String HAS_ANY_ROLE_PRE = "hasAnyRole('";
    public static final String HAS_ROLE_PRE = "hasRole('";
    public static final String HAS_ROLE_POST = "')";
    public static final String HAS_ROLE_OR = "','";
}
