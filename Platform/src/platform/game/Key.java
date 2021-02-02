package platform.game;

import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Key extends Actor implements Signal{
	private boolean taken;
	private Vector position;
	private String spriteName;
	
	/**
	 * Creates a new key
	 * @param position	key's box center
	 * @param spriteName	key's sprite name
	 */
	public Key(Vector position, String spriteName){
		super(position);
		this.position = position;
		taken = false;
		this.spriteName = spriteName;
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
	public boolean isActive() {
		return taken;
	}
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (getBox().isColliding(other.getBox())) {
			other.hurt(this, Damage.ACTIVATION, 1, getPosition());

		}
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {
		
		switch (type) {
			case ACTIVATION :
				taken = true;
				getWorld().unregister(this);
				return true;

			default :
				return super.hurt(instigator, type, amount, location);
		}
		
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(getSprite(spriteName), getBox());
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 7;
	}
	
}
