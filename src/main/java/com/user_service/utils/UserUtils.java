package com.user_service.utils;

import java.util.Random;
import java.util.UUID;
import java.util.function.Function;

public class UserUtils {

    public static Function<String, String> generateAccountId = domain ->
            domain + UUID.randomUUID().toString().substring(0,5);
}
