package code;
import java.io.File;
import java.sql.*;

public class DatabaseCreation {

	private static Connection c = null;

	public DatabaseCreation() {

		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			System.out.println("Opened database successfzzzzully");

			stmt = c.createStatement();
			System.out.println("created statement");
			String sql = "CREATE TABLE IF NOT EXISTS MUSIC "
					+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ " ARTIST           TEXT, " + " ALBUM            TEXT, "
					+ " SONG             TEXT, " + " PATH             TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
		populateTable();
	}

	public static void populateTable() {
		///////////////////////////////////CHANGE MUSIC FOLDER PATH HERE///////////////////////////////////////////////////
		//String MusicFolder = "C:\\Users\\TomDoug\\Music";
		String MusicFolder = "C:\\Users\\Matt\\Music\\Music";
		int IDCount = 0;

		File folder = new File(MusicFolder);
		File[] listOfArtists = folder.listFiles();

		for (int i = 0; i < listOfArtists.length; i++) {
			String ArtistFolder = MusicFolder + '\\'
					+ listOfArtists[i].getName();
			System.out.println(ArtistFolder);

			File ArtFol = new File(ArtistFolder);

			File[] listOfAlbs = ArtFol.listFiles();

			if (listOfAlbs != null) {
				for (int j = 0; j < listOfAlbs.length; j++) {
					String AlbumFolder = ArtistFolder + '\\'
							+ listOfAlbs[j].getName();

					File AlbFol = new File(AlbumFolder);
					File[] listOfSongs = AlbFol.listFiles();

					if (listOfSongs != null) {
						for (int k = 0; k < listOfSongs.length; k++) {
							String artist = listOfArtists[i].getName();
							String album = listOfAlbs[j].getName();
							String song = listOfSongs[k].getName();
							String path = AlbumFolder + listOfSongs[k].getName();
							if (song.contains(".mp3") || song.contains(".m4a")) {
								try {
									PreparedStatement PS;

									PS = c.prepareStatement("INSERT INTO MUSIC (ID,ARTIST,ALBUM,SONG,PATH) VALUES (?,?,?,?,?)");
									PS.setInt(1, IDCount);
									PS.setString(2, artist);
									PS.setString(3, album);
									PS.setString(4, song);
									PS.setString(5, path);
									PS.executeUpdate();

								} catch (SQLException e) {
									e.printStackTrace();
								}

								IDCount++;
							}
						}
					}
				}
			}
		}

		try {
			c.close();
			System.out.println("Database Connection Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public File[] openFolder(String path) {
		File parent = new File(path);
		File[] listOfFiles = parent.listFiles();
		return listOfFiles;
	}
}