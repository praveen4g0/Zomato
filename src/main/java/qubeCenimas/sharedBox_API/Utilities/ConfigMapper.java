package qubeCenimas.sharedBox_API.Utilities;

import java.io.*;
import java.util.Properties;

public class ConfigMapper {
	
	public Properties readPropertiesFromFile(String filename)
            throws FileNotFoundException, IOException {
        Properties p = null;
        FileReader fr = null;

        try {
            p = new Properties();
            fr = new FileReader(filename);
            p.load(fr);
        } finally {
            fr.close();
        }
        return p;
    }

}
