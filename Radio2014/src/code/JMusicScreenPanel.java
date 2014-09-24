package code;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class JMusicScreenPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2692100090680543455L;
	private Song selectedSong;
	private MusicPlayer mp;
	public JMusicScreenPanel(){
		//this.mp = new MusicPlayer();
	}
	
	public void loadSong(Song song){
		this.selectedSong = song;
		//this.mp.loadFile(song.getFilepath());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.blue);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("HELLO", 10, 100);
		//g.drawString(this.selectedSong.getTitle(), 10, 100);
	}
	
	

}
