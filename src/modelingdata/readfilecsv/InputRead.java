package modelingdata.readfilecsv;

import java.io.File;

public abstract class InputRead {
	String dir = System.getProperty("user.dir");

    File directoryPath = new File(dir + "\\data\\data");
    
    File filesList[] = directoryPath.listFiles();

	abstract void read();
}
