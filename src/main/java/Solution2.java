import javafx.util.Pair;

import java.util.*;

public class Solution2 implements Solution {
    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        } else {
            Queue<Pair<Pair<String,Integer>,List<Integer>>> queue = new LinkedList<>();
            List<Integer> visit = new ArrayList<>(Collections.nCopies(wordList.size(), 0));
            queue.add(new Pair<>(new Pair<>(beginWord, 1), visit));
            while (!queue.isEmpty()) {
                Pair<Pair<String,Integer>,List<Integer>> curr = queue.remove();
                if (curr.getKey().getKey().equals(endWord)) {
                    return curr.getKey().getValue();
                } else {
                    List<String> next = findNext(curr.getKey().getKey(), wordList, curr.getValue());
                    for (String nx : next) {
                        List<Integer> copy = new ArrayList<>(curr.getValue());
                        for (int i = 0; i < wordList.size(); i++) {
                            if (wordList.get(i).equals(nx)) {
                                copy.set(i, 1);
                            }
                        }
                        queue.add(new Pair<>(new Pair<>(nx, curr.getKey().getValue()+1), copy));
                    }
                }
            }
            return 0;
        }
    }

    private List<String> findNext(String start, List<String> wordList, List<Integer> visit) {
        List<String> next = new ArrayList<>();
        for (int i = 0; i < start.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != start.charAt(i)) {
                    String transformed = new StringBuilder().append(start, 0, i)
                            .append(ch).append(start.substring(i+1)).toString();
                    int k = 0;
                    while (k < wordList.size()) {
                        if (wordList.get(k).equals(transformed)) {
                            break;
                        } else {
                            k++;
                        }
                    }
                    if (k < wordList.size() && visit.get(k) == 0) {
                        next.add(transformed);
                    }
                }
            }
        }
        return next;
    }
}
