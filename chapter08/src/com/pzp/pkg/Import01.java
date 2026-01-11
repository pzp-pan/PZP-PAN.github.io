package com.pzp.pkg;

import java.util.Arrays;

//import java.util.Scanner;//表示只会引进java.util包下的Scanner
//import java.util.*;//表示将java.util包下的所有类都引入
//建议：需要使用哪个类就导入哪个类即可
public class Import01 {
    public static void main(String[] args) {
        //使用系统提示Arrays完成数组排序
        int[] arr = {-1,20,2,13,3};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "\t ");
        }
    }
}
