package ru.sbt.jschool.session6;

import ru.sbt.jschool.session6.Formatter.JSONFormatterImpl;

import java.util.List;

/**
 * Created by 1 on 27.04.2018.
 */
public class ResponseService {

    private ServerServiceImp serverServiceImp = new ServerServiceImp();
    private RequestService requestService = new RequestService();
    private JSONFormatterImpl formatter = new JSONFormatterImpl();

    public String response(String string){

        StringBuilder sb = new StringBuilder("HTTP/1.1 ");
        final String CONNECTION_CLOSE = "\r\nConnection: close\r\n\r\n";

        if(!requestService.isRequest(string)) {
            sb.append(Response.BAD_REQUEST.toString()).append(CONNECTION_CLOSE).append(Response.BAD_REQUEST.toString());;
        } else {

            string = requestService.requestString(string);

            if(requestService.isCreateRequest(string)){
                String[] strings = requestService.createRequest(string);
                if(strings == null)
                    sb.append(Response.BAD_REQUEST.toString()).append(CONNECTION_CLOSE).append(Response.BAD_REQUEST.toString());
                else {
                    String name = strings[0];
                    int age = Integer.valueOf(strings[1]);
                    double salary = Double.valueOf(strings[2]);
                    serverServiceImp.createUser(name, age, salary);
                    sb.append(Response.OK.toString()).append(CONNECTION_CLOSE).append("User created\n");
                }
                return sb.toString();

            } else if(requestService.isDeleteRequest(string)){
                Integer id = requestService.deleteRequest(string);
                if(id == null) {
                    sb.append(Response.BAD_REQUEST.toString()).append(CONNECTION_CLOSE).append(Response.BAD_REQUEST.toString());
                    return sb.toString();
                } else {
                    if(serverServiceImp.deleteUser(id))
                        sb.append(Response.OK.toString()).append(CONNECTION_CLOSE).append("User " + id + " deleted\n");
                    else
                        sb.append(Response.NOT_FOUND.toString()).append(CONNECTION_CLOSE).append("User " + id + " not found\n");;
                }
                return sb.toString();

            } else if(requestService.isListRequest(string)){
                List<User> list = serverServiceImp.list();
                String str = formatter.marshall(list);
                sb.append(Response.OK.toString()).append(CONNECTION_CLOSE).append("List of users: \n").append(str);

            } else if(requestService.isIdRequest(string)){
                Integer id = requestService.IdRequest(string);
                if(id == null){
                    sb.append(Response.BAD_REQUEST.toString()).append(CONNECTION_CLOSE).append(Response.BAD_REQUEST.toString());
                    return sb.toString();
                }
                else{
                    User user = serverServiceImp.getUser(id);
                    if(user == null){
                        sb.append(Response.NOT_FOUND.toString()).append(CONNECTION_CLOSE).append("User " + id + " not found\n");;
                    }
                    else{
                        String str = formatter.marshall(user);
                        sb.append(Response.OK.toString()).append(CONNECTION_CLOSE).append("User " + id + ":\n").append(str);
                    }
                }
            } else {
                sb.append(Response.BAD_REQUEST.toString()).append(CONNECTION_CLOSE).append(Response.BAD_REQUEST.toString());
                return sb.toString();
            }
        }

        return sb.toString();
    }
}

