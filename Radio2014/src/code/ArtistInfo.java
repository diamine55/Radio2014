package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistInfo {

	private ArrayList<String> listOfAlbums;
	private static Connection c = null;
	private String artistName;
	private ArrayList<String> listOfSongs;
	private String songSelected;
	Statement stmt = null;

	public ArtistInfo(String artist) {
		artistName = artist;

		listOfAlbums = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			PreparedStatement PS;
			PS = c.prepareStatement("SELECT * FROM MUSIC WHERE ARTIST = ?");

			PS.setString(1, artistName);
			ResultSet result = PS.executeQuery();

			while (result.next()) {
				String album = result.getString(3);
				if (listOfAlbums.contains(album)) {

				} else {
					listOfAlbums.add(album);
				}

			}
			PS.clearParameters();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": fucked up "
					+ e.getMessage());
			System.exit(0);
		}

	}

	public ArrayList<String> getAlbumList() {
		return listOfAlbums;
	}

	public ArrayList<String> getSongs(String album) {
		listOfSongs = new ArrayList<String>();

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			stmt = c.createStatement();

			PreparedStatement PS;
			PS = c.prepareStatement("SELECT * FROM MUSIC WHERE ARTIST = ? AND ALBUM = ?");

			PS.setString(1, artistName);
			PS.setString(2, album);
			ResultSet result = PS.executeQuery();

			while (result.next()) {
				String song = result.getString(4);
				listOfSongs.add(song);

			}
			PS.clearParameters();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": fucked up "
					+ e.getMessage());
			System.exit(0);
		}

		return listOfSongs;
	}

	
	public String getPaths(String album, String song) {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			stmt = c.createStatement();

			PreparedStatement PS;
			PS = c.prepareStatement("SELECT * FROM MUSIC WHERE ARTIST = ? AND ALBUM = ? AND SONG = ?");

			PS.setString(1, artistName);
			PS.setString(2, album);
			PS.setString(3, song);
			ResultSet result = PS.executeQuery();

			while (result.next()) {
				songSelected= result.getString(5);
			}
			PS.clearParameters();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": fucked up "
					+ e.getMessage());
			System.exit(0);
		}

		return songSelected;
	}
	
	public ArrayList<String> getListOfSongs(){
		return listOfSongs;
	}
}
