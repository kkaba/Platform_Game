 package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor {

	private Box box;
	private Sprite sprite;
	private String name;
	
	
	/** For more freedom we declared three different constructors**/
	  
	 
	/**
	 * Creates a new Block
	 *	@param min lower-left corner, not null
     * @param max upper-right corner, not null
	 * @param name block's sprite name
	 */
	public Block(Vector min, Vector max, String name) {
		super(min, max);
		
		box = new Box(min, max);
		this.name = name;
	}
	
	
	/**
	 * Creates a new Block
	 * @param box the block's box
	 * @param name block's sprite name
	 */
	public Block(Box box, String name) {
		super(box);
		if (box == null) {
			throw new NullPointerException();
		}
		this.box = box;
		this.name = name;

	}
	
	
	/**
	 * Creates a new Box
	 * @param center center of the block
	 * @param width	horizontal size
	 * @param height vertical size 
	 * @param name	block's sprite name
	 */
	public Block(Vector center, double width, double height, String name) {
		super(center);
		
		box = new Box(center, width, height);
		this.name = name;

	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		sprite = getSprite(name);
		output.drawSprite(sprite, getBox());
	}

	@Override
	public Vector getPosition() {
		return box.getCenter();
	}

	@Override
	// +0.05 otherwise fireballs go through the blocks
	public Box getBox() {
		return new Box(getPosition(),box.getWidth()+0.05,box.getHeight());
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
