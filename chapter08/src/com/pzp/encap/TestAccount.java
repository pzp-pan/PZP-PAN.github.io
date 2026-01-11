package com.pzp.encap;

public class TestAccount {
    public static void main(String[] args) {
        Account account = new Account();
        account.setName("jack");
        account.setBalance(6666);
        account.setPwd("666666");

        account.info();
    }
}
