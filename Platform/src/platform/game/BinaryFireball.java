package platform.game;

import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class BinaryFireball extends Actor implements Signal {
	private Signal signal;
	private final Vector initialPosition;
	private final Vector initialVelocity;
	private Vector position;
	private Vector velocity;
	private Actor owner;
	private final double SIZE;
	public BinaryFireball(Vector position, Vector velocity, double SIZE,
			Actor owner, Signal signal) {
		
		super(position, velocity);
		this.signal = signal;
		this.owner = owner;
		this.initialPosition = position;
		this.initialVelocity = velocity;
		this.position = initialPosition;
		this.velocity = initialVelocity;
		this.SIZE = SIZE;
	}

	@Override
	public Vector getPosition() {
		// TODO Auto-generated method stub
		if (isActive()) {
			return position;
		} else {
			return initialPosition;
		}
	}

	@Override
	public Box getBox() {
		return new Box(getPosition(), SIZE, SIZE);
	}

	public Vector getVelocity() {
		if (isActive()) {
			return velocity;
		} else {
			return initialVelocity;
		}
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 9;
	}
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return signal.isActive();
	}

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		if (isActive()) {
			super.update(input);

			double delta = input.getDeltaTime();
			velocity = getVelocity().add(getWorld().getGravity().mul(delta));
			position = getPosition().add(getVelocity().mul(delta));
		} else {
			position = initialPosition;
			velocity = initialVelocity;
		}
	}

	@Override
	public void draw(Input input, Output output) {
		// TODO Auto-generated method stub
		if (isActive()) {
			super.draw(input, output);
			output.drawSprite(getSprite("fireball"), getBox(), input.getTime());

		}
	}
	
	@Override
	public void interact(Actor other) {
		if (isActive()) {
			super.interact(other);

			// Checks if the fireball must give fire damage or not
			if (getBox().isColliding(other.getBox())) {
				if ((other != owner)) {
					if (other.hurt(this, Damage.FIRE, 5.0, getPosition())) {
						this.getWorld().unregister(this);
					}
				}
			}
			// Checks if the fireball must bounce
			if (other.isSolid()) {
				Vector delta = other.getBox().getCollision(position);
				if (delta != null) {
					position = getPosition().add(delta);
					velocity = getVelocity().mirrored(delta);
				}
			}
		}
	}
}
