package platform.game;

import platform.util.Box;
import platform.util.Vector;

public class Limits extends Actor{
	private Box box;
	private Vector position;
	private double width;
	private double height;
	/**
	 * Creates a new Limit
	 * @param position	Limit's center 
	 * @param width	horizontal size
	 * @param height	vertical size
	 */
	public Limits(Vector position, double width, double height){
		super(position);
		
		this.width = width;
		this.height = height;
		this.position = position;
		this.box = new Box(position, width, height);
		
	}
	
	/**
	 * Creates a new Limit
	 * @param box limits'box
	 */
	public Limits(Box box){
		if(box == null){
			throw new NullPointerException();
		}
		position = box.getCenter();
		width = box.getWidth();
		height = box.getHeight();
		
	}
	
	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public int getPriority() {
		return 10;
	}
	
	@Override
	public Box getBox() {
		return new Box(getPosition(), width, height);
	}
	
	@Override
	public void interact(Actor other) {
		//Infinite damage if other is out of limits
		if (!(this.getBox().isColliding(other.getBox()))) {
			other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);

		}
	}
	
	
}
