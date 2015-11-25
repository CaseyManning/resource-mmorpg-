package engine;

public class WizardSprite extends Sprite {

	Vec2 PPP; //PreviousPlayerPosition
	
	
	
	public WizardSprite(int x, int y) {
		super(x, y);
		
	wizard();
	}
	
	//public void playerMove(Vec2 p) {
		//System.out.println("Moving Player at" + x + ", " + y);
		//int changeInX = Math.abs(p.x - PPP.x);
		//int changeInY = Math.abs(p.y - PPP.y);
		
		//x = x - changeInX;
		
		//y = y - changeInY;
	//}
	
	public void wizard(){
		
		loadImage("/assets/wizard2.png");
		getImageDimensions();
		
		
	}
	

}
