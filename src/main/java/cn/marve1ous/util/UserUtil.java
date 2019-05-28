package cn.marve1ous.util;

import java.util.UUID;

public class UserUtil {
    public static String getUid() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println(getUid());
    }
}
