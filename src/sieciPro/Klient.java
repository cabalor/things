package test;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Klient {


    private static final String PATH = "c:\\skjProject\\pliki\\";
    private final static int PORT = 4445;
    private final static String NAME = "HOST1";
    private final static int SERVERPORT = 4444;
    private final static String SERVERNAME = "localhost";
    private final static List<String> lista = new ArrayList<>();
    private final static List<String> filesChecksum = new ArrayList<>();
    private final static Map<String, Long> FileSentMap = new HashMap<>();
    private final static Map<String, Long> FileGetMap = new HashMap<>();

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
            } catch (IOException e) {
                log("problem w wtku " + e);
            }
        });
        serverThread.start();

        Scanner scn = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            log("witam w programie do pobierania plikow.\n wcisnij 0 aby wyjsc, \n " +
                    "1 aby zobaczyc liste plików,\n 2 aby wysłac plik,\n 3 aby sciagnac plik,\n 4 aby zaaktualizowac pliki na serwerze");
            String line = scn.nextLine();
            int port = 0;
            switch (line) {
                case "0":
                    exit = true;
                    serverThread.stop();
                    logOutFromServer(serverAdress, PORT);
                    System.exit(0);
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
                    break;
                case "4":
                    sendFileToServer(serverAdress);
                    break;
            }
        }
    }

    private static  void logOutFromServer(InetAddress serverAdress, int Port){
        try {
            Socket clientScoket = new Socket(serverAdress, SERVERPORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
            bw.write("logout");
            bw.newLine();
            bw.flush();
            bw.write(Port+"");
            bw.newLine();
            bw.flush();
            clientScoket.close();
        } catch (IOException e) {
            log("wyjatek w pobieraniu listy" + e);
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
            lista.clear();
            br.close();
            bw.close();
            clientScoket.close();
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
        File file = new File(PATH + fileName);
        byte[] bytes = new byte[8192];


        InputStream in = new FileInputStream(file);
        OutputStream out = clientScoket.getOutputStream();
        //todo dorobic jeszcze druga mape dla wypychania i bedzie okej
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));
        bw.write("push");
        bw.newLine();
        bw.flush();
        String serverREsp = br.readLine();
        log(serverREsp + " odpoweidz");
        log(FileSentMap.containsKey(fileName) + " czy zawiera taki plik juz mapa");
        FileSentMap.keySet().forEach(Klient::log);
        if (!FileSentMap.containsKey(fileName)) {
            log("jestem w pierwszym warunku");
            bw.write(fileName);
            bw.newLine();
            bw.flush();
            bw.write("false");
            bw.newLine();
            bw.flush();
            String serverREsp2 = br.readLine();
            log(serverREsp2 + " odpoweidz");
            Long bytesSend = 0L;
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                bytesSend = bytesSend + count;
                FileSentMap.put(fileName, bytesSend);
            }
        } else if (file.length() != FileSentMap.get(fileName) && file.length()>FileSentMap.get(fileName)) {
            log("jestem w drugim warunku");
            bw.write(fileName);
            bw.newLine();
            bw.flush();
            bw.write("true");
            bw.newLine();
            bw.flush();
            String serverREsp2 = br.readLine();
            log(serverREsp2 + " odpoweidz");
            Long bytesSend = FileSentMap.get(fileName);
            in.skip(FileSentMap.get(fileName));
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                bytesSend = bytesSend + count;
                FileSentMap.put(fileName, bytesSend);
            }
        } else {
            log("w else");
            FileSentMap.remove(fileName);
            bw.write(fileName);
            bw.newLine();
            bw.flush();
            bw.write("false");
            bw.newLine();
            bw.flush();
            String serverREsp2 = br.readLine();
            log(serverREsp2 + " odpoweidz");
            Long bytesSend = 0L;
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                bytesSend = bytesSend + count;
                FileSentMap.put(fileName, bytesSend);
            }
        }

    log(FileSentMap.get(fileName)+" długosc pliku "+file.length());
        bw.close();
        br.close();
        out.flush();
        out.close();
        in.close();
        clientScoket.close();
}

    private static void pullFile(int serverPort, Scanner scn, int myPort) throws IOException {
        InetAddress serverAdress = InetAddress.getByName(SERVERNAME);
        Socket clientScoket = new Socket(serverAdress, serverPort);
        scn.nextLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientScoket.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(clientScoket.getInputStream()));

        log("wpisz nazwe pliku ktora chcesz sciagnac od hosta z portem " + serverPort);
        String fileName = scn.nextLine();
        bw.write("pull");
        bw.newLine();
        bw.flush();
        String serverREsp = br.readLine();
        log(serverREsp + " -odpoweidz");

        bw.write(String.valueOf(myPort));
        bw.newLine();
        bw.flush();
        String serverREsp3 = br.readLine();
        log(serverREsp3 + " -odpoweidz");

        bw.write(fileName);
        bw.newLine();
        bw.flush();
        String serverREsp2 = br.readLine();
        log(serverREsp2 + " -odpoweidz");

        br.close();
        bw.close();
        clientScoket.close();

    }



    private static void serverClient(int PORT) throws IOException {
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
        while (true) {
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
            bw.write("ok chcesz " + operation);
            bw.newLine();
            bw.flush();
            switch (operation) {
                case "push":
                    String fileName = br.readLine();
                    bw.write("ok, tworze plik " + fileName);
                    bw.newLine();
                    bw.flush();
                    String append = br.readLine();
                    log("tutaj mamy " + append);
                    try {
                        if (append.equals("false")) {
                            new File(PATH + fileName).delete();
                            outputStream = new FileOutputStream(PATH + fileName);
                        } else {
                            log("dopisuje do przerwanego pliku");
                            outputStream = new FileOutputStream(PATH + fileName, true);
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
                    //inputStream.close();
                    //socket.close();
                    //serSock.close();

                    System.out.println("end");
                    break;
                case "pull":
                    log("start");
                    log("serwer socker creation");

                    //BufferedReader br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    String port = br.readLine();
                    System.out.println("----------------" + port);
                    bw.write("ok, odebrałem port twojego serwaera");
                    bw.newLine();
                    bw.flush();

                    String fileName1 = br.readLine();
                    bw.write("ok, wysyłam plik" + fileName1);
                    bw.newLine();
                    bw.flush();

                    int serverPort = Integer.valueOf(port);

                    InetAddress serverAdress = InetAddress.getByName(SERVERNAME);

                    Socket clientScoket = new Socket(serverAdress, serverPort);

                    File file = new File(PATH + fileName1);
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
                    log(FileSentMap.containsKey(fileName1) + " czy zawiera taki plik juz mapa");
                    FileSentMap.keySet().forEach(Klient::log);
                    if (!FileSentMap.containsKey(fileName1)) {
                        log("jestem w pierwszym warunku");
                        bw2.write(fileName1);
                        bw2.newLine();
                        bw2.flush();
                        bw2.write("false");
                        bw2.newLine();
                        bw2.flush();
                        String serverREsp2 = br2.readLine();
                        log(serverREsp2 + " odpoweidz");
                        Long bytesSend = 0L;
                        int count2;
                        while ((count2 = in.read(bytes2)) > 0) {
                            out.write(bytes2, 0, count2);
                            bytesSend = bytesSend + count2;
                            FileSentMap.put(fileName1, bytesSend);
                        }
                    } else if (file.length() != FileSentMap.get(fileName1) && file.length()>FileSentMap.get(fileName1)) {
                        log("jestem w drugim warunku");
                        bw2.write(fileName1);
                        bw2.newLine();
                        bw2.flush();
                        bw2.write("true");
                        bw2.newLine();
                        bw2.flush();
                        String serverREsp2 = br2.readLine();
                        log(serverREsp2 + " odpoweidz");
                        Long bytesSend = FileSentMap.get(fileName1);
                        in.skip(FileSentMap.get(fileName1));
                        int count2;
                        while ((count2 = in.read(bytes2)) > 0) {
                            out.write(bytes2, 0, count2);
                            bytesSend = bytesSend + count2;
                            FileSentMap.put(fileName1, bytesSend);
                        }
                    } else {
                        log("w else");
                        FileSentMap.remove(fileName1);
                        bw2.write(fileName1);
                        bw2.newLine();
                        bw2.flush();
                        bw2.write("false");
                        bw2.newLine();
                        bw2.flush();
                        String serverREsp2 = br2.readLine();
                        log(serverREsp2 + " odpoweidz");
                        Long bytesSend = 0L;
                        int count2;
                        while ((count2 = in.read(bytes2)) > 0) {
                            out.write(bytes2, 0, count2);
                            bytesSend = bytesSend + count2;
                            FileSentMap.put(fileName1, bytesSend);
                        }
                    }

                    log(FileSentMap.get(fileName1)+" długosc pliku "+file.length());
                    bw2.close();
                    br2.close();
                    out.flush();
                    out.close();
                    in.close();
                    clientScoket.close();
                    break;
            }
        }

    }


}
