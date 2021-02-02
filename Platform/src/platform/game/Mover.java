package platform.game;

import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class Mover extends Block implements Signal {
	private Vector start;
	private Vector end;
	private Signal signal;
	private double current;
	private double width;
	private double height;
	
	public Mover(Vector start, Vector end, double width, double height, String name,Signal signal) {
		super(start, width, height, name);
		if (end == null) {
			throw new NullPointerException();
		}
		
		this.start = start;
		this.end = end;
		this.signal = signal;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Vector getPosition() {
		return start.mixed(end, current);
	}
	
	@Override
	public Box getBox() {
		return new Box(getPosition(),width,height);
	}

	@Override
	public void update(Input input) {
		super.update(input);
		if (isActive()) {
			current += input.getDeltaTime();
			if (current > 1.0) {
				current = 1.0;
			}
		} else {
			current -= input.getDeltaTime();
			if (current < 0.0) {
				current = 0.0;
			}
		}
	}
	
	@Override
	public boolean isActive() {
		return signal.isActive();
	}

}
