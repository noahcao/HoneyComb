package tfidf;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test_1 {

    public static void main(String[] args) {
        try {
            JsonObject obj = new JsonObject();
            obj.addProperty("cat", "it");
            obj.addProperty("pop", true);
            System.out.println("start;" + obj);
            String[] args1 = new String[]{"python", ".\\test.py", obj.toString()};
            Process pr = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}