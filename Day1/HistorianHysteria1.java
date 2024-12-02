package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistorianHysteria1 {

    private static List<List<Integer>> readFromFile(String path) throws IOException {

        List<List<Integer>> res = new ArrayList<>();
        var a = new ArrayList<Integer>();
        var b = new ArrayList<Integer>();
        res.add(a);
        res.add(b);

        var readFile = new BufferedReader(new FileReader(path));

        readFile.lines().forEach(str -> {
            var newStr = str.split("   ");
            a.add(Integer.parseInt(newStr[0]));
            b.add(Integer.parseInt(newStr[1]));
        });

        return res;
    }

    public static void main(String[] args) throws IOException {

        var data = readFromFile("Day1/input.txt");

        data.get(0).sort(Integer::compareTo);
        data.get(1).sort(Integer::compareTo);

        System.out.println(data.get(0));
        System.out.println(data.get(1));

        Integer ans = 0;
        for (int i = 0; i < data.get(0).size(); i++) {
            ans += Math.abs(data.get(0).get(i) - data.get(1).get(i));
        }

        System.out.println(ans);
    }
}

