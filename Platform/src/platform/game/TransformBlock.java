package platform.game;

import platform.circuit.Signal;
import platform.util.Box;
import platform.util.Input;

public class TransformBlock extends Block implements Signal {
    private Signal s;
    private Actor a;

    public TransformBlock(Box box,
            String name, Actor a, Signal s) {
        super(box, name);
        if (a == null) {
			throw new NullPointerException();
		}
        this.a = a;
        this.s = s;

    }
    
    @Override
    public void update(Input input) {
        if (isActive()) {
            getWorld().register(a);         
            getWorld().unregister(this);
        }

    }
    
    @Override
    public boolean isActive() {
    	// TODO Auto-generated method stub
    	return s.isActive();
    }
}