package tfidf;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class GraphData {

    public static String run(JsonObject obj1, JsonObject obj2) {
        try {
            System.out.println(obj1.toString() + "\n" +  obj2.toString());
            String[] args1 = new String[]{"python", ".\\NetProduce.py", obj1.toString(), obj2.toString()};
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
        run(new JsonObject(), new JsonObject());
    }
}