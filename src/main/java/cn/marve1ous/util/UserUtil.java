package cn.marve1ous.util;

import java.util.UUID;

public class UserUtil {
    public static String getUid() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static void main(String[] args) {
        int i=0;
        while (i++<10)
        System.out.println(getUid());
    }
}
