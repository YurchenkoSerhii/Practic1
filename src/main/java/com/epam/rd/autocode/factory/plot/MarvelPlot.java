package com.epam.rd.autocode.factory.plot;

public class MarvelPlot implements Plot {
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    @Override
    public String toString() {
        StringBuilder heroesList = new StringBuilder();
        for (int i = 0; i < heroes.length; i++) {
            heroesList.append("brave ").append(heroes[i].name());
            if (i < heroes.length - 1) {
                heroesList.append(", ");
            }
        }
        return epicCrisis.name() + " threatens the world. But " + heroesList + " on guard. So, no way that intrigues of " + villain.name() + " overcome the willpower of inflexible heroes";
    }
}
