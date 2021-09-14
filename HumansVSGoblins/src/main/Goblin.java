package main;

public class Goblin extends Combatants {

    public Goblin() {
        this.health = 10;
        this.maxHealth = 10;
        this.strength = 5;
        this.defense = 2;
        this.position = new int[]{5, 5};
        this.newPosition = new int[]{this.getPosition()[0], this.getPosition()[1]};
    }

}
