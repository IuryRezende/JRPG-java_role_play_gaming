package org.example.ModelToClasses;

public abstract class Human {
    private String name;
    private Double life;

    public Human() {}

    public Human(String name, Double life) {
        this.name = name;
        this.life = life;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getLife() { return life; }

    public void setLife(Double life) { this.life = life; }
}
