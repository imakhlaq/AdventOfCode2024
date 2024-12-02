package Day1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorianHysteria2 {

    private static List<List<Integer>> parseFile(String path) throws IOException {

        //wrapping the file input stream in buffered stream for better performance
        var fis = new BufferedInputStream(new FileInputStream(path));
        var buffer = new byte[fis.available()];

        //reading the bytes
        int data = 0;
        while (data != -1) {
            //here we are reading all bytes at once (but for better performance use chunks)
            data = fis.read(buffer);
        }
        fis.close();

        //Parse the buffer into a string
        var str = new String(buffer);
        //removing the new line
        var splitStr = str.split("\r\n");

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<Integer>());
        ans.add(new ArrayList<Integer>());

        //getting a and b columns
        for (var d : splitStr) {
            var i = d.split("   ");
            ans.get(0).add(Integer.valueOf(i[0]));
            ans.get(1).add(Integer.valueOf(i[1]));
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {

        var r = parseFile("Day1/input.txt");

        //crate a hash map and keep key as the value as count
        Map<Integer, Integer> hasMap = new HashMap<>();

        //counting occurrences and increasing the count
        for (int i = 0; i < r.get(1).size(); i++) {
            if (hasMap.containsKey(r.get(1).get(i))) {
                var prevCount = hasMap.get((r.get(1).get(i)));
                hasMap.put(r.get(1).get(i), ++prevCount);
            } else {
                hasMap.put(r.get(1).get(i), 1);
            }
        }

        List<Integer> ans = new ArrayList<>();

        //checking for occurrences and multiplying by count
        for (int i = 0; i < r.get(0).size(); i++) {
            if (hasMap.containsKey(r.get(0).get(i))) {
                var count = hasMap.get(r.get(0).get(i));
                ans.add(count * r.get(0).get(i));
            }
        }

        var res = ans.stream().reduce(0, Integer::sum);

        System.out.println(res);
    }
}
