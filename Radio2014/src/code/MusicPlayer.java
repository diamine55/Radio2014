package code;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
/*
 * MusicPlayer is a java wrapper for mpg321.
 * To use:
 * First instantiate the class
 * Next load the file that you want to play
 * 
 * Playback should begin immediately
 * 
 * 
 */


//NOTE This does not work on windows!!!!!!!

//to get mpg321 on linux:
// sudo apt-get install mpg321


public class MusicPlayer {
	private ProcessBuilder proBuild;
	private Process proc;
	private ErrorHandle eh;
	private BufferedWriter bw;
	private InputHandle ih;

	public MusicPlayer() {
		proBuild = new ProcessBuilder("mpg321", "-R", "a");
		proBuild.directory(new File("/home/tomdoug/Music"));
		try {
			proc = proBuild.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ih = new InputHandle(proc.getInputStream());
		eh = new ErrorHandle(proc.getErrorStream());
		eh.start();
		ih.start();

		OutputStream out = proc.getOutputStream();
		bw = new BufferedWriter(new OutputStreamWriter(out));
	}

	public void loadFile(String filePath) {
		try {
			bw.write("L " + filePath);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		try {
			bw.write("P");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void stop() {
		try {
			bw.write("S");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void quit() {
		try {
			bw.write("Q");
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ErrorHandle extends Thread {

	private InputStream es;

	public ErrorHandle(InputStream es) {

		this.es = es;
	}

	@Override
	public void run() {
		InputStreamReader isr = new InputStreamReader(es);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				System.err.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class InputHandle extends Thread {

	private InputStream is;

	public InputHandle(InputStream is) {

		this.is = is;
	}

	@Override
	public void run() {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
