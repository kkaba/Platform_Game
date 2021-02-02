package platform.game.level;

import platform.game.Background;
import platform.game.BinaryLever;
import platform.game.Block;
import platform.game.Door;
import platform.game.Exit;
import platform.game.Fireball;
import platform.game.Heart;
import platform.game.Jumper;
import platform.game.Key;
import platform.game.Lever;
import platform.game.Limits;
import platform.game.Mover;
import platform.game.Overlay;
import platform.game.Player;
import platform.game.Slime;
import platform.game.Spike;
import platform.game.Torch;
import platform.game.TransformBlock;
import platform.game.WoodBox;
import platform.game.World;
import platform.util.Box;
import platform.util.Vector;

public class Hell extends Level {
    @Override
    public void register(World world) {
        // TODO Auto-generated method stub
        super.register(world);

        world.setNextLevel(new Hell());

        // Confirmation of step 2
        // ---------------------------------------------------------------------------------
        // Create Player
        Player p = new Player(new Vector(-2, 3), new Vector(0.0, 0.00));
        world.register(p);
        Overlay o = new Overlay(p);
        world.register(o);
        //Signals
        Torch torch = new Torch(new Vector(-2, -2.5), false);
        world.register(torch);
        Torch torchWood = new Torch(new Vector(30, -6), false);
        world.register(torchWood);
        
        //first mover
        BinaryLever leverMover = new BinaryLever(new Vector(28, -4));
        world.register(leverMover);
        //exit Mover
        BinaryLever leverMover2 = new BinaryLever(new Vector(-6, -8));
        world.register(leverMover2);
        
        
        //exit
        Lever leverExit = new Lever(new Vector(3, 9),7);
        world.register(leverExit);
        Key keyMovers = new Key(new Vector(-8,-2), "key.blue");
        world.register(keyMovers);
        
        // Create Blocks
        for (int i = 0; i < 5; i++) {
            world.register(new Block(new Box(new Vector(-6, -1 - i), 1, 1), "stone.broken.2"));
        }
        world.register(new Block(new Box(new Vector(-1.5, 0), 6, 1), "stone.broken.2"));
        world.register(new Block(new Vector(-6.5, -0.5), new Vector(-4.5, 5), "stone.broken.7"));
        // Create Jumper
        world.register(new Jumper(new Vector(1, 1)));
        // Create Spikes
        world.register(new Spike(new Vector(-1, 1), 1, 0.5));
        // Create Heart
        world.register(new Heart(new Vector(-4, 3), 5.0));
        // Limits
        world.register(new Limits(new Box(Vector.ZERO, 80, 80)));
        //bigger than limits
        world.register(new Background(new Box(Vector.ZERO,100,100), "night-background_PNG"));
        // -------------------------------------------------------------------------------------
        // torch
      
       

        for (int k = 1; k < 20; k += 2) {
            world.register(new Heart(new Vector(5 + k, -1), 5, torch));
        }
        for (int k = 1; k < 20; k += 4) {
            world.register(new Fireball(new Vector(4 + k, 3), new Vector(0, 0), 0.5, null));
        }

        // structure
        for (int i = 0; i < 14; i++) {
            world.register(new Block(new Box(new Vector(30, -5 + i), 1, 1), "stone.broken.2"));
        }
        for (int j = 0; j < 30; j++) {

            world.register(new Spike(new Vector(-5 + j, -4), 1, 0.5));
            world.register(new Block(new Box(new Vector(-5 + j, -5), 1, 1), "stone.broken.2"));
            world.register(new Block(new Box(new Vector(-6 + j, -9), 1, 1), "stone.broken.2"));
            world.register(new Block(new Box(new Vector(j, 8), 1, 1), "stone.broken.2"));

        }
        // torch permettant de passer des bloc durs aux blocs de bois
        
        for (int i = 24; i > 21; i--) {
            // --------------------------------------------------------------------
            // blocs juste avant Mover

            world.register(new Block(new Box(new Vector(i, -7), 1, 1), "stone.broken.2"));
            world.register(new Block(new Box(new Vector(i, -8), 1, 1), "stone.broken.2"));

            // passerelle
            world.register(new TransformBlock(new Box(new Vector(i, -6), 1, 1), "stone.broken.2",
                    new WoodBox(new Vector(i, -6), "box.double", 1), torchWood));
            


            // -----------------------------------------------------------------------
            // blocs passage ennemi

            for (int j = 8; j <= 24; j += 8) {

                world.register(new Block(new Box(new Vector(i - j, -6), 1, 1), "stone.broken.2"));

                world.register(new TransformBlock(new Box(new Vector(i - j, -7), 1, 1), "stone.broken.2",
                        new WoodBox(new Vector(i - j, -7), "box.double", 1), torchWood));

                world.register(new TransformBlock(new Box(new Vector(i - j, -8), 1, 1), "stone.broken.2",
                        new WoodBox(new Vector(i - j, -8), "box.double", 1), torchWood));
                
                

            }
//            
            
            // --------------------------------------------------------------------------
        }
        
        for (int i = 0; i < 17; i+= 8) {
			world.register(new Slime(new Vector(2 + i, -8), Vector.ZERO));
		}
        // 5 block platform on the right with lever below
        for (int i = 0; i < 5; i++) {
            world.register(new Block(new Box(new Vector(26 + i, -5), 1, 1), "stone.broken.2"));
        }
        
                world.register(new Mover(new Vector(28, -7.5), new Vector(26, -8.5), 3, 4, "stone.5", leverMover));
        
        //3 movers + exit mover
      
        world.register(new Mover(new Vector(-8, 1.75), new Vector(-11, 1.75), 3, 4.5, "stone.4", leverMover2));
        world.register(new Mover(new Vector(-8, -2.75), new Vector(-11, -2.75), 3, 4.5, "stone.4", leverMover2));
        world.register(new Mover(new Vector(-8, -7.25), new Vector(-11, -7.25), 3, 4.5, "stone.4", leverMover2));
        
        //exit mover
        world.register(new Mover(new Vector(-3.5, 6.75), new Vector(-7.5, 6.75), 6, 3.5, "stone.2", leverMover2));
        //Exit Door
        world.register(new Exit(new Box(new Vector(29, 9), 1, 1), leverExit, new Limbo()));
        
        for(int i = 0; i < 17; i+=8){
        	world.register(new Slime(new Vector(2 + i, 11), Vector.ZERO));        
        }
        
        world.register(new Door(new Box(new Vector(-10,4.5),1,1), "lock.blue", keyMovers));
        
    }
}
