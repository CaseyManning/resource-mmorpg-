package engine;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import player.PlayerAttributes;

public class Wizard extends JPanel implements KeyListener {

	//ArrayList<String> responses = new ArrayList<String>();
	//HashMap<String, Integer> responses = new HashMap<String, Integer>();
	JTextArea wizText = new JTextArea(5,5);
	JTextField text = new JTextField(10);

	public static void main(String[] args) {
		Wizard w = new Wizard();
	}
	
	public Wizard() {
		setSize(400,400);
		text.addKeyListener(this);
		add(text, BorderLayout.SOUTH);
		add(wizText, BorderLayout.NORTH);
		wizText.append("I am the Wizard! What can I do for you?");
		this.setVisible(true);

	}

	public ArrayList<Item> startWizard(ArrayList<String> playerattributes) {
		PlayerAttributes attributes = new PlayerAttributes();
		for(String s : playerattributes) {
			try {
				Item i = new Item(s, ImageIO.read(this.getClass().getResourceAsStream("/assets/" + s + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList<Item> ret = new ArrayList<Item>();
		String str = text.getText();
		switch(str) {
		case "gimme a sword": {

			wizText.append("I iz giving you sword");
			Item i = attributes.getItemWithName("Wood");
			if(i != null) {
				attributes.removeItem(i);
				wizText.append("Take this sword");
				try {
					ret.add(new Item("Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"))));
				} catch (IOException e) { e.printStackTrace(); }
			}
			break;
		}
		case "gimme a good sword": {
			try {
				wizText.append("Good Sword");
				ret.add(new Item("Good Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"))));
			} catch (IOException e) { e.printStackTrace(); }
			break;
		}
		case "I'm done": {
			wizText.append("Thank you.");
			return ret;
		}
		case "nothing": {
			wizText.append("Thank you.");
			return ret;
		}

		}
		text.setText("");
		return null;
	}

	public void draw() {
		add(wizText);
		add(text);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar()));
		if(KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar()) == KeyEvent.VK_ENTER) {
			System.out.println("Igrhjuisghjd");
			
			startWizard(null);
		}


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
