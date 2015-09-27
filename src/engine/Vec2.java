package engine;

public class Vec2 {

	public int x;
	public int y;
	
	public Vec2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
