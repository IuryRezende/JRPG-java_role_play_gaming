package org.example.Classes;

import org.example.model.Player;

public class AssassinClass extends Player {

    private final int defaultDodge = 2;

    public AssassinClass() {}


    public AssassinClass(String name, int life) {
        setAgility(defaultDodge);
    }

    @Override
    public void takeDamage(int damage) {

    }


    @Override
    public boolean dodge() { return true; }

    @Override
    public boolean isLive() {
        return false;
    }

    @Override
    public void showAbilities() {

    }

    @Override
    public void describeAbilities() {

    }
}
