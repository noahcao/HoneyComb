package tfidf;

import model.PaperSmall;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tfidf {

    private static HashMap<String, Float> tf(String[] cutWordResult) {
        HashMap<String, Float> tf = new HashMap<>();
        int wordNum = cutWordResult.length;
        int wordtf;
        for (int i = 0; i < wordNum; i++) {
            wordtf = 0;
            for (int j = 0; j < wordNum; j++) {
                if (cutWordResult[i] != " " && i != j) {
                    if (cutWordResult[i].equals(cutWordResult[j])) {
                        cutWordResult[j] = " ";
                        wordtf++;
                    }
                }
            }
            if (cutWordResult[i] != " ") {
                tf.put(cutWordResult[i], (new Float(++wordtf)) / wordNum);
                cutWordResult[i] = " ";
            }
        }
        return tf;
    }

    public static HashMap<PaperSmall, HashMap<String, Float>> tfOfAll(List<PaperSmall> objects, int limit) {
        int top = limit * 100 >= 10000 ? 10000 : limit * 100;
        HashMap<PaperSmall, HashMap<String, Float>> allTheTf = new HashMap<>();
        int i = 0;
        for (PaperSmall object : objects) {
            if (++i > top) {
                return allTheTf;
            }
            HashMap<String, Float> dict = tf(object.getTitle().split("[ ,-]"));
            allTheTf.put(object, dict);
        }
        return allTheTf;
    }
}
