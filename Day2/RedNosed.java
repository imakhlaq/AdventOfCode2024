package Day2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RedNosed {


    private static long parseFile(String path) throws IOException {

        //reading file
        var data = Files.lines(Path.of(path), StandardCharsets.UTF_8)
            .filter(RedNosed::checkForSafety)
            .count();

        return data;
    }


    private static boolean checkForSafety(String s) {

        var list = s.split(" ");

        var num1 = Integer.parseInt(list[0]);
        var num2 = Integer.parseInt(list[1]);

        //check for increasing
        if (num1 < num2) return checkForIncreasingSafety(List.of(list));

        //check for decreasing
        if (num1 > num2) return checkForDecreasingSafety(List.of(list));

        return true;
    }


    private static boolean checkForIncreasingSafety(List<String> s) {

        for (int i = 1; i < s.size(); i++) {
            if (Integer.parseInt(s.get(i - 1)) > Integer.parseInt(s.get(i))) return false;
            if (Integer.parseInt(s.get(i - 1)) == Integer.parseInt(s.get(i))) return false;
            if ((Integer.parseInt(s.get(i - 1) + 3)) < Integer.parseInt(s.get(i))) return false;
        }
        return true;
    }

    private static boolean checkForDecreasingSafety(List<String> s) {

        for (int i = 1; i < s.size(); i++) {
            if (Integer.parseInt(s.get(i - 1)) < Integer.parseInt(s.get(i))) return false;
            if (Integer.parseInt(s.get(i - 1)) == Integer.parseInt(s.get(i))) return false;
            if ((Integer.parseInt(s.get(i - 1)) - 3) < Integer.parseInt(s.get(i))) return false;
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        var ans = parseFile("Day2/input.txt");
        System.out.println(ans);
    }
}
