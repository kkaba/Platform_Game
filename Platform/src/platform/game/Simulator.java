package platform.game;

import java.util.ArrayList;
import java.util.List;

import platform.game.level.Level;
import platform.game.level.BasicInteract;
import platform.util.Box;
import platform.util.Input;
import platform.util.Loader;
import platform.util.Output;
import platform.util.SortedCollection;
import platform.util.Vector;
import platform.util.View;
/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World {

    private Loader loader;
	private Vector gravity;
    private Vector currentCenter;
	private double currentRadius;
	private Vector expectedCenter;
	private double expectedRadius;
	
	private SortedCollection<Actor>  actors;
    private List<Actor> registered;
    private List<Actor> unregistered;
    private boolean transition;
    private Level nextLevel;

	/**
     * Create a new simulator.
     * @param loader associated loader, not null
     * @param args level arguments, not null
     */
	public Simulator(Loader loader, String[] args) {
		
        if (loader == null)
            throw new NullPointerException();
        this.loader = loader;
        
       
        currentCenter = Vector.ZERO;
        currentRadius = 10.0;
        expectedCenter = currentCenter;
        expectedRadius = currentRadius;
        gravity = new Vector(0.0, -9.81);
        
        //Initialisation des attributs pour les acteurs
        actors = new SortedCollection<Actor>(); 
        registered = new ArrayList<Actor>();
        unregistered = new ArrayList<Actor>();
        nextLevel = new BasicInteract();
        transition = true;
        
	}
	
    /**
     * Simulate a single step of the simulation.
     * @param input input object to use, not null
     * @param output output object to use, not null
     */
	public void update(Input input, Output output) {
        
		double factor = 0.005 ;
		currentCenter = currentCenter.mul(1.0 -factor).add(expectedCenter.mul(factor)) ;
		currentRadius = currentRadius * (1.0 - factor ) + expectedRadius * factor;
		
		View view = new View(input, output);
		view.setTarget(currentCenter, currentRadius);
	
		for(Actor actor: actors){
			actor.preUpdate(input);
		}
		
		for(Actor actor : actors){
			for(Actor other: actors){
				if (actor.getPriority() > other.getPriority()) {
					actor.interact(other);
				}
			}
		}
		
		for(Actor a : actors){
			a.update(view);
		}
		

		for(Actor a: actors.descending()){
			a.draw(view, view);
		}
		
		
		for(Actor a : actors){
			a.postUpdate(input);
		}
		
		for (int i = 0; i < registered.size(); i++) {
			Actor actor = registered.get(i);
			if(!actors.contains(actor)){
				actor.register(this);

				actors.add(actor);
			}
		}
		
		registered.clear();
		
		for (int j = 0; j < unregistered.size(); j++) {
			Actor actor = unregistered.get(j);
			actors.remove(actor);
			actor.unregister();
			
		}
		
		unregistered.clear();
		
		//Check si passage au level suivant
		if (transition) {
			if (nextLevel == null) {
			nextLevel = Level.createDefaultLevel();
			}
			
			Level level = nextLevel;
			transition = false;
			nextLevel = null;
			actors.clear();
			registered.clear();
			// old actors are unregisterd:
			unregistered.clear();
			register(level);
		}
	}
	
	@Override 
	public void nextLevel(){
		transition = true;
	}
	@Override
	public void setNextLevel(Level level){
		nextLevel = level;
	}

    @Override
    public Loader getLoader() {
        return loader;
    }
    
    
    @Override
	public void setView(Vector center, double radius){
    	if (center == null) {
			throw new NullPointerException();
		}
    	if (radius <= 0.0) {
			throw new IllegalArgumentException("radius must be positive");
		}
    	expectedCenter = center;
    	expectedRadius = radius;
    }
    
    @Override
    public void register(Actor actor){
    	registered.add(actor);
    }
    
    @Override
    public void unregister(Actor actor){
    	unregistered.add(actor);
    }
    
    @Override
    public Vector getGravity(){
    	return gravity;
    }

    @Override
    public int hurt(Box area, Actor instigator, Damage type, double amount,
    		Vector location) {

    	int victims = 0;
    	for(Actor actor: actors){
    		if (area.isColliding(actor.getBox())) {
				if (actor.hurt(instigator, type, amount, location)) {
					victims++;
				}
			}
    	}
    	return victims;
    }
}
