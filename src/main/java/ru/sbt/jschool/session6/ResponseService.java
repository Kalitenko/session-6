package ru.sbt.jschool.session6;

import ru.sbt.jschool.session6.Formatter.JSONFormatterImpl;

import java.util.List;

class ResponseService {

    private ServerServiceImp serverServiceImp = new ServerServiceImp();
    private RequestService requestService = new RequestService();
    private JSONFormatterImpl formatter = new JSONFormatterImpl();

    private final String CONNECTION_CLOSE = "\r\nConnection: close\r\n\r\n";

    String response(String string){

        StringBuilder sb = new StringBuilder("HTTP/1.1 ");

        if(!requestService.isRequest(string)) {
            sb.append(writeBadRequest());
        } else {

            string = requestService.requestString(string);

            if(requestService.isCreateRequest(string)){
                String[] strings = requestService.createRequest(string);
                if(strings == null)
                    sb.append(writeBadRequest());
                else {
                    String name = strings[0];
                    int age = Integer.valueOf(strings[1]);
                    double salary = Double.valueOf(strings[2]);
                    serverServiceImp.createUser(name, age, salary);
                    sb.append(writeOkRequest()).append("User created\n");
                }
                return sb.toString();

            } else if(requestService.isDeleteRequest(string)){
                Integer id = requestService.deleteRequest(string);
                if(id == null) {
                    sb.append(writeBadRequest());
                    return sb.toString();
                } else {
                    if(serverServiceImp.deleteUser(id))
                        sb.append(writeOkRequest()).append("User ").append(id).append(" deleted\n");
                    else
                        sb.append(writeNotFoundRequest()).append("User ").append(id).append(" not found\n");
                }
                return sb.toString();

            } else if(requestService.isListRequest(string)){
                List<User> list = serverServiceImp.list();
                String str = formatter.marshall(list);
                sb.append(writeOkRequest()).append("List of users: \n").append(str);

            } else if(requestService.isIdRequest(string)){
                Integer id = requestService.IdRequest(string);
                if(id == null){
                    sb.append(writeBadRequest());
                    return sb.toString();
                }
                else{
                    User user = serverServiceImp.getUser(id);
                    if(user == null){
                        sb.append(writeNotFoundRequest()).append("User ").append(id).append(" not found\n");
                    }
                    else{
                        String str = formatter.marshall(user);
                        sb.append(writeOkRequest()).append("User ").append(id).append(":\n").append(str);
                    }
                }
            } else {
                sb.append(writeBadRequest());
                return sb.toString();
            }
        }

        return sb.toString();
    }
    private String writeBadRequest(){

        return Response.BAD_REQUEST.toString() +
                CONNECTION_CLOSE + Response.BAD_REQUEST.toString();
    }
    private String writeOkRequest(){

        return Response.OK.toString() + CONNECTION_CLOSE;
    }
    private String writeNotFoundRequest(){

        return Response.NOT_FOUND.toString() + CONNECTION_CLOSE;
    }


}

