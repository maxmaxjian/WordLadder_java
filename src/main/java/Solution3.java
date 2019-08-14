import java.util.*;

public class Solution3 implements Solution {
    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        } else {
            Queue<List<String>> queue = new LinkedList<>();
            queue.add(Collections.singletonList(beginWord));
            while (!queue.isEmpty()) {
                List<String> curr = queue.remove();
                if (curr.get(curr.size()-1).equals(endWord)) {
                    return curr.size();
                } else {
                    List<String> next = findNext(curr, wordList);
                    for (String nx : next) {
                        List<String> copy = new ArrayList<>(curr);
                        copy.add(nx);
                        queue.add(copy);
                    }
                }
            }
            return 0;
        }
    }

    private List<String> findNext(List<String> curr, List<String> wordList) {
        List<String> next = new ArrayList<>();
        for (String cand : wordList) {
            if (!curr.contains(cand) && diffInOneChar(curr.get(curr.size()-1), cand)) {
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
