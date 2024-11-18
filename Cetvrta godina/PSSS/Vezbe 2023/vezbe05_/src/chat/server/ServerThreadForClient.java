package chat.server;

import chat.protocol.Login;
import chat.protocol.Logout;
import chat.protocol.Message;
import chat.protocol.PrivateMessage;

import java.beans.XMLDecoder;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ServerThreadForClient extends Thread {
    HashMap<String, BufferedWriter> clients = null;
    Socket client = null;
    String name = null;
    public static String secureCode = "1234";

    public ServerThreadForClient(HashMap<String, BufferedWriter> clients, Socket client) {
        this.clients = clients;
        this.client = client;
    }

    @Override
    public void run() {
        BufferedWriter bw = null;
        BufferedReader br = null;

        try {
            bw = new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            String line = br.readLine();
            XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
            Object o = xmlDecoder.readObject();

            Login login = null;

            if (!(o instanceof Login)) {
                client.close();
                return;
            } else {
                login = (Login) o;
                name = login.getName();

                if (login.getCode().equals(ServerThreadForClient.secureCode) == false) {
                    client.close();
                    return;
                }

                synchronized (clients) {
                    clients.put(name, bw);
                }

                Message message = new Message();
                message.setText("Novi klijet: " + name);
                message.setFrom("SERVER");

                for (BufferedWriter client_writter : clients.values()) {
                    client_writter.write(message.toString() + "\n");
                    client_writter.flush();
                }

                message.setText("KLIJENTI: " + clients.keySet());
                message.setFrom("SERVER");

                for (BufferedWriter client_writter : clients.values()) {
                    client_writter.write(message.toString() + "\n");
                    client_writter.flush();
                }

                while (true) {
                    line = br.readLine(); // xml string
                    xmlDecoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
                    o = xmlDecoder.readObject();

                    if (o instanceof PrivateMessage) {
                        PrivateMessage privateMessage = (PrivateMessage) o;
                        BufferedWriter write_private = clients.get(privateMessage.getToRecipient());
                        privateMessage.setFrom(name);
                        write_private.write(privateMessage.toString() + "\n");
                        write_private.flush();
                    } else if (o instanceof Message) {
                        message = (Message) o;
                        message.setFrom(name);
                        for (BufferedWriter client_writter : clients.values()) {
                            client_writter.write(message.toString() + "\n");
                            client_writter.flush();
                        }
                    } else if (o instanceof Logout) {
                        BufferedWriter write_logout = clients.get(name);
                        synchronized (clients) {
                            clients.remove(name);
                        }
                        Logout logout = new Logout();
                        write_logout.write(logout.toString() + "\n");
                        write_logout.flush();

                        if (client.isClosed() == false) {
                            client.close();
                        }

                        message.setText("Klijent " + name + " je napustio chat");
                        message.setFrom("SERVER");

                        for (BufferedWriter client_writter : clients.values()) {
                            client_writter.write(message.toString() + "\n");
                            client_writter.flush();
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
