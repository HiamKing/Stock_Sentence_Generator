package database.process;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import database.Sentence;

/**
 *
 * @author Ninh Van Nghia 20190060
 */
public class Reader {
    private DataProcess dp = new DataProcess();
    
    public ArrayList<Sentence> readInput() {
        Scanner sc = null;
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        
        try {
        	String dir = System.getProperty("user.dir");
            File file = new File(dir + "\\data\\sentences.txt");
            sc = new Scanner(file);

            while (sc.hasNext()) {
                sentences.add(dp.toSentence(sc.nextLine()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Không tìm thấy file data nguồn");
        } finally {
            try {
                if (sc != null) sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sentences;
    }
}
