package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Spike extends Actor {
	private Vector position;
	private double width;
	private double height;
	private Vector velocity;
	private double cooldown;
	private double time ;
	
	public Spike(Vector position, double width, double height) {
		super(position);
		this.position = position;
		this.width = width;
		this.height = height;
		this.cooldown = 1.0;
		time = 0.0;
		velocity = new Vector(0.0, 0.0);
	}

	@Override
	public Box getBox() {
		
			return new Box(position, width, height);
	}
	@Override
	public int getPriority() {
				return 7;
	}

	@Override
	public boolean isSolid() {
		return true;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime();
		Vector acceleration = getWorld().getGravity();
		 velocity = velocity .add( acceleration .mul(delta)) ;
		 position = position .add( velocity .mul(delta)) ;

		time -= input.getDeltaTime();

	}
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(getSprite("spikes"), getBox());
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);

		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(getBox());

			if (delta != null) {
				position = position.add(delta);
				if (delta.getX() != 0.0)
					velocity = new Vector(0.0, velocity.getY());
				if (delta.getY() != 0.0)
					velocity = new Vector(velocity.getX(), 0.0);
			}
		} 
		
		if (getBox().isColliding(other.getBox())) {
			
			if (other.getVelocity().getY() < 0 && time < 0) {	

				if(other.hurt(this, Damage.PHYSICAL, 3, getPosition())){
					time = cooldown;
				}
				
			}
			other.hurt(this, Damage.AIR, 9, getPosition());
		
		}
		
		

	}
}
