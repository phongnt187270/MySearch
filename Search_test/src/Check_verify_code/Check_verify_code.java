package Check_verify_code;

import Constant.Constant;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Check_verify_code {
    public static void main(String[] args)throws Exception {
        case3();
    }

    public static Check_verify_codeResp Check_verify_code(String phonenumber, String code_verify) throws IOException {
        URL url = new URL(Constant.Check + "?phonenumber=" + phonenumber + "&code_verify=" + code_verify );
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

            return g.fromJson(java_string_content, Check_verify_codeResp.class);
        }
        finally {
            connection.disconnect();
        }
    }

    public static void case1() throws IOException {
        System.out.println("Case 1: Token is correct");
        Check_verify_codeResp check_verify_codeResp = Check_verify_code("0904516406", "680956");
        try {
            assert ("1000".equals(check_verify_codeResp.code));
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void case2() throws IOException {
        System.out.println("Case 2: Parameters are invalid");
        Check_verify_codeResp check_verify_codeResp = Check_verify_code("090451640a", "680956");
        try {
            assert ("1004".equals(check_verify_codeResp.code)) : "OK";
            System.out.println("Parameter value is invalid");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void case3() throws IOException {
        System.out.println("Case 3: phonenumber is invalid");
        Check_verify_codeResp check_verify_codeResp = Check_verify_code("0904516406", "");
        try {
            assert ("1002".equals(check_verify_codeResp.code)) : "OK";
            System.out.println("Parameter is not enough");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }


}
