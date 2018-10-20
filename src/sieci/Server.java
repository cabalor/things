import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private final static int PORT = 4444;

    private final static String NAME = "serwer";


    private static void log(String msg){

        System.out.println(NAME + " "+msg);
        //System.out.println(PORT + " "+msg);
        System.out.flush();
    }


    public static void main(String[] args) throws Exception{

        log("start");
        log("serwer socker creation");

        ServerSocket welcomeSocket = new ServerSocket(PORT);

        log("socket nasluchuje");

        Socket clientScocket =  welcomeSocket.accept();
        log("klient has connected");
        log("info klienta "+ getClientInfo(clientScocket));

        log("strumienie");

        //InputStream is = clientScocket.getInputStream();
        //OutputStream os = clientScocket.getOutputStream();

        //InputStreamReader isr = new InputStreamReader(is);
        //OutputStreamWriter osw = new OutputStreamWriter(os);

        BufferedReader br = new BufferedReader(new InputStreamReader(clientScocket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScocket.getOutputStream()));

        log("service start ");

        String login = br.readLine();

        /*if("dupa".compareTo(login) == 0 ){
            bw.write("asdsadasd");
            bw.flush();
        }*/

        if("dupa".equals(login)){
            log("witamy na serwerze");
            bw.write("ok");
            //bw.newLine();
            bw.flush();
        } else {
            log("nie ma wstepu");
            bw.write("spadaj");
            bw.newLine();
            bw.flush();
        }

        log("login "+login);
        log("client closing");

        clientScocket.close();
        welcomeSocket.close();

        log("service start");

        log("end");

    }

    private static String getClientInfo(Socket clientScocket){

        String clientInfo = clientScocket.getInetAddress().toString();
        int port = clientScocket.getPort();

        return clientInfo + " "+port;
    }



}

