package cn.marve1ous.util;

import cn.marve1ous.service.Impl.StudentServiceImpl;
import cn.marve1ous.service.StudentService;

import java.util.Random;
import java.util.UUID;
import java.util.Date;

public class UserUtil {
    public UserUtil() {
    }

    public static String getUU() {
        long d = new Date().getTime();
        String n = String.valueOf(d);
        String a = String.valueOf(new Random().nextInt(1000));
        n += a;
        return n;
    }

    public static int div(int a, int b) {
        return a/b;
    }

    public static void main(String[] args) {

    }
}
