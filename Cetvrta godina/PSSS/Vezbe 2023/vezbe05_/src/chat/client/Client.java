package chat.client;

import chat.protocol.Login;
import chat.protocol.Logout;
import chat.protocol.Message;
import chat.protocol.PrivateMessage;
import chat.server.Server;

import java.beans.XMLDecoder;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), Server.PORT);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);

            Thread fromServer = new Thread(new Runnable() {
                @Override
                public void run() {
                    String line = null;
                    while (true) {
                        try {
                            line = br.readLine(); // blokirajuci - xml string
                            XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
                            Object o = xmlDecoder.readObject();

                            if (o instanceof Message) {
                                Message messageFroMServer = (Message) o;
                                System.out.println(messageFroMServer.getFrom() + ": " + messageFroMServer.getText());
                            } else if (o instanceof Logout) {
                                break;
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            fromServer.start();

            Thread toServer = new Thread(new Runnable() {
                @Override
                public void run() {
                    String line = "";
                    System.out.println("Unesite ime");
                    line = scanner.nextLine();

                    System.out.println("Unesite kod:");
                    String code = scanner.nextLine();

                    Login login = new Login(line, code);
                    try {
                        bw.write(login.toString() + "\n");
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while (true) {
                        line = "";
                        while (line.equals(""))
                            line = scanner.nextLine();

                        // logout
                        // all
                        // lazar: privatna poruka za Lazara

                        if (line.equals("logout")) {
                            Logout logout = new Logout();
                            try {
                                bw.write(logout.toString() + "\n");
                                bw.flush();
                                break;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        Message message = null;
                        String[] splitted = line.trim().split(":");
                        if (splitted.length == 2){
                            if(splitted[0].equals("ALL"))
                                message = new Message(splitted[1]);
                            else
                                message = new PrivateMessage(splitted[1], splitted[0]);

                            try {
                                bw.write(message.toString() + "\n");
                                bw.flush();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            System.out.println("Poruka mora biti tipa:\n\t 1) ALL:poruka\n\t 2) IME:poruka\n\t 3) logout");
                        }
                    }
                }
            });

            toServer.start();

            fromServer.join();
            toServer.join();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
