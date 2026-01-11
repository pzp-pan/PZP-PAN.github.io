package com.pzp.encap;

public class Encapsulation01 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("jack");
        person.setAge(30);
        person.setSalary(30000);
        System.out.println(person.info());
        System.out.println(person.getSalary());

        //如果我们自己使用构造器指定属性
        Person person1 = new Person("tom", 2000, 50000);
        System.out.println("======tom======的信息");
        System.out.println(person1.info());
    }
}
class Person{
    public String name; //名字公开
    private int age; //age私有化
    private double salary;//..

    public Person(){

    }
    //有三个属性的构造器
    public Person(String name, int age, double salary) {
//        this.name = name;
//        this.age = age;
//        this.salary = salary;

        //我们可以将set方法写在构造器中，这样仍然可以验证数据
        setName(name);
        setAge(age);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //加入对数据的校验
        if (name.length() >= 2 && name.length() <= 6){
            this.name = name;
        }else {
            System.out.println("名字的长度不对，需要2到4个字符");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        //判断
        if (age >= 1 && age <= 120){
            this.age = age;
        }else {
            System.out.println("年龄输入错误");
            this.age = 18;
        }
    }

    public double getSalary() {
        //可以在这里增加对当前对象的权限判断
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String info(){
        return "信息为name " + name + " age=" + age + " 薪水=" + salary;
    }
}
