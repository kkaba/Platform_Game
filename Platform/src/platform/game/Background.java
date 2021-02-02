package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

public class Background extends Actor{
    private Box box;
    private String name;
   
    public Background(Box box, String name){
        super(box);
        this.box = new Box(box.getMin(), box.getMax());
        this.name=name;
    }
   
    @Override
    public void draw(Input input, Output output){
        super.draw(input, output);
        output.drawSprite(getSprite(name), getBox());
    }
    public Box getBox(){
        return this.box;
    }

    @Override
    public int getPriority() {
        // TODO Auto-generated method stub
        return -2;
    }

}

