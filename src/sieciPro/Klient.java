package skj.projekt.test.projektSerwerKlient;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.ExportException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Klient {


    private static final String PATH = "c:\\skjProject\\pliki\\";
    private final static int PORT = 4445;
    private final static String NAME = "HOST1";
    private final static int SERVERPORT = 4444;
    private final static String SERVERNAME = "localhost";
    private final static List<String> lista = new ArrayList<>();
    private final static List<String> filesChecksum = new ArrayList<>();

    private static void log(String msg) {
        System.out.println(NAME + " " + msg);
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {

        InetAddress serverAdress = InetAddress.getByName(SERVERNAME);
        sendFileToServer(serverAdress);
        Thread serverThread = new Thread(() -> {
            try {
                serverClient(PORT);
            } catch (IOException e){
                log("problem w wtku "+e);
            }
        });
        serverThread.start();

        //ServerSocket welcomeSocket = new ServerSocket(PORT);
        Scanner scn = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            log("witam w programie do pobierania plikow. wcisnij 0 aby wyjsc, 1 aby zobaczyc liste plików, 2 aby wysłac plik, 3 aby sciagnac plik");
            String line = scn.nextLine();
            int port = 0;
            switch (line) {
                case "0":
                    exit = true;
                    break;
                case "1":
                    askForFiles(serverAdress);
                    break;
                case "2":
                    log("wpisz numer portu klienta do ktorgo chcesz wrzucic plik");
                    port = scn.nextInt();
                    pushFile(port, scn);
                    break;
                case "3":
                    log("wpisz numer portu klienta od ktorego chcesz sciaganac plik");
                    port = scn.nextInt();
                    pullFile(port, scn, PORT);
            }
        }
    }

    private static void sendFileToServer(InetAddress serverAdress) {
        try {
            Socket clientScoket = new Socket(serverAdress, SERVERPORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));

            log("sprawdzam liste plikow i wysyłam na serwer");
            File[] files = new File(PATH).listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    String strHash = null;
                    try {
                        byte[] b = Files.readAllBytes(Paths.get(f.toURI()));
                        byte[] hash = MessageDigest.getInstance("MD5").digest(b);
                        strHash = DatatypeConverter.printHexBinary(hash);
                    } catch (IOException | NoSuchAlgorithmException e) {
                        log("problem z md5");
                    }
                    filesChecksum.add(strHash);
                    lista.add(f.getName());
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Port:" + PORT + ";");

            for (int i = 0; i < lista.size(); i++) {
                sb.append(lista.get(i) + ";checksum=" + filesChecksum.get(i) + ";");
            }
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
            clientScoket.close();
            br.close();
            bw.close();
        } catch (IOException e) {
            log("prooblem z wyslaniem plikow " + e);
        }

    }

    private static void askForFiles(InetAddress serverAdress) {
        try {
            Socket clientScoket = new Socket(serverAdress, SERVERPORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
            bw.write("lista");
            bw.newLine();
            bw.flush();
            log("zasoby z sieci");
            br.lines().forEach(Klient::log);
            clientScoket.close();
        } catch (IOException e) {
            log("wyjatek w pobieraniu listy" + e);
        }
    }

    private static void pushFile(int serverPort, Scanner scn) throws Exception {

        InetAddress serverAdress = InetAddress.getByName(SERVERNAME);
        Socket clientScoket = new Socket(serverAdress, serverPort);
        scn.nextLine();
        log("lista twoich plikow");
        lista.forEach(Klient::log);
        log("wpisz dokladna nazwe pliku ktory chcesz wyslac");
        String fileName = scn.nextLine();
        File file = new File(PATH+fileName);
        byte[] bytes = new byte[8192];

        InputStream in = new FileInputStream(file);
        OutputStream out = clientScoket.getOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
        bw.write("push");
        bw.newLine();
        bw.flush();
        String serverREsp = br.readLine();
        log(serverREsp + " odpoweidz");



        bw.write(fileName);
        bw.newLine();
        bw.flush();
        String serverREsp2 = br.readLine();
        log(serverREsp2 + " odpoweidz");

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        clientScoket.close();
    }

    private static void pullFile(int serverPort, Scanner scn, int myPort) throws IOException{
        InetAddress serverAdress = InetAddress.getByName(SERVERNAME);
        Socket clientScoket = new Socket(serverAdress, serverPort);
        scn.nextLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));

        log("wpisz nazwe pliku ktora chcesz sciagnac od hosta z portem "+serverPort);
        String fileName = scn.nextLine();
        bw.write("pull");
        bw.newLine();
        bw.flush();
        String serverREsp = br.readLine();
        log(serverREsp + " odpoweidz");
        log("-----------------"+myPort);

        bw.write(String.valueOf(myPort));
        bw.newLine();
        bw.flush();
        String serverREsp3 = br.readLine();
        log(serverREsp3 + " odpoweidz");

        bw.write(fileName);
        bw.newLine();
        bw.flush();
        String serverREsp2 = br.readLine();
        log(serverREsp2 + " odpoweidz");

    }

    private static void serverClient(int PORT) throws IOException{
        System.out.println("start");
        System.out.println("serwer socker creation");
        ServerSocket serSock = null;
        try {
            serSock = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("nie mozna utworzyc serwera, port zajety");
        }

        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            socket = serSock.accept();
        } catch (IOException ex) {
            System.out.println("problem z zaakceptowaniem polaczenia od klienta");
        }

        try {
            inputStream = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("problem z is");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String operation = br.readLine();
        bw.write("ok chcesz "+operation);
        bw.newLine();
        bw.flush();
        switch(operation) {
            case "push":
                String fileName = br.readLine();
                bw.write("ok, tworze plik " + fileName);
                bw.newLine();
                bw.flush();
                boolean isExist = new File(PATH+fileName).isFile();

                try {
                    if(!isExist) {
                        outputStream = new FileOutputStream(PATH + fileName);
                    } else {
                        new File(PATH+fileName).delete();
                        outputStream = new FileOutputStream(PATH + fileName);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("problem z utworzeniem pliku");
                }


                byte[] bytes = new byte[8192];

                int count;
                try {
                    while ((count = inputStream.read(bytes)) > 0) {
                        outputStream.write(bytes, 0, count);
                    }
                } catch (NullPointerException e) {
                    System.out.println("problem z bitami null pointer");
                }

                outputStream.close();
                inputStream.close();
                socket.close();
                serSock.close();

                System.out.println("end");
                break;
            case "pull":
                log("start");
                log("serwer socker creation");

                BufferedReader br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String port = br1.readLine();
                System.out.println("----------------"+port);
                bw1.write("ok, odebrałem port twojego serwaera");
                bw1.newLine();
                bw1.flush();

                String fileName1 = br1.readLine();
                bw1.write("ok, wysyłam plik"+fileName1);
                bw1.newLine();
                bw1.flush();



                int serverPort = Integer.valueOf(port);

                InetAddress serverAdress = InetAddress.getByName(SERVERNAME);

                Socket clientScoket = new Socket(serverAdress, serverPort);

                File file = new File(PATH+fileName1);
                byte[] bytes2 = new byte[8192];

                InputStream in = new FileInputStream(file);
                OutputStream out = clientScoket.getOutputStream();

                BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
                BufferedReader br2 = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
                bw2.write("push");
                bw2.newLine();
                bw2.flush();
                String serverREsp = br2.readLine();
                log(serverREsp + " odpoweidz");



                bw2.write(fileName1);
                bw2.newLine();
                bw2.flush();
                String serverREsp2 = br2.readLine();
                log(serverREsp2 + " odpoweidz");

                int count2;
                while ((count2 = in.read(bytes2)) > 0) {
                    out.write(bytes2, 0, count2);
                }

                out.close();
                in.close();
                clientScoket.close();
        }

    }


}
