package engine;

public class WizardSprite extends Sprite {

	Vec2 PPP; //PreviousPlayerPosition
	
	int x;
	int y;
	
	public WizardSprite(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		loadImage("/assets/wizard2");
	}
	
	public void playerMove(Vec2 p) {
		System.out.println("Moving Player at" + x + ", " + y);
		int changeInX = Math.abs(p.x - PPP.x);
		int changeInY = Math.abs(p.y - PPP.y);
		
		x = x - changeInX;
		
		y = y - changeInY;
	}
	
	@Override
	public int getX() {
		return x;
		
	}
	
	@Override
	public int getY() {
		return y;
		
	}

}
