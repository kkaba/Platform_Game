package platform.game;

import platform.util.Vector;

public abstract class Character extends Actor {
	private final double maxHealth;

	public Character(Vector position, Vector velocity, double maxHealth) {
		// TODO Auto-generated constructor stub
		super(position, velocity);
		this.maxHealth = maxHealth;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Returns the maximum number of life points a character can have
	 * 
	 * @return maximum number of life points a character can have
	 */
	public double getMaxHealth() {
		return maxHealth;
	}

	/**
	 * The current number of life points
	 * 
	 * @return current number of life points
	 */
	public abstract double getHealth();

}
