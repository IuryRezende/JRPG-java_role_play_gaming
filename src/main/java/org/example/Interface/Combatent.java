package org.example.Interface;

public interface Combatent {

    void takeDamage(int damage);
    void attack(Combatent target);
    boolean dodge();
    void defense();
    boolean isLive();
}
