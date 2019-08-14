import javafx.util.Pair;

import java.util.*;

public class Solution4 implements Solution {
    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        } else {
            Queue<Pair<Pair<String,Integer>, Set<String>>> queue = new LinkedList<>();
            queue.add(new Pair<>(new Pair<>(beginWord, 1), new HashSet<>(wordList)));
            while (!queue.isEmpty()) {
                Pair<Pair<String,Integer>, Set<String>> curr = queue.remove();
                if (curr.getKey().getKey().equals(endWord)) {
                    return curr.getKey().getValue();
                } else {
                    List<String> next = findNext(curr.getKey().getKey(), curr.getValue());
                    for (String nx : next) {
                        curr.getValue().remove(nx);
                        queue.add(new Pair<>(new Pair<>(nx, curr.getKey().getValue()+1), curr.getValue()));
                    }
                }
            }
        }
        return 0;
    }

    private List<String> findNext(String start, Set<String> wordList) {
        List<String> next = new ArrayList<>();
        for (String cand : wordList) {
            if (diffInOneChar(start, cand)) {
                next.add(cand);
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
