package com.pzp.extend_.improve_;

import com.pzp.extend_.Graduate;

public class Extends01 {
    public static void main(String[] args) {
        Pupil pupil = new Pupil();
        pupil.name="xiaomi";
        pupil.age=15;
        pupil.testing();
        pupil.setScore(60);
        pupil.showInfo();

        com.pzp.extend_.Graduate graduate = new Graduate();
        graduate.name="vivo";
        graduate.age=88;
        graduate.setScore(99);
        graduate.showInfo();
    }
}
