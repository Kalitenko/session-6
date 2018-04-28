package ru.sbt.jschool.session6;

/**
 * Created by 1 on 27.04.2018.
 */
public class RequestService {

    boolean isRequest(String string){

        if(string.startsWith("GET"))
            return true;

        return false;
    }

    String requestString(String string){
        return string.substring("GET /".length(), string.length() - " HTTP/1.1".length());
    }

    boolean isCreateRequest(String string){
        if(string.startsWith("user/create?"))
            return true;
        return false;
    }
    boolean isDeleteRequest(String string){
        if(string.startsWith("user/delete/"))
            return true;
        return false;
    }
    boolean isListRequest(String string){
        if(string.startsWith("user/list"))
            return true;
        return false;
    }
    boolean isIdRequest(String string){
        if(string.startsWith("user/$"))
            return true;
        return false;
    }
    String[] createRequest(String string){
        String substring = string.substring("/user/create?".length() - 1, string.length());
        String[] strings = substring.split("&");
        String[] result = new String[3];
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].startsWith("name="))
                result[0] = strings[i].substring("name=".length(), strings[i].length());
            else if(strings[i].startsWith("age="))
                result[1] = strings[i].substring("age=".length(), strings[i].length());
            else if(strings[i].startsWith("salary="))
                result[2] = strings[i].substring("salary=".length(), strings[i].length());
        }
        for(String str : result)
            if(str.equals(""))
                return null;

        return result;
    }

    Integer deleteRequest(String string){
        String substring = string.substring("/user/delete/".length()-1, string.length());

        if(substring == "")
            return null;
        else
            return Integer.valueOf(substring);

    }

    Integer IdRequest(String string){
        String substring = string.substring("/user/$".length() - 1, string.length());

        if(substring == "")
            return null;
        else
            return Integer.valueOf(substring);

    }
}

