package platform.game.level;
 
import platform.game.Block;
import platform.game.Exit;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.Lever;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;
 
public class BasicInteract  extends Level{
 
    @Override
    public void register(World world) {
        // TODO Auto-generated method stub
        super.register(world);
         
        world.setNextLevel(new BasicInteract());
         
        //Create Player
        Player p = new Player(new Vector(0.0,0.0) , new Vector(0.0,0.00));
        world.register(p);
        Overlay o = new Overlay(p);
        world.register(o);
        //Create Blocks
        world.register(new Block(new Box(new Vector(0,0),6,2), "stone.broken.2"));
        world.register(new Block(new Vector(-5, -1),new Vector(-3,5), "stone.broken.7"));
        //Create Jumper
        world.register(new Jumper(new Vector(-2.5,1)));
        //Create Spikes
        world.register(new Spike(new Vector(-1.5,1), 1, 0.5));
        //Create Heart
        world.register(new Heart(new Vector(-4,7), 5.0));
        //Limits
        world.register(new Limits(new Box(Vector.ZERO,40,30)));
        //Torch
        Lever leverExit = new Lever(new Vector(1,1.5),7);
        world.register(leverExit);
          world.register(new Torch(new Vector(1.0,2.5),false));
          world.register(new Exit(new Box(new Vector(-4, 5.5), 1, 1), leverExit, new Hell()));
    }
} 