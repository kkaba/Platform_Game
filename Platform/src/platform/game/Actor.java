package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;

/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor implements Comparable<Actor> {
	private World world;

	/**
	 * Creates new Actor
	 */
	public Actor() {

	}

	/**
	 * Creates a new Actor and checks that its position is not null
	 * 
	 * @param position
	 *            Actor's position
	 */
	public Actor(Vector position) {
		if (position == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Creates a new Actor and checks that its box is not null
	 * 
	 * @param box
	 *            Actor's box
	 */
	public Actor(Box box) {
		if (box == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Creates a new Actor and checks that the position and velocity are not
	 * null
	 * 
	 * @param position
	 *            Actor's position
	 * @param velocity
	 *            Actor's velocity
	 */
	public Actor(Vector position, Vector velocity) {
		this(position);
		if (velocity == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Allows the actor to act before the physic
	 * 
	 * @param input
	 */
	public void preUpdate(Input input) {
	}

	/**
	 * Updates the actor
	 * 
	 * @param input
	 */
	public void update(Input input) {

	}

	/**
	 * Allows the actor to act after the drawing
	 * 
	 * @param input
	 */
	public void postUpdate(Input input) {

	}

	/**
	 * Draws the actor
	 * 
	 * @param input
	 * @param output
	 */
	public void draw(Input input, Output output) {

	}

	/**
	 * Returns the actor's velocity Initially an unspecified actor doesn't have
	 * speed
	 * 
	 * @return actor's velocity
	 */
	public Vector getVelocity() {
		return Vector.ZERO;
	}

	/**
	 * Check which actor has the highest priority
	 */
	@Override
	public int compareTo(Actor other) {

		if (getPriority() > other.getPriority()) {
			return -1;
		} else if (getPriority() == other.getPriority()) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Interaction between an actor and another one
	 * 
	 * @param other
	 *            the other actor
	 */
	public void interact(Actor other) {
	}

	/**
	 * Returns the actor priority
	 * 
	 * @return actor's priority
	 */
	public abstract int getPriority();

	/**
	 * Determines if the actor is solid
	 * 
	 * @return true if solid, false otherwise
	 */
	public boolean isSolid() {
		return false;
	}

	/**
	 * Returns the actor's box
	 * 
	 * @return actor's box
	 */
	public Box getBox() {
		return null;
	}

	/**
	 * Returns the actor's sprite
	 * 
	 * @param name
	 *            sprite name
	 * @return Sprite
	 */
	protected Sprite getSprite(String name) {
		return world.getLoader().getSprite(name);
	}

	/**
	 * Return the actor's position
	 * 
	 * @return actor's position
	 */
	public Vector getPosition() {
		Box box = getBox();
		if (box == null) {
			return null;
		}
		return box.getCenter();
	}

	/**
	 * Sets actor's world attribute
	 * 
	 * @param world
	 *            the world to affect
	 */
	public void register(World world) {
		this.world = world;
	}

	/**
	 * Resets the world attribute to null
	 */
	public void unregister() {
		world = null;
	}

	/**
	 * Return the world attribute. World is the world the actor belongs to
	 * 
	 * @return World
	 */
	protected World getWorld() {
		return world;
	}

	/**
	 * Sets off the actor's reaction's when he receives a Damage
	 * 
	 * @param instigator
	 *            the actor who gives the damage
	 * @param type
	 *            The type of damage
	 * @param amount
	 *            the amount of damage
	 * @param location
	 *            the position where the damage has been given
	 * @return
	 */
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {
		return false;
	}

}