package Get_verify_code;


import Constant.Constant;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get_verify_code {
    public static void main(String[] args)throws Exception {
        case2();
    }

    public static Get_verify_codeResp get_verify_code(String phonenumber) throws IOException {
        URL url = new URL(Constant.Get + "?phonenumber=" + phonenumber);
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        try {
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getResponseCode() / 100 == 2 ? connection.getInputStream() : connection.getErrorStream()))){
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String java_string_content = content.toString();
            System.out.println(java_string_content);
            Gson g = new Gson();

            return g.fromJson(java_string_content, Get_verify_codeResp.class);
        }
        finally {
            connection.disconnect();
        }
    }

    public static void case1() throws IOException {
        System.out.println("Case 1: Token is correct");
        Get_verify_codeResp get_verify_codeResp = get_verify_code("0904516406");
        try {
            assert ("1000".equals(get_verify_codeResp.code));
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void case2() throws IOException {
        System.out.println("Case 2: Phonenumber haven't been signuped");
        Get_verify_codeResp get_verify_codeResp = get_verify_code("0904516406");
        try {
            assert ("1004".equals(get_verify_codeResp.code)) : "OK";
            System.out.println("Parameter value is invalid");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
}
