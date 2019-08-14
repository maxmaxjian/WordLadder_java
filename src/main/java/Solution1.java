import java.util.*;

public class Solution1 implements Solution {
    Map<String,Integer> map = new HashMap<>();

    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        } else {
            int[] visit = new int[wordList.size()];
            return ladderFrom(beginWord, endWord, wordList, visit);
        }
    }

    private int ladderFrom(String start, String end, List<String> wordList, int[] visit) {
        if (!map.containsKey(start)) {
            if (start.equals(end)) {
                map.put(start, 1);
            } else {
                List<String> next = findNext(start, wordList, visit);
                if (!next.isEmpty()) {
                    int result = Integer.MAX_VALUE;
                    for (String nx : next) {
                        int[] copy = Arrays.copyOf(visit, visit.length);
                        for (int i = 0; i < copy.length; i++) {
                            if (wordList.get(i).equals(nx)) {
                                copy[i] = 1;
                                break;
                            }
                        }
                        int prev = ladderFrom(nx, end, wordList, copy);
                        result = Math.min(result, prev);
                    }
                    map.put(start, result+1);
                }
            }
        }
        return map.get(start);
    }

    private List<String> findNext(String start, List<String> wordList, int[] visit) {
        List<String> next = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (visit[i] == 0 && diffInOneChar(wordList.get(i), start)) {
                next.add(wordList.get(i));
            }
        }
        return next;
    }

    private boolean diffInOneChar(String first, String second) {
        boolean found = false;
        if (first.length() == second.length()) {
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (!found) {
                        found = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return found;
    }
}
