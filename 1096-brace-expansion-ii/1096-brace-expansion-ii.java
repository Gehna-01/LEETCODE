import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length() - 1);
        return new ArrayList<>(result);
    }

    private Set<String> parse(String s, int l, int r) {
        Set<String> result = new TreeSet<>();
        Set<String> current = new TreeSet<>();
        current.add("");

        int i = l;

        while (i <= r) {

            // Union: move to the next part
            if (s.charAt(i) == ',') {
                result.addAll(current);
                current.clear();
                current.add("");
                i++;
            }

            // Bracket expression
            else if (s.charAt(i) == '{') {
                int count = 1;
                int j = i + 1;

                while (count > 0) {
                    if (s.charAt(j) == '{') count++;
                    else if (s.charAt(j) == '}') count--;
                    j++;
                }

                // Expand inside braces
                Set<String> inside = parse(s, i + 1, j - 2);

                // Concatenate current with inside
                current = concatenate(current, inside);

                i = j;
            }

            // Normal character
            else {
                Set<String> single = new TreeSet<>();
                single.add(String.valueOf(s.charAt(i)));

                current = concatenate(current, single);
                i++;
            }
        }

        result.addAll(current);
        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}