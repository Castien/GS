package main;

public class Human extends Combatants {

    public Human() {
        this.health = 20;
        this.maxHealth = 20;
        this.strength = 10;
        this.defense = 5;
        this.position = new int[]{0, 0};
        this.newPosition = new int[]{this.getPosition()[0], this.getPosition()[1]};
    }
}
