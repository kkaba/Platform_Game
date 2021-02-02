package platform.game;

import platform.circuit.Constant;
import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Heart extends Actor implements Signal {
	private double size;
	private Vector position;
	private double cooldown;
	private double count;
	private Signal signal;
	private final double lifePoints;

	/**
	 * Creates a new Heart
	 * 
	 * @param position
	 *            heart's position
	 * @param cooldown
	 *            time during which the heart is inactive
	 */
	public Heart(Vector position, double cooldown) {
		super(position);
		this.position = position;
		this.cooldown = cooldown;
		count = 0;
		size = 0.5;
		signal = new Constant(true);
		lifePoints = 3.0;
	}

	/**
	 * Creates a new Heart
	 * 
	 * @param position
	 *            heart's position
	 * @param cooldown
	 *            time during which the heart is inactive
	 * @param signal
	 *            signal that activates the heart
	 */
	public Heart(Vector position, double cooldown, Signal signal) {
		this(position, cooldown);
		this.signal = signal;
	}

	@Override
	public int getPriority() {
		return 8;
	}

	@Override
	public void update(Input input) {
		super.update(input);
		double dt = input.getDeltaTime();
		if (count > 0) {
			count -= dt;
		} else {
			count = 0;
		}
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		String name;
		if (signal.isActive()) {
			if (count <= 0) {
				name = "heart.full";
			} else if (count < cooldown / 2.0) {// at the half of the cooldown
												// on
				name = "heart.half";
			} else {
				name = "heart.empty";
			}
			output.drawSprite(getSprite(name), getBox());
		}
	}

	@Override
	public Box getBox() {
		return new Box(position, size, size);
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);

		if (isActive()) {
			if (getBox().isColliding(other.getBox())) {

				// at the half of the cooldown, the half of LifePoints are given
				// to other
				if (count <= cooldown / 2.0) {
					double amount;

					if (count <= 0) {// the cooldown is over
						amount = lifePoints;
					} else {
						amount = lifePoints / 2.0;
					}
					// reset the count if other reacted to the Heal damage
					if (other.hurt(this, Damage.HEAL, amount, getPosition())) {
						count = cooldown;
					}
				}
			}
		}
	}

	@Override
	public boolean isActive() {
		return signal.isActive();
	}
}
