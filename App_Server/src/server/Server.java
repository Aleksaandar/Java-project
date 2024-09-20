package server;

import domain.Admin;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isidora
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private List<ClientHandler> clientHandlers;
    private List<Admin> ulogovaniAdmini;
    private int maxClients;
    private int port;
    private boolean running;

    public Server(ServerConfig config) {
        this.clientHandlers = new ArrayList<>();
        this.maxClients = config.getMaxUsers();
        this.port = config.getPort();
        this.ulogovaniAdmini = new ArrayList<>();
        this.running = false;
    }

    public List<Admin> getUlogovaniAdmini() {
        return ulogovaniAdmini;
    }

    public Server() {

    }

    public boolean start() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //pozivamo handler da mi se prekinula konekcija sa svim korisnicima
    //zaustavljamo server
    public void stop() {
        running = false;
        for (ClientHandler handler : clientHandlers) {
            //da bi pozatvarao forme
            handler.close();
        }
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //proveravamo ima li mesta da se prikljuci novi korisnik
    public void listen() {
        try {
            while (running) {
                System.out.println("Waiting for clients...");
                try {
                    Socket clientSocket = serverSocket.accept();
                    if (clientHandlers.size() < maxClients) {
                        ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                        clientHandlers.add(clientHandler);
                        new Thread(clientHandler).start();
                        System.out.println("Client connected.");
                    } else {
                        clientSocket.close();
                        System.out.println("Maximum number of clients reached. Connection refused.");
                    }
                } catch (IOException e) {
                    if (running) {
                        e.printStackTrace();
                    }
                    // Exit loop if running is false and serverSocket is closed
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //unistavamo nit
    public void removeClientHandler(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }

    public static void main(String[] args) {
        try {
            String propertiesFilePath = "src/properties/database_properties.properties";
            System.out.println("Attempting to load properties file from: " + propertiesFilePath);

            ServerConfig config = new ServerConfig(propertiesFilePath);
            System.out.println("Loaded properties file successfully.");

            Server server = new Server(config);
            if (server.start()) {
                System.out.println("Server started.");
                server.listen();
            } else {
                System.out.println("Failed to start the server.");
            }
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("General exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        try {
            // Direktno postavite vrednosti konfiguracije
            String url = "jdbc:mysql://localhost:3306/seminarski";
            String username = "root";
            String password = "";
            int maxUsers = 10;
            int port = 9999;

            // Kreirajte instancu ServerConfig sa direktnim vrednostima
            ServerConfig config = new ServerConfig(url, username, password, maxUsers, port);

            Server server = new Server(config);
            if (server.start()) {
                System.out.println("Server started.");
                server.listen();
            } else {
                System.out.println("Failed to start the server.");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
    public boolean isAdminLoggedIn(Admin admin) {

        for (Admin ulogovaniAdmin : ulogovaniAdmini) {
            if (ulogovaniAdmin.getAdminId() == admin.getAdminId()) {
                return true;
            }
        }
        return false;
    }

    public void addLoggedInAdmin(Admin admin) {
        ulogovaniAdmini.add(admin);
    }

    public void removeLoggedInAdmin(Admin ulogovaniAdmin) {
        System.out.println(ulogovaniAdmin.getUsername());

        Admin adminToRemove = null;
        for (Admin a : ulogovaniAdmini) {
            if (a.getUsername().equals(ulogovaniAdmin.getUsername())) {
                adminToRemove = a;
                System.out.println(adminToRemove);
                break;
            }
        }

        if (adminToRemove != null) {
            System.out.println("Vrednost izbacivanja iz liste " + ulogovaniAdmini.remove(adminToRemove));
            System.out.println("Izbacio sam admina u serveru");
        } else {
            System.out.println("Admin nije pronađen u listi");
        }
    }

}
