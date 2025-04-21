package com.Override;
public class OverrideExercise {
    public static void main(String[] args) {
        Person jake = new Person("jake", 20);
        System.out.println(jake.say());

        Student smith = new Student("smith", 22, 123456, 99.9);
        System.out.println(smith.say());
    }
}
