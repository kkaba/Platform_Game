package platform.circuit;

public class Or implements Signal{
	Signal left;
	Signal right;
	
	public Or(Signal left, Signal right){
		this.left =left;
		this.right = right;
		
	}
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return (left.isActive() || right.isActive());
	}	
}
