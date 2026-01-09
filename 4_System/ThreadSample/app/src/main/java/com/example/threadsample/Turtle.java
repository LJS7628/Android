package com.example.threadsample;

public class Turtle implements Runnable {
    String name;
    public Turtle(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for(int i =0; i<25; i++){
            System.out.println(name+" : "+i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
