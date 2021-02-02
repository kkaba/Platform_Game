package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class WoodBox extends Actor{
	private String name;
	private double size;
	private Vector position;
	
	//Particles that will be destroyed when the Woodbox is destroyed
	//We decided to take three particles for the aesthetic result
	private Particle smoke ;
	private Particle smoke1;
	private Particle smoke2;

	public WoodBox(Vector position, String name, double size) {
		super(position);
		
		this.position = position;
		this.name = name;
		this.size = size;
		
		smoke = new Particle(getPosition());
		smoke1 = new Particle(getPosition());
		smoke2 = new Particle(getPosition());
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		output.drawSprite(getSprite(name), getBox());
	}	
	@Override
	public Vector getPosition() {
		return position;
	}
	
	@Override
	public Box getBox() {
		return new Box(getPosition(), size, size);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
	@Override
	public int getPriority() {
		return 0;
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
	}
	
	@Override
	public void update(Input input) {
		
		super.update(input);
	}
	
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount,
			Vector location) {
		
		
		switch (type) {
		case FIRE:
			smoke.setSize(size);
			smoke.setDuration(1.0);
			
			getWorld().register(smoke);
			smoke.register(getWorld());
		
			smoke.setSprite("smoke.white.3");
			getWorld().unregister(this);
			
			smoke1.setSize(size/2);
			smoke1.setDuration(1.0);
			
			getWorld().register(smoke1);
			smoke1.register(getWorld());
		
			smoke1.setSprite("smoke.white.2");
			getWorld().unregister(this);
			
			smoke2.setSize(size/3);
			smoke2.setDuration(1.0);
			
			getWorld().register(smoke2);
			smoke2.register(getWorld());
		
			smoke2.setSprite("smoke.white.1");
			getWorld().unregister(this);
			return true;

		default:
			return super.hurt(instigator, type, amount, location);
		}
	}
		

}
