package platform.game;

import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class BinaryLever extends Actor implements Signal {
	private boolean value;
	private Vector position;

	public BinaryLever(Vector position) {
		super(position);

		this.position = position;
		value = false;
	}

	@Override
	public boolean isActive() {
		return value;
	}

	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public Box getBox() {
		return new Box(getPosition(), 1, 1);
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		if (isActive()) {
			output.drawSprite(getSprite("lever.right"), getBox());
		} else {
			output.drawSprite(getSprite("lever.left"), getBox());
		}
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {

		switch (type) {
			case ACTIVATION :

				if (amount > 0) {
					// condition to switch the signal on/off
					if (!isActive()) {
						value = true;
					} else {
						value = false;
					}
				}
				return true;

			default :
				return super.hurt(instigator, type, amount, location);
		}
	}

	@Override
	public int getPriority() {
		return 3;
	}
}
