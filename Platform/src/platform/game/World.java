package platform.game;

import platform.game.level.Level;
import platform.util.Box;
import platform.util.Loader;
import platform.util.Vector;

/**
 * Represents an environment populated by actors.
 */
public interface World {
	
    /** @return associated loader, not null */
    public Loader getLoader();
    
    /**
     * Set viewport location and size.
     * @param center viewport center, not null
     * @param radius viewport radius, positive
     */
    public void setView(Vector center, double radius);
    
    public void register(Actor actor);
    
    public void unregister(Actor actor);
    
    public Vector getGravity();
    
    public void nextLevel();
    
    public void setNextLevel(Level level);
    
    public int hurt(Box area, Actor instigator, Damage type, double amount, Vector location);
}
