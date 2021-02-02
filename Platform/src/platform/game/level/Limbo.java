package platform.game.level;


import platform.circuit.And;
import platform.circuit.Not;
import platform.game.Background;
import platform.game.BinaryFireball;
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

public class Limbo extends Level{
	public void register(World world) {
        // TODO Auto-generated method stub
        super.register(world);
        
        world.setNextLevel(new Limbo());
        //---------Important Actors-------------------------------------------------
        
        Player player = new Player(new Vector(-2,-4), new Vector(0,0));
        world.register(new Background(new Box(Vector.ZERO,100,100), "night-background_PNG"));
//        world.register(new Slime(new Vector(-3,-4), new Vector(0,0)));
        world.register(player);
        world.register(new Overlay(player));
        world.register(new Limits(Vector.ZERO,100,100));
        
      
        Lever lever =new Lever(new Vector(5,1), 7);
        world.register(lever);
        
      //Trap and heal
        BinaryLever slimeLever = new BinaryLever(new Vector(18,-4));
        BinaryLever heartLever = new BinaryLever(new Vector(22,5));
        BinaryLever spikeLever = new BinaryLever(new Vector(14,7));

        world.register(slimeLever);
        world.register(heartLever);
        world.register(spikeLever);

        Torch firstTorch = new Torch(new Vector(22,10), false);
        Torch secondTorch = new Torch(new Vector(23,10), false);
        Torch thirdTorch = new Torch(new Vector(24,10), false);
        Torch fourthlTorch = new Torch(new Vector(25,10), false);
        
        world.register(firstTorch);
        world.register(secondTorch);
        world.register(thirdTorch);
        world.register(fourthlTorch);
        
        
        Key greenKey = new Key(new Vector(14,10.0),"key.green");
        world.register(greenKey);
        world.register(new Door(new Box(new Vector(-5,9),1,1), "lock.green", greenKey));
        
        
        Torch fireBallTorch = new Torch(new Vector(5,10), false);
        Torch heartTorch = new Torch(new Vector(2, 10), false);
        world.register(fireBallTorch);
        world.register(heartTorch);
        Key blueKey = new Key(new Vector(15,0), "key.blue");
        world.register(blueKey);
        world.register(new Door(new Box(new Vector(13,-4),1,1),"lock.blue",blueKey));
        Key exitKey = new Key(new Vector(-6, -4),"key.yellow");
        world.register(exitKey);
        world.register(new Exit(new Box(new Vector(-4, -4),1,1), exitKey, new Limbo()));
        
        
        //-------------------------------------------------------------------------------------------------
        
        
        //----------Near the start position-----
        
        //first column from the left under first floor
        for (int j = -4; j < 9; j++) {
            world.register(new Block(new Box(new Vector(-5,j),1,1), "castle.center"));
            
		}
        //first column from the left after the first floor
        for (int i = 10; i < 13; i++) {
            world.register(new Block(new Box(new Vector(-5,i),1,1), "castle.center"));

		}
        
        world.register(new Block(new Box(new Vector(-5,13),1,1), "castle.middle"));
        
        
        for (int j = -4; j < 13; j++) {
            world.register(new Block(new Box(new Vector(-10,j),1,1), "castle.center"));

		}
        world.register(new Block(new Box(new Vector(-10,13),1,1), "castle.middle"));

        for (int i = -10; i < -4; i++) {
			world.register(new WoodBox(new Vector(i,13), "box.empty", 1));
			world.register(new WoodBox(new Vector(i,14), "box.empty", 1));

        }
        
        
        world.register(new Fireball(new Vector(-6,10),new Vector(5,0),0.5,null));
        world.register(new Jumper(new Vector(-8.5,-4)));
        world.register(new Jumper(new Vector(-6.5,0)));
        world.register(new Jumper(new Vector(-8.5,3)));
        world.register(new Jumper(new Vector(-6.5,7.0)));
        world.register(new Jumper(new Vector(-8.5,10.5)));

        
        
        
        world.register(new Block(new Box(new Vector(10,14),1,1), "stone.1"));
        
        for (int i = -10; i < 30; i++) {
        	for(int j = -5; j > -20; j--)
        	if(j ==-5){
        		world.register(new Block(new Box(new Vector(i,j),1,1), "grass.middle"));
        	}else{
        		world.register(new Block(new Box(new Vector(i,j),1,1), "grass.center"));

        	}
		}
        world.register(new Spike(new Vector(0,-4),2,1));
        
        world.register(new Jumper(new Vector(2,-4)));
           
        
        
        world.register(new Block(new Box(new Vector(4,0),1,1), "stone.1"));
        world.register(new Block(new Box(new Vector(6,0),1,1), "stone.1"));
        
   	 	world.register(new Mover(new Vector(10,8), new Vector(10,6), 1, 1, "castle.middle", lever));
   	 	
   	 	//Stairs
        for (int i = 1; i < 5; i++) {
        	 world.register(new Mover(new Vector(5,-5+i), new Vector(i+5,i), 1, 1, "stone.1", lever));
		}
        world.register(new Block(new Box(new Vector(5,0),1,1), "stone.1"));
        
        //----------first floor ----------------------
        for (int i = -5; i < 10; i++) {
            world.register(new Block(new Box(new Vector(i,8),1,1), "castle.middle"));

		}
        for (int i =11; i < 30; i++) {
            world.register(new Block(new Box(new Vector(i,8),1,1), "castle.middle"));

		}
        //------------Woodboxes on the first floor-------------------------
        world.register(new WoodBox(new Vector(13,9), "box.double", 1));
        world.register(new WoodBox(new Vector(13,10), "box.double", 1));
        world.register(new WoodBox(new Vector(13,11), "box.double", 1));
        world.register(new WoodBox(new Vector(13,12), "box.double", 1));

        for (int i = -3; i < 9; i++) {	
            world.register(new Block(new Box(new Vector(13,i),1,1), "stone.1"));
		}
        
        //-------Boxes near the green key --------------------------------
        for (int i = 9; i < 13; i++) {
            world.register(new Block(new Box(new Vector(15,i),1,1), "stone.1"));
		}
        
        
        //--------------------3 Levers zone----------------------------
        //Vertical stones at the entry
        for (int i=-3 ; i< 9; i++) {
			world.register(new Block(new Box(new Vector(24,i),1,1), "stone.1"));

		}
        
        for (int i=8 ; i< 13; i++) {
        	if(i!=9){
        		world.register(new Block(new Box(new Vector(28,i),1,1), "stone.1"));
        	}
		}
        world.register(new Block(new Box(new Vector(27,12),1,1), "stone.1"));//first vertical column
        for (int i = -1; i < 5; i++) {
			world.register(new Block(new Box(new Vector(22,i),1,1), "stone.1"));
		}
        
        //horizontal stones at the entry....
        for (int i = 18; i < 23; i++) {
			world.register(new Block(new Box(new Vector(i,-3),1,1), "stone.1"));

		}
        //... and their vertical stones at the left
        for (int i = -2; i < 5; i++) {
			world.register(new Block(new Box(new Vector(18,i),1,1), "stone.1"));
		}
        
        
        world.register(new Spike(new Vector(14,1), 1, 1));
        world.register(new Spike(new Vector(15,1), 1, 1));
        
        //Trap slime / heart / final Mover
        world.register(new TransformBlock(new Box(new Vector(15,-2),1,1), "box.item", new Slime(new Vector(15,-2), Vector.ZERO), slimeLever));
        world.register(new TransformBlock(new Box(new Vector(19,5),1,1), "box.item", new Heart(new Vector(19,5), 40), heartLever));
        world.register(new Mover(new Vector(12, -4), new Vector(11,-3), 1, 1, "stone.1", new And(new And(slimeLever, spikeLever), spikeLever)));

        
              
        
       
        
        //---Torch Puzzle--------------------------------------------------
        world.register(new Block(new Vector(17,9), 0.5,0.5, "digit.1"));
        world.register(new Block(new Vector(18,9),0.5,0.5, "digit.0"));
        world.register(new Block(new Vector(19,9),0.5,0.5, "digit.1"));
        world.register(new Block(new Vector(20,9), 0.5,0.5, "digit.1"));
        
       

        world.register(new Door(new Box(new Vector(28, 9),1,1), "stone.1", new And(new And(firstTorch,new Not(secondTorch)), new And(thirdTorch, fourthlTorch))));

        world.register(new Block(new Box(new Vector(14,0),1,1), "stone.1"));
        world.register(new Jumper(new Vector(9,-3)));
        
        
        world.register(new Heart(new Vector(1, 9),4 ,heartTorch ));
        //Trap fireballs 
        for (int i = -4; i < 20; i++) {
        	world.register(new BinaryFireball(new Vector(i,12), new Vector(0,-1),0.5, null, fireBallTorch));
            world.register(new Block(new Box(new Vector(i,13),1,1), "castle.middle"));
        }
        
        world.register(new BinaryFireball(new Vector(-5,10),new Vector(12,0),0.5, null, fireBallTorch));
        
        world.register(new Slime(new Vector(0, 14), Vector.ZERO));
        world.register(new Slime(new Vector(6, 14), Vector.ZERO));
        world.register(new Slime(new Vector(12, 14), Vector.ZERO));
        world.register(new Slime(new Vector(17.5, 14), Vector.ZERO));

    }
}
