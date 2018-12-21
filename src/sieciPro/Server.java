package skj.projekt.test.projektSerwerKlient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Server {


private final static int PORT = 4444;

private final static String NAME = "serwer";

private final static Map<String, String> mapa = new HashMap<>();
private static void log(String msg){

    System.out.println(NAME + " "+msg);
    System.out.flush();
}


public static void main(String[] args) throws Exception{

        log("start");
        log("serwer socker creation");
        Socket clientScocket = null;
        boolean done = false;

        try(ServerSocket welcomeSocket = new ServerSocket(PORT)) {

            while(!done) {
                log("socket nasluchuje");
                clientScocket = welcomeSocket.accept();
                //log("klient has connected");
                //log("info klienta " + getClientInfo(clientScocket));

                BufferedReader br = new BufferedReader(new InputStreamReader(clientScocket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScocket.getOutputStream()));

                //InputStream inputStream = clientScocket.getInputStream();
                //OutputStream outputStream = clientScocket.getOutputStream();
                //Scanner scn = new Scanner(inputStream);


                log("service start ");

                String hostFiles = br.readLine();
                log(hostFiles);
                switch(hostFiles){
                    case "lista":
                        sendFileList(clientScocket);
                        break;
                    default:
                        inserIntoMap(hostFiles);
                }
            }
            log("service start");

            log("end");
        }
    }

    private static void inserIntoMap(String things){
            int portEnd = things.indexOf(';');
            int portStart = things.indexOf(':');
            String port = things.substring(portStart+1, portEnd);
            mapa.put(port, things.substring(portEnd+1));
            log(mapa.keySet().size()+"");
    }

    private static String getClientInfo(Socket clientScocket){

    String clientInfo = clientScocket.getInetAddress().toString();
    int port = clientScocket.getPort();

    return clientInfo + " "+port;
}

    private static void sendFileList(Socket clientScocket){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(clientScocket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScocket.getOutputStream()));
            Set<String> key = mapa.keySet();
            //StringBuilder sb = new StringBuilder();

            //key.forEach(k -> sb.append("Port:"+k+";"+mapa.get(k)));
            key.forEach(k -> {
               String line =  "Port:"+k+";"+mapa.get(k);
                try {
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                } catch (IOException e){
                   log("problem wewnatrz lambydy");
                }
            });

            //bw.write(sb.toString());
            //bw.newLine();
            //bw.flush();
            clientScocket.close();    //sprawdzic czy mozna go zamknac, mozliwe ze trzeba go otwierac i zamukac za kazdym razem.  cos czuje ze raczej na pewno
            br.close();
            bw.close();
        } catch (IOException e) {
            log("problem z pobraniem listy plikow" +e);
        }


    }


}

