package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Jumper extends Actor {
	private double cooldown;
	private Vector position;

	private String spriteName;
	private Vector velocity;

	public Jumper(Vector position) {
		super(position);
		this.position = position;
		velocity = new Vector(0.0, 0.0);
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return velocity;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public void update(Input input) {
		super.update(input);
		cooldown -= input.getDeltaTime();
	}

	@Override
	public Vector getPosition() {
		return position;
	}
	@Override
	public Box getBox() {
		// Use the constructor for the extreme points (x-0.5,y) & (x+0.5,y+0.5)
		return new Box(
				new Vector(getPosition().getX() - 0.5, getPosition().getY()),
				new Vector(getPosition().getX() + 0.5,
						getPosition().getY() + 0.5));
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);

		if (cooldown <= 0 && getBox().isColliding(other.getBox())) {
			Vector below = new Vector(getPosition().getX(),
					getPosition().getY() - 1.0);
			if (other.hurt(this, Damage.AIR, 10.0, below)) {
				cooldown = 0.5;
			}
		}
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		if (cooldown > 0) {
			spriteName = "jumper.extended";
		} else {
			spriteName = "jumper.normal";
		}
		output.drawSprite(getSprite(spriteName), getBox());
	}

}
