package database.process;

import java.util.ArrayList;
import java.util.List;

import database.Sentence;

/**
 *
 * @author Ninh Van Nghia 20190060
 */
public class Search {
    private HashFunction hf = new HashFunction();
    
    public ArrayList<String> searchFor(ArrayList<Sentence> data, List<String> listTag) {
        ArrayList<String> sentences = new ArrayList<String>();
        ArrayList<Integer> listCode = hf.getHashCode(listTag);
        
        try {
            for (int i = 0; i < data.size(); i++) {
                ArrayList<Integer> tempList = data.get(i).getTag();
                
                boolean match = true;
                for (int j = 0; j < listCode.size(); j++) {
                    if (!tempList.contains(listCode.get(j))) {
                        match = false;
                        break;
                    }
                }
                if (match) sentences.add(data.get(i).getSentence());
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        if(sentences.size() == 0) sentences.add("Không có câu nào phù hợp với các tags người dùng nhập.");
        return sentences;
    }
}
