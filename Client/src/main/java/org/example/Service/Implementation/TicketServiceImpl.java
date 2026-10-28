package org.example.Service.Implementation;

import org.example.Service.ITicketService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketServiceImpl implements ITicketService {

    public Socket socket;
    public BufferedReader in = null;
    public PrintWriter out = null;

    public TicketServiceImpl() {
    }

    void apel() {
        try {
            socket = new Socket("localhost", 12345);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void adaugaBilet(int nrZbor, String nume, int nrBilet) {
        apel();
        out.println("#adaugaBilet " + nrZbor + " " + nume + " " + nrBilet);
    }

    @Override
    public String cautaBilet(String nrBilet) {
        apel();
        out.println("#cautaBilet " + nrBilet);
        String rez = "";
        try {
            rez = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rez;
    }

    @Override
    public ArrayList<String> toateBiletele() {
        apel();
        out.println("#toateBiletele");
        ArrayList<String> rez = new ArrayList<>();
        String aux = "";

        try {
            long startTime = System.currentTimeMillis();
            long timeout = 5000; // 5 seconds timeout
            Thread.sleep(2000);
            aux = in.readLine();
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Timeout while waiting for server response.");
            }

            rez = splitTickets(aux);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return rez;
    }

    public static ArrayList<String> splitTickets(String tickets) {
        ArrayList<String> ticketList = new ArrayList<>();
        if (tickets == null) {
            throw new IllegalArgumentException("Tickets data cannot be null");
        }

        String[] tokens = tickets.split(" ");
        StringBuilder ticket = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            ticket.append(tokens[i]).append(" ");
            // Assuming the ticket info is composed of 3 tokens (e.g., flight number, passenger name, ticket number)
            if ((i + 1) % 3 == 0) {
                ticketList.add(ticket.toString().trim());
                ticket.setLength(0);
            }
        }
        return ticketList;
    }

    @Override
    public void updateBilet(int nrZbor, String nume, int nrBilet) {
        apel();
        out.println("#updateBilet " + nrZbor + " " + nume + " " + nrBilet);
    }

    @Override
    public void stergeBilet(String nrBilet) {
        apel();
        out.println("#stergeBilet " + nrBilet);
    }

    public static ArrayList<String> extractTicketInfo(String input) {
        ArrayList<String> ticketDetails = new ArrayList<>();
        String regex = "bilet:(\\d+)\\s+(\\w+)\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String flightNumber = matcher.group(1);
            String passengerName = matcher.group(2);
            String ticketNumber = matcher.group(3);
            String ticketInfo = "Număr zbor: " + flightNumber + ", Nume pasager: " + passengerName + ", Număr bilet: " + ticketNumber;
            ticketDetails.add(ticketInfo);
        }

        return ticketDetails;
    }
}
