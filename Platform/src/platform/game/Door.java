package platform.game;

import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

public class Door extends Block implements Signal {

	private Signal signal;

	/**
	 * Creates a new Door
	 * 
	 * @param box
	 *            Door's box
	 * @param name
	 *            door's sprite name
	 * @param signal
	 *            the signal to activate in order to unlock the door
	 */
	public Door(Box box, String name, Signal signal) {
		super(box, name);
		this.signal = signal;
	}

	@Override
	public boolean isSolid() {
		if (!isActive()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean isActive() {
		return signal.isActive();
	}

	@Override
	public Box getBox() {
		return super.getBox();
	}

	@Override
	public void draw(Input input, Output output) {
		if (!isActive()) {
			super.draw(input, output);
		}
	}

	@Override
	public int getPriority() {
		return 2;
	}
}
