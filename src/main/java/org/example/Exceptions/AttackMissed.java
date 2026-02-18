package org.example.Exceptions;

public class AttackMissed extends RuntimeException {
    public AttackMissed(String target, String attacker) {
        super(" ".repeat(4) + target + " dodged the attack of " + attacker);
    }
}
