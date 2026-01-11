package com.pzp.extend_;

public class Extends01 {
    public static void main(String[] args) {
        Pupil pupil = new Pupil();
        pupil.name="xiaomi";
        pupil.age=10;
        pupil.testing();
        pupil.setScore(60);
        pupil.showInfo();

        Graduate graduate = new Graduate();
        graduate.name="vivo";
        graduate.age=19;
        graduate.testing();
        graduate.setScore(99);
        graduate.showInfo();
    }
}

