package database.process;

import java.util.ArrayList;
import java.util.List;

public class HashFunction {
	
	private final int BASE = 9001;
	
	private final int MOD = 1000003;
	
	// lay ma hash cua xau
	public int getHashCode(String str) {
		
		long hashCode = 0;
		for(int i = 0; i < str.length(); ++ i) {
				hashCode = (hashCode*BASE + str.charAt(i)) % MOD;
		}
		
		return (int)hashCode;
	}

    public ArrayList<Integer> getHashCode(List<String> list) {
        ArrayList<Integer> listCode = new ArrayList<Integer>();
        
        for (int i = 0; i < list.size(); i++) {
            listCode.add(getHashCode(list.get(i)));
        }
        
        return listCode;
    }
}
