package platform.game;

import platform.util.Box;
import platform.util.Direction;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Slime extends Character {
	private Vector position;
	private Vector velocity;
	private double health;

	private double variation;
	private double animationTime;
	private boolean colliding;
	private final Box surroundingBox;
	private Direction direction;

	/**
	 * Creates a new Slime
	 * 
	 * @param position
	 *            slime's position
	 * @param velocity
	 *            slime's inital velocity
	 */
	public Slime(Vector position, Vector velocity) {

		super(position, velocity, 15);
		this.position = position;
		this.velocity = velocity;
		variation = 0.0;
		animationTime = 0.5;
		direction = Direction.RIGHT;
		this.surroundingBox = new Box(position, 3, 3);
		health = getMaxHealth();
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

	public double getHealth() {
		return health;
	}

	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box(getPosition(), 1, 1);
	}

	public Box getSurroundingBox() {
		return surroundingBox;
	}
	@Override
	public Vector getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return velocity;
	}

	@Override
	public void preUpdate(Input input) {
		// TODO Auto-generated method stub
		super.preUpdate(input);
		colliding = false;
	}

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		super.update(input);

		variation -= input.getDeltaTime();
		if (variation < 0.0) {
			variation = animationTime;
		}

		double delta = input.getDeltaTime();
		Vector acceleration = getWorld().getGravity();

		if (colliding) {
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity = getVelocity().mul(scale);
		}

		// check that the slime is in the right half of the surrounding box
		if (getPosition().getX() > getSurroundingBox().getMax().getX()) {
			direction = Direction.LEFT;
		} else if (getPosition().getX() < getSurroundingBox().getMin().getX()) {
			direction = Direction.RIGHT;
		}

		switch (direction) {
			case RIGHT :
				velocity = new Vector(1.0, getVelocity().getY());
				break;

			case LEFT :
				velocity = new Vector(-1.0, getVelocity().getY());
				break;

			default :
				break;

		}
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(getVelocity().mul(delta));

	}

	@Override
	public void postUpdate(Input input) {
		// TODO Auto-generated method stub
		super.postUpdate(input);
		if (health <= 0) {
			die();
		}
	}

	private void die() {
		getWorld().unregister(this);
	}
	@Override
	public void interact(Actor other) {

		super.interact(other);
		if (other.isSolid() && !(other instanceof Player)) {
			
			Vector delta = other.getBox().getCollision(getBox());
			if (delta != null) {
				
				colliding = true;
				position = getPosition().add(delta);
				
				//If the slime touches an object it will go in the opposite direction
				if (delta.getX() != 0.0) {
					
					velocity = new Vector(0.0, getVelocity().getY());
					if (direction == Direction.RIGHT) {
						
						direction = Direction.LEFT;
					} else {
						
						direction = Direction.RIGHT;
					}
				}
				
				if (delta.getY() != 0.0){
					velocity = new Vector(getVelocity().getX(), 0.0);
				}
			}
		}

		if (getBox().isColliding(other.getBox())) {
			other.hurt(this, Damage.PHYSICAL, 5, getPosition());
			other.hurt(this, Damage.AIR, 2, getPosition());
		}

	}
	@Override
	public void draw(Input input, Output output) {
		// TODO Auto-generated method stub
		super.draw(input, output);

		String spriteName;

		if (getVelocity().getX() >= 0.0) {
			spriteName = "slime.right.";
		} else {
			spriteName = "slime.left.";
		}

		if (getHealth() <= getMaxHealth() / 3.0) {
			spriteName += "3";
		} else if (getHealth() <= 2 * getMaxHealth() / (3.0)) {
			spriteName += "2";
		} else {
			spriteName += "1";
		}
		output.drawSprite(getSprite(spriteName), getBox());
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {
		// TODO Auto-generated method stub
		switch (type) {
			case FIRE :
				health -= 5;
				return true;

			default :
				return super.hurt(this, Damage.PHYSICAL, 2, getPosition());
		}
	}

}
