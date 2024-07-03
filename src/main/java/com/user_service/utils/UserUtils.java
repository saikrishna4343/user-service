package com.user_service.utils;

import java.util.UUID;
import java.util.function.Function;

public class UserUtils {

    public static Function<String, String> generateAccountId = domain ->
            domain + UUID.randomUUID().toString().toUpperCase().substring(0,5);
}
