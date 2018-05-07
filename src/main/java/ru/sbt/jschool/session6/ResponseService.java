package ru.sbt.jschool.session6;

import ru.sbt.jschool.session6.Formatter.JSONFormatterImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

class ResponseService {

    private ServerServiceImp serverServiceImp = new ServerServiceImp();
    private RequestService requestService = new RequestService();
    private JSONFormatterImpl formatter = new JSONFormatterImpl();

    private final String CONNECTION_CLOSE = "\r\nConnection: close\r\n\r\n";

    String response(String string) throws Exception {

        StringBuilder sb = new StringBuilder("HTTP/1.1 ");

        if (!requestService.isRequest(string)) {
            sb.append(writeBadRequest());
        } else {

            string = requestService.requestString(string);

            if (requestService.isCreateRequest(string)) {
                String[] strings = requestService.createRequest(string);
                if (strings == null)
                    sb.append(writeBadRequest());
                else {
                    String name = strings[0];
                    try {
                        int age = Integer.valueOf(strings[1]);
                        double salary = Double.valueOf(strings[2]);
                        serverServiceImp.createUser(name, age, salary);
                        sb.append(writeOkRequest()).append("User created\n");
                    } catch (Exception e) {
                        sb.append(writeException(e));
                    }
                }
                return sb.toString();

            } else if (requestService.isDeleteRequest(string)) {
                Integer id = requestService.deleteRequest(string);
                if (id == null) {
                    sb.append(writeBadRequest());
                    return sb.toString();
                } else {
                    if (serverServiceImp.deleteUser(id))
                        sb.append(writeOkRequest()).append("User ").append(id).append(" deleted\n");
                    else
                        sb.append(writeNotFoundRequest()).append("User ").append(id).append(" not found\n");
                }
                return sb.toString();

            } else if (requestService.isListRequest(string)) {
                List<User> list;
                try {
                    list = serverServiceImp.list();
                } catch (IOException | ClassNotFoundException e) {
                    return sb.append(writeException(e)).toString();
                }
                if (list.size() == 0)
                    return sb.append(writeOkRequest()).append("List of users is empty.").toString();

                String str = formatter.marshall(list);
                return sb.append(writeOkRequest()).append("List of users: \n").append(str).toString();

            } else if (requestService.isIdRequest(string)) {
                Integer id = requestService.IdRequest(string);
                if (id == null) {
                    sb.append(writeBadRequest());
                    return sb.toString();
                } else {
                    User user;
                    try {
                        user = serverServiceImp.getUser(id);

                    } catch (FileNotFoundException e) {
                        return sb.append(writeNotFoundRequest()).append("User ").append(id).append(" not found\n").toString();
                    } catch (IOException | ClassNotFoundException e) {
                        return sb.append(writeException(e)).toString();
                    }
                    if (user == null) {
                        return sb.append(writeNotFoundRequest()).append("User ").append(id).append(" not found\n").toString();
                    } else {
                        String str = formatter.marshall(user);
                        return sb.append(writeOkRequest()).append("User ").append(id).append(":\n").append(str).toString();
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

    private String writeException(Exception e){
        e.printStackTrace();
        return writeOkRequest() + "Error! " + e.toString();
    }

}

