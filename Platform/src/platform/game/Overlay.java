package platform.game;


import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Overlay extends Actor{
	private Player player;
	private Vector position;
	private double size;
	
	public Overlay(Player player){
		super();
		if (player == null) {
			throw new NullPointerException();
		}
		this.player =  player;
		size = player.getBox().getHeight() / 3.0;
		this.position = new Vector(player.getBox().getMin().getX() - (size / 4.0 ),player .getBox().getMax().getY() + size);

	}
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 6;
	}
	
	@Override
	public Vector getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	public Box getBox(){
		return new Box(getPosition(), size,size);
	}
	@Override
	public void update(Input input) {
		super.update(input);
		//Manipulations with size are here to adjust the overlay
		this.position = new Vector(player.getBox().getMin().getX() - (size / 4.0) ,player .getBox().getMax().getY() +size);

	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);

		double health = 5.0* player.getHealth()/player.getMaxHealth();
		for (int i = 1; i <= 5;i++) {
			String name;
			if (health>= i) {
				name = "heart.full";
			}else if(health >= i - 0.5){
				name = "heart.half";
			}else{
				name = "heart.empty";
			}
			
			//we substract 0.2 to adjust the hearts
			output.drawSprite(getSprite(name), new Box(new Vector(position.getX() + size * i - 0.2, position.getY()),size,size));
			

			
		}
	}

}
