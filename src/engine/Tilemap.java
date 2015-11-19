package engine;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Tilemap {

	protected Tile[][] data;
	protected ArrayList<Item> items;
	protected ArrayList<Resource> resources;
	
	public Tilemap(Tile[][] data, ArrayList<Resource> resources) {
		this.resources = resources;
		this.data = data;
	}
	
	public Tilemap(HashMap<Character, Tile> key, InputStream is, Charset encoding, ArrayList<Resource> resources) {
		this.resources = resources;
		try {
			byte[] encoded = new byte[4096];
			is.read(encoded);
			is.close();
			String contents = new String(encoded, encoding);
			String[] lines = contents.split("\\n");
			data = new Tile[lines.length][lines[0].length()];
			for(int line = 0; line < lines.length; line++) {
				for(int ch = 0; ch < lines[0].length(); ch++) {
					data[line][ch] = key.get(lines[line].charAt(ch));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Resource resourceAt(Vec2 v) {
		for(int i=0; i<resources.size(); i++) {
			if(resources.get(i).getPos() == v) {
				return resources.get(i);
			}
		}
		return null;
	}
	
	public Resource resourceAt(int x, int y) {
		return resourceAt(new Vec2(x, y));
	}
	
	public Tile getTile(int x, int y) {
		return data[y][x];
	}
	
	public Tile getTile(Vec2 v) {
		return getTile(v.x, v.y);
	}
	
	public Resource addResource(Resource r) {
		resources.add(r);
		
		return r;
	}
	
	public Vec2 getSize() {
		return new Vec2(this.data[0].length, this.data.length);
	}
	
	public Vec2 randomEmptyPosition() {
		Random r=new Random();
		Vec2 pos = new Vec2(r.nextInt(this.getSize().x), r.nextInt(this.getSize().y));
		while (!(this.resourceAt(pos) == null && this.getTile(pos).isPassable())) {
			pos = new Vec2(r.nextInt(this.getSize().x), r.nextInt(this.getSize().y));
		}
		return pos;
	}
	
	public void addResourceAtRandomPosition(Resource toBePlaced) {
		this.addResource(toBePlaced);
	}
	
	public void update(int elapsed) {
		// resources.entrySet().removeIf(entry -> entry.getValue().shouldDelete());
		
		for(int i=0; i<resources.size(); i++) {
			resources.get(i).update(elapsed);
			if(resources.get(i).shouldDelete()) {
				resources.remove(i);
			}
		}
	}
	
	public void draw(Graphics2D g, Vec2 playerPos, Tile outOfBoundsTile) {
		// (GamePanel.HEIGHT / GameManager.TILESIZE)/2;
		
		for(int row=0; row < (GamePanel.HEIGHT / GameAssistant.TILESIZE)+1; row++) {
			for(int col=0; col < (GamePanel.WIDTH / GameAssistant.TILESIZE)+1; col++) {
				try {
					data[playerPos.y+row-(GamePanel.HEIGHT / GameAssistant.TILESIZE)/2][playerPos.x+col-(GamePanel.WIDTH / GameAssistant.TILESIZE)/2].draw(g, col, row);
				} catch(Exception e) {
					outOfBoundsTile.draw(g, col, row);
				}
//				try {
//					g.drawImage(data[row+playerPos.y-(GamePanel.HEIGHT / GameManager.TILESIZE)/2][col+playerPos.x-(GamePanel.WIDTH / GameManager.TILESIZE)/2].getImg(), row*GameManager.TILESIZE-GameManager.TILESIZE/2, col*GameManager.TILESIZE-GameManager.TILESIZE/2, null);
//					System.out.println("In array: "+data[row+playerPos.y-(GamePanel.HEIGHT / GameManager.TILESIZE)/2][col+playerPos.x-(GamePanel.WIDTH / GameManager.TILESIZE)/2]);
//				} catch(Exception e) {
//					g.drawImage(outOfBoundsTile.getImg(), row*GameManager.TILESIZE-GameManager.TILESIZE/2, col*GameManager.TILESIZE-GameManager.TILESIZE/2, null);
//				}
			}
		}
		
		
		for(int i=0; i<resources.size(); i++) {
			Resource r = resources.get(i);
			
			g.drawImage(r.getImg(), (r.getPos().x-playerPos.x+(GamePanel.WIDTH / GameAssistant.TILESIZE)/2)*GameAssistant.TILESIZE-GameAssistant.TILESIZE/2+1, (r.getPos().y-playerPos.y+(GamePanel.HEIGHT / GameAssistant.TILESIZE)/2)*GameAssistant.TILESIZE-GameAssistant.TILESIZE/2+1, null);
		}
	}

}
