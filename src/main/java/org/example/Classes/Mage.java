package org.example.Classes;


import org.example.Armory.MageSpells;
import org.example.ModelToClasses.Human;

import java.util.ArrayList;
import java.util.List;


public class Mage extends Human {

    private Double mana;
    private List<MageSpells> spellsList = new ArrayList<>();
}
