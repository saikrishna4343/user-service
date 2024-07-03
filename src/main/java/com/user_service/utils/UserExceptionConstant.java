package com.user_service.utils;

public class UserExceptionConstant {
    public static final String USER_EXISTS_WITH_EMAIL = "User with email %s already exists";
    public static final String USER_EXISTS_WITH_PHONE = "User with phone number %s already exists";
    public static final String FIRST_LAST_NAME_IN_PASSWORD = "Cannot have first name or last name as password";

    public static final String USER_NOT_EXISTS_WITH_EMAIL = "Not a valid email %s";

    public static final String PROVIDE_VALID_USER_ROLE = "Provide a valid user role";

    public static final String USER_ALREADY_HAVE_GIVEN_ROLE = "User already have given role %s";

    public static final String ACCOUNT_IS_DEACTIVATED = "User account %s is deactivated";

    public static final String DATE_OF_BIRTH_CANNOT_BE_EMPTY = "Date of Birth cannot be empty";

    public static final String ACCOUNT_NOT_VERIFIED = "Account is not verified";
}
