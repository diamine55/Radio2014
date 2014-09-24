package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JPanel;

public class JMusicSelectPanel extends JPanel {

	private static final long serialVersionUID = 7541731121709002749L;
	private static Connection c = null;
	Statement stmt = null;

	public int line = 20;
	public ArrayList<String> manywords;
	public int selectY;
	public int pageNum = 0;
	public int pageLimit;
	public ArrayList<String> manywordsReset;

	public JMusicSelectPanel() {
		this.setBackground(Color.black);
		manywords = new ArrayList<String>();
		listArts();
		selectY = 3;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		int i = 0;
		while (i < 11) {
			String artistName = manywords.get(i + pageNum);
			g.drawString(artistName, 10, line);
			line += 20;
			i++;
		}
		g.drawRect(5, selectY, this.getWidth() - 10, 20);
	}

	public void listArts() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			stmt = c.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT DISTINCT ARTIST FROM MUSIC;");
			while (rs.next()) {
				String artist = rs.getString("Artist");

				// System.out.println();

				manywords.add(artist);

			}
			rs.close();
			stmt.close();

			c.close();
			System.out.println("Database Connection Closed");
			manywordsReset = manywords;
			repaint();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		checkDivis();
	}

	public void checkDivis() {
		int z = 0;
		if (manywords.size() == 0) {
			while (z < 11) {
				manywords.add("");
				z++;
			}
		}
		if (manywords.size() % 11 != 0) {
			manywords.add("");
			checkDivis();
		}

		else {
			pageLimit = manywords.size() / 11;
		}
	}
}
