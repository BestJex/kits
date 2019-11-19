package com.ptyt.haiguan.kits.test;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author: yq
 * @date: 2019/11/15 10:06
 * @description:
 */

public class PtytHaiguanKitsTestApplication {

    @Test
    public void test() {
        String path = "D:\\ftpdata";
        String path1 = "D:\\ftpdata\\123456\\654.jpg";
        String[] ss = path1.split(path);
        System.out.println(ss[1]);
    }


    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String s = localDateTime.getYear() + "-" + localDateTime.getMonth().getValue() + "-" + localDateTime.getDayOfMonth();
        System.out.println(s);
    }
}
