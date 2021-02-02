package platform.game;

import platform.circuit.Signal;
import platform.util.Input;
import platform.util.Vector;
//Lever extends Binary lever because the have similar behaviors 
//and the Lever needs two more variables than lever : duration and time
//Otherwise the Lever Class behaves like Lever for the display
public class Lever extends BinaryLever implements Signal {
	private boolean value;
	private double duration;
	private double time;

	/**
	 * Creates a new Lever
	 * 
	 * @param position
	 *            center of lever's box
	 * @param duration
	 *            time during which the lever is active
	 */
	public Lever(Vector position, double duration) {
		super(position);
		value = false;
		this.duration = duration;
		time = 0.0;
	}

	@Override
	public boolean isActive() {
		return value;
	}

	@Override
	public void update(Input input) {
		time -= input.getDeltaTime();
		if (time <= 0) {
			value = false;
		}
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {

		switch (type) {
			case ACTIVATION :
				if (amount >= 1) {
					value = true;
					time = duration;
					return true;
				}

			default :
				return super.hurt(instigator, type, amount, location);
		}
	}
}
