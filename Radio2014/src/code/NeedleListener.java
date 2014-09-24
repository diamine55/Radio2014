package code;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class NeedleListener implements KeyListener {

	private JBackgroundPanel Jbb;
	public BufferedImage blank;;

	public NeedleListener(JBackgroundPanel Jbb) {
		this.Jbb = Jbb;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (Jbb.nx < 300) {
				Jbb.nx += 10;
				setTitle();
				Jbb.repaint();
			} else {
				System.out.println("too far");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// Invisible image background causes funky coords
			if (Jbb.nx > -50) {
				Jbb.nx -= 10;
				setTitle();
				Jbb.repaint();
			} else {
				System.out.println("too far");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (-60 < Jbb.nx && Jbb.nx < 100) {
				System.out.println("Music");
				
				 Main.changeScreen(Main.getMusicSelectPanel(), Main.getMSList());
				
			} else {
				System.out.println("other shit");

			}
		}

	}

	public void setTitle() {
		if (-60 < Jbb.nx && Jbb.nx < 100) {
			// System.out.println("Music");
			Jbb.section = Jbb.music;
		} else {
			// System.out.println("other shit");
			Jbb.section = Jbb.other;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
