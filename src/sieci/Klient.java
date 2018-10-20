import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Klient {



    public static void main(String[] args) throws Exception{


        String serverName = "localhost";
        int serverPort = 4444;

        InetAddress serverAdress = InetAddress.getByName(serverName);

        Socket clientScoket = new Socket(serverAdress, serverPort);

        BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));


        bw.write("dupa");
        bw.newLine();
        bw.flush();
        String serverREsp = br.readLine();
        System.out.println(serverREsp + " odpoweidz");
        clientScoket.close();


    }






}
