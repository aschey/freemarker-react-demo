package com.dfs.freemarkerDemo.model;

public class Car {
    private String make;
    private String model;
    private String secret;

    public Car() {

    }

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
        this.secret = "";
    }

    public Car(String make, String model, String secret) {
        this.make = make;
        this.model = model;
        this.secret = secret;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
