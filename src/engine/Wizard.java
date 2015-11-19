package engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import player.PlayerAttributes;

public class Wizard extends JPanel implements KeyListener {

	//ArrayList<String> responses = new ArrayList<String>();
	//HashMap<String, Integer> responses = new HashMap<String, Integer>();
	BufferedImage swordimg;

	public static final int Done = 0;
	public static final int Sword = 1;
	public static final int GoodSword = 2;

	
	public Wizard() {
		setSize(400,400);
		//text.addKeyListener(this);
		//add(text, BorderLayout.WEST);
		try {
			swordimg = ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setVisible(true);

	}

	public ArrayList<Item> startWizard(ArrayList<String> playerattributes, int x, int y) {
		System.out.println(playerattributes);
		PlayerAttributes attributes = new PlayerAttributes();
		if(playerattributes == null){
			System.out.println("lf,ytyfhmdffh");
		}
		for(String s : playerattributes) {
			try {
				Item i = new Item(s, ImageIO.read(this.getClass().getResourceAsStream("/assets/" + s + ".png")));
				attributes.addItem(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList<Item> ret = new ArrayList<Item>();
		//String str = text.getText();
		int n = 1;
		if(x > 5 && x < 30) {
		n = 1;	
		}
		switch(n) {
		case Sword: {

			System.out.println("I iz giving you sword");
			Item i = attributes.getItemWithName("Wood");
			if(i != null) {
				attributes.removeItem(i);
				System.out.println("Take this sword");
				try {
					ret.add(new Item("Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"))));
				} catch (IOException e) { e.printStackTrace(); }
			}
			break;
		}
		case GoodSword: {
			try {
				System.out.println("Good Sword");
				ret.add(new Item("Good Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"))));
			} catch (IOException e) { e.printStackTrace(); }
			break;
		}
		case Done: {
			System.out.println("Thank you.");
			return ret;
		}
		
		}
		return ret;
	}

	public void draw(Graphics g) {
		//System.out.println("I am the wizard, and I am being told to draw myself");
		g.drawString("I is Wizard", 29, 52);
		g.drawImage(swordimg, 20, 20, 10, 10, this);
		this.revalidate();
		this.paintChildren(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println(KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar()));
//		if(KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar()) == KeyEvent.VK_ENTER) {
//			System.out.println("Igrhjuisghjd");
//			
//			startWizard(null);
//		}


	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}
class HappyListener implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		
		System.out.println("Happy got key: " + e.getKeyChar() + " with code: " + e.getKeyCode());
		System.out.println("Happy code should be: " + KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar()));
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
