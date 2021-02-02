package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Particle extends Actor {

	private Sprite sprite;
	private Vector position;
	private double angle;
	private double duration;
	private double time;
	private double width;
	private double height;

	/**
	 * Creates a new Particle
	 * 
	 * @param position
	 *            particle's center
	 */
	public Particle(Vector position) {
		super(position);
		this.position = position;
	}

	/**
	 * Sets the particle's size
	 * 
	 * @param size
	 *            particle's width and height
	 */
	public void setSize(double size) {
		width = size;
		height = size;

	}

	/**
	 * Sets the particle's size
	 * 
	 * @param width
	 *            horizontal size
	 * @param height
	 *            vertical size
	 */
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Duration during which the particle exists in the world and is visible
	 * 
	 * @param duration
	 *            duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Sets the particle's sprite
	 * 
	 * @param name
	 *            sprite name
	 */
	public void setSprite(String name) {
		sprite = getSprite(name);
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);

		output.drawSprite(sprite, getBox(), angle, duration - time);

	}

	
	@Override
	public void update(Input input) {
		super.update(input);

		time += input.getDeltaTime();
		if (time >= duration) {
			getWorld().unregister(this);
		}

	}

	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box(getPosition(), width, height);
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
