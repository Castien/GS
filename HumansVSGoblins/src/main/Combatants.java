package main;

public class Combatants {

    public int health = 50;
    public int  maxHealth = 50;
    public int  strength = 1;
    public int  defense = 1;
    public int [] position = new int[]{0, 0};
    public int [] newPosition = new int[]{0, 0};

    void loseHealth(int dmg) {
        setHealth(getHealth() + getDefense() <= dmg ? 0 : getHealth() + getDefense() - dmg);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int[] newPosition) {
        this.newPosition = newPosition;
    }
}
