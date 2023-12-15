import java.util.List;
import java.util.ArrayList;


public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int total = 0;
        if (L.size() == 0){
            return total;  
        } else {
            for (int i = 0; i < L.size(); i++){
                total += L.get(i);
            }
            return total;
        }
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        if (L.size() == 0){
            return null;
        } else {
            List<Integer> lst = new ArrayList<Integer>();
            for (int i = 0; i < L.size(); i++){
                if (L.get(i) % 2 == 0){
                    lst.add(L.get(i));
                }
            }
            return lst;
        }
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        if (L1 == null){
            return null;
        } else if (L2 == null){
            return null;
        } else {
            List<Integer> newLst = new ArrayList<Integer>();
            for (int i = 0; i < L1.size(); i++){
                for (int j = 0; j < L2.size(); j ++){
                    if (L1.get(i) == L2.get(j)){
                        newLst.add(L1.get(i));
                    }
                }
            }
            return newLst;
        }
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        if (words == null){
            return 0;
        } else {
            int ans = 0;
            for (int i = 0; i < words.size(); i++){
                String s = words.get(i);
                for (int j = 0; j < s.length(); j++){
                    if (s.charAt(j) == c){
                        ans += 1;
                    }
                }

            }
            return ans;
        }
    }
}
