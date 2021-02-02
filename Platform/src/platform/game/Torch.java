package platform.game;

import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Torch extends Actor implements Signal {
	private Vector position;

	private boolean lit;
	private double size = 0.8;
	private double variation;

	/**
	 * Creates a new Torch
	 * 
	 * @param position
	 *            torch's position
	 * @param lit
	 *            boolean true for lit ,false otherwise
	 */
	public Torch(Vector position, boolean lit) {
		super(position);
		this.position = position;
		this.lit = lit;
		variation = 0;
	}

	@Override
	public Box getBox() {
		return new Box(position, size, size);
	}

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		super.update(input);
		variation -= input.getDeltaTime();

		if (variation < 0.0) {
			variation = 0.6;
		}
	}

	@Override
	public void draw(Input input, Output output) {

		if (lit) {

			String name = "torch.lit.1";

			if (variation < 0.3) {
				name = "torch.lit.2";
			}

			output.drawSprite(getSprite(name), getBox());
		} else {
			output.drawSprite(getSprite("torch"), getBox());
		}

	}

	@Override
	public int getPriority() {
		return 1;
	}

	@Override
	public boolean isActive() {
		return lit;
	}
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {
		super.hurt(instigator, type, amount, location);

		switch (type) {
			case FIRE :
				if (lit) {
					return false;
				} else {
					lit = true;
					return true;
				}

			case AIR :
				lit = false;
				return true;

			default :
				return super.hurt(instigator, type, amount, location);

		}
	}

}
