package com.auth.assignment;

public final class AuthConstants {

    private AuthConstants() {
    }

    public static final String authenticateURL= "/api/authenticate";
    public static final String FETCH_ALL_USER_URL= "/api/user/all";
    public static final String FETCH_CURRENT_USER_URL= "/api/user";
    public static final String login_email= "user@localhost.com";

    public static final String wrong_login_email= "user1@localhost.com";
    public static final String login_password= "1234";

    public static final String wrong_login_password= "12345";

    public static final long ONE_MINUTE = 60000;



}
