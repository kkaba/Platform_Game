package platform.game.level;

import platform.game.Jumper;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.World;
import platform.game.Block;
import platform.util.Box;
import platform.util.Vector;

public class BasicLevel extends Level {
    // UNCOMMENT ME WHEN NEEDED
    @Override
    public void register(World world) {
        super.register(world);
        
        // Register a new instance, to restart level automatically
        world.setNextLevel(new BasicLevel());
        
        // Create blocks
        
        world.register(new Block(new Box(new Vector(0, 0), 10, 2), "stone.broken.2"));
        world.register(new Block(new Box(new Vector(-1.5, 1.5), 1, 1), "stone.broken.2"));
        world.register(new Spike(new Vector(1.0,0.0), 1, 1));
        Player p = new Player(new Vector(0.0,0.0) , new Vector(0.0,0.00));
       world.register(p);
       world.register(new Jumper(new Vector(-2.0,2.0)));
       world.register(new Torch(new Vector(1.0,2.5),false));
     //  world.register(new Fireball(new Vector(0.0,0.0), new Vector(1,0) ,4,this));
       world.register(new Limits(new Box(Vector.ZERO,40,30)));
       Overlay o = new Overlay(p);
       world.register(o);
       
    }
    
}
