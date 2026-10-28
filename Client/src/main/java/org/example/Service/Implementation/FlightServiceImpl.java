package org.example.Service.Implementation;


import org.example.Service.IFlightService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightServiceImpl implements IFlightService {

    public Socket socket;
    public  BufferedReader in=null;
    public PrintWriter out=null;
    public FlightServiceImpl()
    {

    }


    void apel()
    {
        try {
            socket = new Socket("localhost",12345);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String cautaZboruri(String plecare, String destinatie) { apel();
      out.println("#cautaZborPD "+plecare+" "+destinatie);
      try {
         String zboruri=in.readLine();

        return zboruri;
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    }

    @Override
    public ArrayList<String> toateZborurile() {
        apel();
        out.println("#toateZborurile");
        ArrayList<String> rez = new ArrayList<>();
        String aux = "";

        try {
            long startTime = System.currentTimeMillis();
            long timeout = 5000; // 5 seconds timeout
            Thread.sleep(2000);
            aux = in.readLine() ;
                if (System.currentTimeMillis() - startTime > timeout) {
                    throw new RuntimeException("Timeout while waiting for server response.");
                }

            rez = splitFlights(aux);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return rez;
    }


    public static ArrayList<String> splitFlights(String flights) {
        ArrayList<String> flightList = new ArrayList<>();
        if (flights == null) {
            throw new IllegalArgumentException("Flights data cannot be null");
        }

        String[] tokens = flights.split(" ");
        StringBuilder flight = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            flight.append(tokens[i]).append(" ");
            // Check if this is the end of a flight info (i.e., the number of seats is the 4th token)
            if ((i + 1) % 4 == 0) {
                flightList.add(flight.toString().trim());
                flight.setLength(0);
            }
        }
        return flightList;
    }

   @Override
   public void updateZbor(String plecare ,String destinatie, Integer nrZbor, Integer nr) { apel();
       out.println("#updateZbor "+ plecare+" "+destinatie+" "+nrZbor+" "+nr);
   }

    @Override
    public void adaugaZbor(String plecare,String destinatie ,Integer numar ){ apel();
        out.println("#adaugaZbor "+plecare+" "+destinatie+" "+numar);
    }
    @Override
    public void stergeZbor(String numar ){ apel();
        out.println("#stergeZbor "+numar);
    }

    @Override
    public String cautaZbor(String numar ){ apel();
        out.println("#zborCuNr "+numar);
        String rez="";
        try {
            rez=in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rez;
    }

    public static ArrayList<String> extractFlightInfo(String input) {
        ArrayList<String> flightDetails = new ArrayList<>();
        String regex = "zbor:(\\d+)\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String flightNumber = matcher.group(1);
            String departure = matcher.group(2);
            String destination = matcher.group(3);
            String seats = matcher.group(4);
            String flightInfo = "Număr zbor: " + flightNumber + ", Plecare: " + departure + ", Destinație: " + destination + ", Locuri: " + seats;
            flightDetails.add(flightInfo);
        }

        return flightDetails;
    }

}
