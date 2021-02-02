package platform.game;

import java.awt.event.KeyEvent;


import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Player extends Character {
	private Vector position;
	private Vector velocity;
	private boolean colliding;
	private double health;
	
	//Variables to handle the sprite variation
	private boolean hit;
	private double hitCooldown;
	private double hitTimer;
	
	public Player(Vector position, Vector velocity) {
		super(position, velocity, 20.0);
		this.position = position;
		this.velocity = velocity;
		health = getMaxHealth();
		hit = false;
		hitCooldown = 0.5;
		hitTimer = 0.0;
	}

	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public Box getBox() {
		return new Box(getPosition(), 0.5, 0.5);
	}

	@Override
	public int getPriority() {
		return 6;
	}
	
	public Vector getVelocity(){
		return velocity;
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		String spriteName;
		
		if(hit){
			spriteName = "blocker.sad";
		}else{
			spriteName = "blocker.happy"; 
		}
		output.drawSprite(getSprite(spriteName), getBox());
	}

	@Override
	public void preUpdate(Input input) {
		super.preUpdate(input);
		colliding = false;
	}

	@Override
	public void update(Input input) {
		super.update(input);
		
		double delta = input.getDeltaTime();
		Vector acceleration = getWorld().getGravity();
		double maxSpeed = 4.0;
		
		hitTimer -= input.getDeltaTime();
		

		if (hitTimer > 0) {
			hit = true;
		}else{
			hit = false;
		}
		
		//Reduces the speed to simulate the friction
		if (colliding) {
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity = getVelocity().mul(scale);
		}
		
		//Run to the right
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()) {
			if (getVelocity().getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime();
				double speed = getVelocity().getX() + increase;
				if (speed > maxSpeed) {
					speed = maxSpeed;
				}
				velocity = new Vector(speed, getVelocity().getY());
			}

		}  
		
		//Run to the left
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) {

			if (velocity.getX() > -maxSpeed) {

				double decrease = -60.0 * input.getDeltaTime();
				double speed = getVelocity().getX() + decrease;

				if (speed < -maxSpeed) {
					speed = -maxSpeed;
				}

				velocity = new Vector(speed, getVelocity().getY());
			}
		}  
		
		
		//jump
		if ((input.getKeyboardButton(KeyEvent.VK_UP).isPressed())
				&& colliding) {
			
			velocity = new Vector(getVelocity().getX(), 7.0);
			colliding = false;
			
		}  
		
		//Releases fire
		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()){ 
			this.getWorld().register(new Fireball(this.getPosition(), this.velocity.add(getVelocity().resized(2.0)), 0.4, this));
		}
		
		//Air damage
		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed()){
			getWorld().hurt(getBox(), this , Damage.AIR, 1.0,
			getPosition());
		}
		
		//Activation Damage
		if (input.getKeyboardButton(KeyEvent.VK_E).isPressed()) {
			getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1, getPosition());
		}
		
		//Velocity and position update
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(getVelocity().mul(delta));
		
	}
	
	
	
	@Override
	public void postUpdate(Input input){
		super.postUpdate(input);
		
		if (getHealth() <= 0) {
			die();
		}
		getWorld().setView(getPosition(), 8);	

	}
	
	
	private void die(){
		getWorld().nextLevel();
		this.getWorld().unregister(this);
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		
		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(getBox());
			if (delta != null) {
				colliding = true;
				position = getPosition().add(delta);
				if (delta.getX() != 0.0)
					velocity = new Vector(0.0, getVelocity().getY());
				if (delta.getY() != 0.0)
					velocity = new Vector(getVelocity().getX(), 0.0);
			}
		}
		
	
		
		
	}
	
	
	public double getHealth(){
		return this.health;
	}
	

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {
		
		switch(type){
		case AIR : 
			if (instigator != this) {
				velocity = getPosition().sub(location).resized(amount);
				return true;
			}else{
				return false;
			}
			
		
		case FIRE:
			
			health-= amount;
			hitTimer = hitCooldown;
			return true;
			
		case HEAL:
			health+= amount;
			return true;
			
			case VOID:
				health = 0;
				hit = true;
				return true;
				
			case PHYSICAL:
				health -= amount;
				hit = true;
				return true;
				
			case ACTIVATION:
				if (instigator != this) {
					instigator.hurt(this, Damage.ACTIVATION, amount, location);
					return true;
				}else{
					return false;
				}
				
			default:
				return super.hurt(instigator, type, amount, location);
		}
		
		
	}

}
