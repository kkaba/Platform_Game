package platform.game;

import platform.circuit.Signal;
import platform.game.level.Level;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Exit extends Door {
	private Level level;
	private Box box;

	/**
	 * Creates a new Exit
	 * 
	 * @param box
	 *            the exit box
	 * @param signal
	 *            signal that unlocks the exit
	 * @param level
	 *            the next level to set
	 */
	public Exit(Box box, Signal signal, Level level) {
		super(box, "door.closed", signal);
		this.level = level;
		this.box = box;
	}

	@Override
	public Box getBox() {
		return box;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);

		if (!isActive()) {
			super.draw(input, output);
		} else {
			output.drawSprite(getSprite("door.open"), getBox());
		}

	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {

		switch (type) {
			case ACTIVATION :
				if (isActive()) {
					getWorld().setNextLevel(level);
					getWorld().nextLevel();
					return true;

				} else {
					return false;
				}

			default :
				return super.hurt(instigator, type, amount, location);

		}

	}

	@Override
	public int getPriority() {
		return 3;
	}

}
