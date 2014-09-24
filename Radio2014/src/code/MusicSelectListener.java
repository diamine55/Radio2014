package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MusicSelectListener implements KeyListener {

	private JMusicSelectPanel MP = Main.getMusicSelectPanel();
	private String selectedArtist;
	private ArtistInfo currentArtist;
	private int columnIndex;
	private String selectedAlbum;
	private String selectedSong;
	private String filePath;

	public MusicSelectListener() {
		columnIndex = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (MP.selectY < 202) {
				MP.line = 20;
				MP.selectY += 20;
				MP.repaint();
			} else {
				if (MP.pageNum / 11 < MP.pageLimit - 1) {
					String index = "up";
					changePage(index);
					MP.line = 20;
					MP.repaint();
				} else {
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (MP.selectY > 4) {
				MP.line = 20;
				MP.selectY -= 20;
				MP.repaint();
			} else {
				String indexD = "down";
				changePage(indexD);
				MP.line = 20;
				MP.repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (columnIndex == 2) {
				selectedSong = MP.manywords.get(MP.pageNum
						+ ((MP.selectY - 3) / 20));

				if (selectedSong == "") {
					System.out.println("no song selected");
				} else {
					filePath = currentArtist.getPaths(selectedAlbum,
							selectedSong);
					System.out.println(currentArtist);
					System.out.println("path of song is: " + filePath);
					System.out.println(columnIndex);
				}

			}

			if (columnIndex == 1) {
				selectedAlbum = MP.manywords.get(MP.pageNum
						+ ((MP.selectY - 3) / 20));
				if (selectedAlbum == "") {
					System.out.println("no album selected");
				} else {
					columnIndex += 1;
					MP.manywords = currentArtist.getSongs(selectedAlbum);
					System.out.println("index = " + columnIndex);
					MP.pageNum = 0;
					MP.line = 20;
					MP.selectY = 3;
					MP.checkDivis();
					MP.repaint();
				}
			}
			if (columnIndex == 0) {
				System.out.println(columnIndex);
				// selectedArtist is assigned to the highlighted text, index++,
				// getInfo makes new Artist obj and sets manywords to albums,
				// screen repaints with albums

				// System.out.println("MANY WORDS = " + MP.manywords);
				selectedArtist = MP.manywords.get(MP.pageNum
						+ ((MP.selectY - 3) / 20));
				if (selectedArtist == "") {
					System.out.println("no artist selected");
				} else {
					columnIndex += 1;
					MP.pageNum = 0;
					getInfo();
					System.out.println("Selected artist is: " + selectedArtist);
					System.out.println("index = " + columnIndex);
				}
			}

			else {
				// System.out.println("too far");
			}

		}

		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (columnIndex == 1) {
				columnIndex = 0;
				MP.manywords = MP.manywordsReset;
				System.out.println("index = " + columnIndex);
				MP.pageNum = 0;
				MP.line = 20;
				MP.selectY = 3;
				MP.checkDivis();
				MP.repaint();

			}

			if (columnIndex == 2) {
				columnIndex = 1;
				MP.manywords = currentArtist.getAlbumList();
				System.out.println("index = " + columnIndex);
				MP.pageNum = 0;
				MP.line = 20;
				MP.selectY = 3;
				MP.checkDivis();
				MP.repaint();

			}

		}

	}

	public void changePage(String s) {
		if (s == "up") {

			MP.pageNum += 11;
			MP.selectY = 3;

		}
		if (s == "down") {
			if (MP.pageNum != 0) {
				MP.pageNum -= 11;
				MP.selectY = 203;
			} else {
			}
		}

	}

	public void getInfo() {
		currentArtist = new ArtistInfo(selectedArtist);
		MP.manywords = currentArtist.getAlbumList();
		MP.line = 20;
		MP.selectY = 3;
		MP.checkDivis();
		MP.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent f) {

	}

}