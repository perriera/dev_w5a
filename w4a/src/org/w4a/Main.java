package org.w4a;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.w4a.server.Server;

public class Main extends org.w0a.Main {

	public Main(String arg) throws Exception {
		super(new String[]{arg}, new W4AIniFile(arg));
	}
	
	public Main(String[] args, W4AIniFile iniFile) throws Exception {
		super(args, iniFile);
	}

	public Main(String[] args) throws Exception {
		this(args, new W4AIniFile(args[0]));
	}

	@Override
	public W4AIniFile getXIniFile() {
		return (W4AIniFile) iniFile;
	}
	/**
	 * void execute()
	 * 
	 * Standard main() construct.
	 * 
	 */
	
	public void execute() throws Exception {
		
		Server server = new Server(this.getXIniFile());
		server.execute();
		
	}
	
	/**
	 * void main()
	 * 
	 * Standard main() construct.
	 * 
	 */
	
	boolean isDirEmpty(File dir) throws Exception {
		String ignoreList = this.getXIniFile().getIgnoreList();
		File folder = new File(dir.getPath());
		File[] listOfFiles = folder.listFiles();
		for ( File x : listOfFiles ) {
			if ( !ignoreList.contains(x.getName()) )
				return false;
		}
		return true;
	}

	/**
	 * java -jar ../w4a.jar files/inifiles/w4a.mock-server.monitor.ini -w
	 */
	
	Timer timer = null;
	
	public void monitorSource() throws Exception {

		printStartup();

		this.timer = new Timer();
		TimerTask task = new TimerTask() {

			File f2 = new File(iniFilename);

			@Override
			public void run() {
				try {
					File f1 = new File(getXIniFile().getUploadsDir());
					if (f1.exists() && !isDirEmpty(f1)) {
						System.out.println(f1.length());
						timestamp1 = f1.lastModified();
						execute();
						printStartup();
					}
					if (f2.exists() && f2.lastModified() != timestamp2) {
						timestamp2 = f2.lastModified();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		timer.schedule(task, 1000, 1000);

	}
	
	public void stop() throws Exception {
		timer.cancel();
		timer.purge();
	}
	
	/**
	 * void main()
	 * 
	 * Standard main() construct.
	 * 
	 */

	public static void main(String[] args) {

		try {
			Main main = new Main(args);
			main.process();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
