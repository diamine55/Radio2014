package code;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	private static JFrame frame;
	@SuppressWarnings("unused")
	private static DatabaseCreation DB;
	
	private static JBackgroundPanel JBPanel;
	private static JMusicSelectPanel JMPanel;
	private static JPanel currentPanel;
	
	private static NeedleListener JBListener;
	private static MusicSelectListener MSListener;
	private static KeyListener currentListener;
	
		
	public static void main(String[] args) {

		DB = new DatabaseCreation(); // Creates Database
		
		// Create Panels
		JBPanel = new JBackgroundPanel();
		JMPanel = new JMusicSelectPanel();

		
		// Create Listeners
		JBListener = new NeedleListener(JBPanel);//Must receive the JBackgroundPanel
		MSListener = new MusicSelectListener();

		// frame options
		frame = new JFrame("Radio");
		frame.setSize(480, 272);

		currentPanel = JBPanel;
		currentListener = JBListener;
		changeScreen(JBPanel, JBListener); // first frame added

		// frame set visible
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void changeScreen(JPanel jp, KeyListener kl) {
		// removes current JPanel, adds new panel, sets currentPanel to new
		// panel, revalidates frame
		frame.remove(currentPanel);
		frame.add(jp);

		currentPanel = jp;
		//System.out.println("New JPanel is " + jp.toString());
		frame.revalidate();
		changeListener(kl);
	}

	public static void changeListener(KeyListener kl) {
		// similar to changeScreen, Keylisteners instead of JPanels
		frame.removeKeyListener(currentListener);
		frame.addKeyListener(kl);
		
		currentListener = kl;
		//System.out.println("New Listener is " + kl.toString());
	}

	public static JMusicSelectPanel getMusicSelectPanel() {
		return JMPanel;
	}
	public static NeedleListener getNeedleList(){
		return JBListener;
	}
	public static MusicSelectListener getMSList(){
		return MSListener;
	}
	
}
