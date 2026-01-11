package com.pzp.modifier;

public class B {
    public void say(){
        A a = new A();
        //在同一个包下可以访问 public protected 和默认,不能访问private
        System.out.println("n1=" + a.n1 + " " + a.n2 + " " + a.n3);
    }
}
