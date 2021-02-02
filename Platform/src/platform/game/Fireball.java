package platform.game;

import platform.circuit.Constant;
import platform.util.Vector;

public class Fireball extends BinaryFireball {



	public Fireball(Vector position, Vector velocity, final double SIZE,
			Actor owner) {
		super(position, velocity , SIZE, owner, new Constant(true));

		
	
	}



}
