package org.example.Classes;

import org.example.model.Player;

public class AssassinClass extends Player {

    private final String emoji = "🐱‍👤";
    private final int defaultDodge = 2;

    public AssassinClass() {}


    public AssassinClass(String name, int life) {
        agility += defaultDodge;
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public void dodge() {

    }

    @Override
    public boolean isLive() {
        return false;
    }
}
