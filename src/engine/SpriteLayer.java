package engine;

public class SpriteLayer {

	Sprite[][] data;
	
	public SpriteLayer(Sprite[][] data) {
		this.data = data;
	}
	
	public Sprite spriteAt(int x, int y) {
		return data[y][x];
	}

}
