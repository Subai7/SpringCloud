package com.atguigu.springcloud;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(21);
        list.add(23);
        list.add(30);
        list.add(32);
        boolean existed = isExisted(list,53);
        System.out.println(existed);
    }

    public static boolean isExisted(List<Integer> s, int P){
        for (int i = 0; i < s.size() ; i++) {
            for (int j = i+1;j<s.size();j++){
                if((s.get(i)+s.get(j))==P){
                    return true;
                }
            }
        }
        return false;
    }
}
