package com.pzp.encap;



 public class Account{
    private String name;
    private double balance;
    private String pwd;

    //提供两个构造器


     public Account() {
     }

     public Account(String name, double balance, String pwd) {
         this.setName(name);
         this.setBalance(balance);
         this.setPwd(pwd);
     }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length()>=2 && name.length()<=4){
            this.name = name;
        }else {
            System.out.println("名字长度不符合，长度应该为2或3或4个字符，将返回默认值 无名");
            this.name = "无名";
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance>20){
            this.balance = balance;
        }else {
            System.out.println("余额必须>20，默认为0");
        }
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if (pwd.length() == 6){
            this.pwd = pwd;
        }else {
            System.out.println("密码必须是6位，默认密码为000000");
            this.pwd = "000000";
        }
    }

    public void info(){
        System.out.println("账号信息 name " +  name  + "余额" + balance + "密码" + pwd);
    }


}
