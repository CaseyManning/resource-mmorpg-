package engine;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Tilemap {

	protected Tile[][] data;
	protected HashMap<Vec2, Item> items;
	protected HashMap<Vec2, Resource> resources;
	
	public Tilemap(Tile[][] data, HashMap<Vec2, Resource> resources) {
		this.resources = resources;
		this.data = data;
	}
	
	public Tilemap(HashMap<Character, Tile> key, InputStream is, Charset encoding, HashMap<Vec2, Resource> resources) {
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
	
	public Item itemAt(Vec2 v) {
		if (items.containsKey(v)) {
			return items.get(v);
		}
		return null;
	}
	
	public Item itemAt(int x, int y) {
		return itemAt(new Vec2(x, y));
	}
	
	public Resource resourceAt(Vec2 v) {
		if (resources.containsKey(v)) {
			return resources.get(v);
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
	
	public Resource addResource(Vec2 pos, Resource r) {
		resources.put(pos, r);
		
		return r;
	}
	
	public Vec2 getSize() {
		return new Vec2(this.data[0].length, this.data.length);
	}
	
	public void update(int elapsed, Graphics2D g) {
		// resources.entrySet().removeIf(entry -> entry.getValue().shouldDelete());
		
		Iterator<Vec2> it = resources.keySet().iterator();
		while(it.hasNext()) {
			Vec2 current = it.next();
			resources.get(current).update(elapsed);
		}
		
//		Iterator<Entry<Vec2, Resource> > it2 = resources.entrySet().iterator();
//		do {
//			Entry<Vec2, Resource> current = it2.next();
//			it2.hashCode();
//			if (resources.get(current.getKey()).shouldDelete()) {
//				if(it2.hasNext()) {
//					it2.remove();
//					System.out.println(current.getValue().toString());
//				}
//			}
//		} while(it2.hasNext());
		
//		for(Iterator<Map.Entry<Vec2, Resource>> it = resources.entrySet().iterator(); it.hasNext(); ) {
//			Map.Entry<Vec2, Resource> entry = it.next();
//			if(resources.get(entry.getKey()).shouldDelete()) {
//				it.remove();
//			} else {
//				resources.get(entry.getKey()).update(elapsed);
//			}
//		}
//		
//		Iterator<Vec2> it = resources.keySet().iterator();
//		while(it.hasNext()) {
//			Vec2 current = it.next();
//			if(resources.get(current).shouldDelete()) {
//				it.remove();
//			} else {
//				resources.get(current).update(elapsed);
//			}
//		}
	}
	
	public void draw(Graphics2D g, Vec2 playerPos, Tile outOfBoundsTile) {
		// (GamePanel.HEIGHT / GameManager.TILESIZE)/2;
		
		for(int row=0; row < (GamePanel.HEIGHT / GameManager.TILESIZE)+1; row++) {
			for(int col=0; col < (GamePanel.WIDTH / GameManager.TILESIZE)+1; col++) {
				try {
					data[playerPos.y+row-(GamePanel.HEIGHT / GameManager.TILESIZE)/2][playerPos.x+col-(GamePanel.WIDTH / GameManager.TILESIZE)/2].draw(g, col, row);
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
		
		
		try {
			int count = 0;
			for(java.util.Map.Entry<Vec2, Resource> entry : resources.entrySet()) {
				if(count == 0){
					Vec2 pos = entry.getKey();
					Resource r = entry.getValue();
					
					if(r != null && r.shouldDelete()) {
						resources.remove(pos);
					}
					
					g.drawImage(r.getImg(), (pos.x-playerPos.x+(GamePanel.WIDTH / GameManager.TILESIZE)/2)*GameManager.TILESIZE-GameManager.TILESIZE/2+1, (pos.y-playerPos.y+(GamePanel.HEIGHT / GameManager.TILESIZE)/2)*GameManager.TILESIZE-GameManager.TILESIZE/2+1, null);
					count++;
				}
			}
		} catch (Exception e) {}
	}

}
