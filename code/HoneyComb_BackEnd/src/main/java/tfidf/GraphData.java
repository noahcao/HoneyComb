package tfidf;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class GraphData {

    public static String run(JsonObject obj) {
        try {
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
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    public static void main(String[] args) {
        run(new JsonObject());
    }
}