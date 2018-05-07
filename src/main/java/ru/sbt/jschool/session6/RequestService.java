package ru.sbt.jschool.session6;

class RequestService {

    boolean isRequest(String string){
        return string.startsWith("GET");
    }

    String requestString(String string){
        return string.substring("GET /".length(), string.length() - " HTTP/1.1".length());
    }

    boolean isCreateRequest(String string){
        return string.startsWith("user/create?");
    }
    boolean isDeleteRequest(String string){
        return string.startsWith("user/delete/");
    }
    boolean isListRequest(String string){
        return string.startsWith("user/list");
    }
    boolean isIdRequest(String string){
        return string.startsWith("user/$");
    }
    String[] createRequest(String string){
        String substring = string.substring("/user/create?".length() - 1, string.length());
        String[] strings = substring.split("&");
        if(!(strings.length == 3))
            return null;
        String[] result = new String[3];
        for (String str: strings) {
            if (str.startsWith("name="))
                result[0] = str.substring("name=".length(), str.length());
            else if (str.startsWith("age="))
                result[1] = str.substring("age=".length(), str.length());
            else if (str.startsWith("salary="))
                result[2] = str.substring("salary=".length(), str.length());
            else return null;
        }
        for(String str : result)
            if(str.equals(""))
                return null;

        return result;
    }

    Integer deleteRequest(String string){
        String substring = string.substring("/user/delete/".length()-1, string.length());

        if(substring.equals(""))
            return null;
        else
            return Integer.valueOf(substring);

    }

    Integer IdRequest(String string){
        String substring = string.substring("/user/$".length() - 1, string.length());

        if(substring.equals(""))
            return null;
        else
            return Integer.valueOf(substring);

    }
}

