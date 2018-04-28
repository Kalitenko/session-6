package ru.sbt.jschool.session6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 1 on 22.04.2018.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        String propertyPath;
        String defaultPropertyPath = "property";
        Property property = new Property(defaultPropertyPath);
        int port = property.getPort();
        ResponseService responseService = new ResponseService();

        String responseString;

        try(ServerSocket serverSocket = new ServerSocket(port)){

            while(true){

                Socket socket = serverSocket.accept();
                InputStream inputStream =  socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                Scanner scanner = new Scanner(inputStream);
                PrintWriter printWriter = new PrintWriter(outputStream, true);

                if (scanner.hasNextLine()){
                    String str = scanner.nextLine();
                    System.out.println(str);
                    responseString = responseService.response(str);
                    System.out.println(responseString );
                    outputStream.write(responseString.getBytes());
                    outputStream.flush();

                    if(str.equals("exit")){
                        break;
                    }
                } else {
                    continue;
                }

                socket.close();
            }
        }

    }
}

