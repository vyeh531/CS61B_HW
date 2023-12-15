import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> map = new TreeMap<>();
        int i = 1;
        char j = 'a';
        while (i < 27){
            map.put(j, i);
            i++;
            j++;
        }
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        if (nums == null){
            return null;
        } else {
            Map<Integer, Integer> map = new TreeMap<>();
            for (Integer elem : nums) {
                map.put(elem, (elem * elem));
            }
            return map;
        }
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        if (words == null){
            return null;
        } else {
            Map<String, Integer> map = new TreeMap<>();
            int n = 1;
            for (String elem : words){
                if (map.containsKey(elem)){
                    map.put(elem, map.get(elem)+1);
                } else {
                    map.put(elem, n);
                }
            }
            return map;
        }
    }
}
