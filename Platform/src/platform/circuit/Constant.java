package platform.circuit;

public class Constant implements Signal{
	private boolean value;
	
	public Constant(boolean value){
		this.value = value;
	}
	
	@Override
	public boolean isActive() {
		return value;
	}
	
}

