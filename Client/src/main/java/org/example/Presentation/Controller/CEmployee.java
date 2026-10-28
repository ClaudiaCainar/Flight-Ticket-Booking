package org.example.Presentation.Controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.example.Service.IFlightService;
import org.example.Service.ITicketService;
import org.example.Service.Implementation.FlightServiceImpl;
import org.example.Service.Implementation.TicketServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class CEmployee {
    private JTextField nrZbor, destinatie, plecare, nume, nrBilet, nrZborBilet;
    private JTable tabelZbor, tableBilete;
    private IFlightService IFlightService = new FlightServiceImpl();
    private ITicketService ITicketService = new TicketServiceImpl();

    public CEmployee(JTextField nrZbor, JTextField destinatie, JTextField plecare, JTextField nume, JTextField nrBilet, JTextField nrZborBilet, JTable tabelZbor, JTable tableBilete) {
        this.nrBilet = nrBilet;
        this.nrZbor = nrZbor;
        this.destinatie = destinatie;
        this.plecare = plecare;
        this.nume = nume;
        this.nrZborBilet = nrZborBilet;
        this.tabelZbor = tabelZbor;
        this.tableBilete = tableBilete;
    }


    public ActionListener adaugaZbor() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IFlightService.adaugaZbor(plecare.getText(), destinatie.getText(), Integer.parseInt(nrZbor.getText()));
            }
        };
        return e;
    }

        public ActionListener zborCuNr() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String z = IFlightService.cautaZbor(nrZbor.getText());
                DefaultTableModel model = (DefaultTableModel) tabelZbor.getModel();

                model.setRowCount(0);
                if (z == null) {
                    model.addRow(new Object[]{"NU EXISTA", "ZBORUL CU", "NR " + nrZbor.getText()});
                } else {
                    String[] s=z.split(" ");
                    model.addRow(new Object[]{s[1], s[2], s[0], s[3]});
                }

            }
        };
        return e;
    }

    public ActionListener stergeZbor() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IFlightService.stergeZbor(nrZbor.getText());
            }
        };
        return e;
    }

    public ActionListener veziTot() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> zboruri = IFlightService.toateZborurile();

                DefaultTableModel model = (DefaultTableModel) tabelZbor.getModel();

                model.setRowCount(0);
                if (zboruri.size() == 0) {
                    model.addRow(new Object[]{"NU", "EXISTA", "ZBORURI"});
                } else {
                    for (String z : zboruri) {
                        String[] s=z.split(" ");

                        model.addRow(new Object[]{s[1], s[2], s[0], s[3]});
                    }
                }
            }
        };
        return e;
    }

    public ActionListener updateZbor() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IFlightService.updateZbor(plecare.getText(), destinatie.getText(), Integer.parseInt(nrZbor.getText()), 200);
            }
        };
        return e;
    }


    public void createJSONFile(ArrayList<String> zboruri) {
        for (String s : zboruri) {
            System.out.println(s);
        }

        try (FileWriter file = new FileWriter("flights.json")) {
            file.write("[\n");
            for (int i = 0; i < zboruri.size(); i++) {
                String s = zboruri.get(i);
                String[] parts = s.split(" ");
                String id = parts[1];
                String plecare = parts[1];
                String destinatie = parts[2];
                String nrLocuri = parts[3];

                file.write("{ \"id\": \"" + id + "\", \"plecare\": \"" + plecare + "\", \"destinatie\": \"" + destinatie + "\", \"nrLocuri\": \"" + nrLocuri + "\" }");
                if (i < zboruri.size() - 1) {
                    file.write(",");
                }
                file.write("\n");
            }
            file.write("]");
            file.flush();
            System.out.println("Fișierul JSON a fost creat cu succes.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createXMLFile(ArrayList<String> zboruri) {
        try {
            // Construim manual structura XML
            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            xmlBuilder.append("<zboruri>\n");
            for (String zbor : zboruri) {
                String[] parts = zbor.split(" ");
                String id = parts[0];
                String plecare = parts[1];
                String destinatie = parts[2];
                String nrLocuri = parts[3];
                xmlBuilder.append("  <zbor>\n");
                xmlBuilder.append("    <id>").append(id).append("</id>\n");
                xmlBuilder.append("    <plecare>").append(plecare).append("</plecare>\n");
                xmlBuilder.append("    <destinatie>").append(destinatie).append("</destinatie>\n");
                xmlBuilder.append("    <nrLocuri>").append(nrLocuri).append("</nrLocuri>\n");
                xmlBuilder.append("  </zbor>\n");
            }
            xmlBuilder.append("</zboruri>");

            // Salvăm structura XML în fișier
            FileWriter writer = new FileWriter("flights.xml");
            writer.write(xmlBuilder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCSVFile(ArrayList<String> zboruri) {
        try {
            FileWriter writer = new FileWriter("flights.csv");
            for (int i = 0; i < zboruri.size(); i++) {
                String s = zboruri.get(i);
                String[] parts = s.split(" ");
                String id = parts[0];
                String plecare = parts[1];
                String destinatie = parts[2];
                String nrLocuri = parts[3];

                writer.append(id).append(",").append(plecare).append(",").append(destinatie).append(",").append(nrLocuri);
                if (i < zboruri.size() - 1) {
                    writer.append("\n");
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDOCFile(ArrayList<String> zboruri) {
        try {
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream("flights.docx");
            XWPFParagraph paragraph = document.createParagraph();

            for (int i = 0; i < zboruri.size(); i++) {
                String s = zboruri.get(i);
                String[] parts = s.split(" ");
                String id = parts[0];
                String plecare = parts[1];
                String destinatie = parts[2];
                String nrLocuri = parts[3];

                String zborJson = "Zbor" + i + "  ---->    id\": \"" + id + "\", \"plecare\": \"" + plecare + "\", \"destinatie\": \"" + destinatie + "\", \"nrLocuri\": \"" + nrLocuri + "\" ";

                paragraph.createRun().setText(zborJson);
                if (i < zboruri.size() - 1) {
                    paragraph.createRun().addBreak();
                }
            }

            document.write(out);
            out.close();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ActionListener export() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> vec = IFlightService.toateZborurile();

                createCSVFile(vec);
                createDOCFile(vec);
                createJSONFile(vec);
                createXMLFile(vec);
            }
        };
        return e;
    }


    public ActionListener adaugaBilet() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ITicketService.adaugaBilet(Integer.parseInt(nrZborBilet.getText()), nume.getText(), Integer.parseInt(nrBilet.getText()));
            }
        };
        return e;
    }

    public ActionListener cautaNrBilet() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String b = ITicketService.cautaBilet(nrBilet.getText());
                DefaultTableModel model = (DefaultTableModel) tableBilete.getModel();

                model.setRowCount(0);
                if (b == null) {
                    model.addRow(new Object[]{"NU", "EXISTA", "BILETUL CU NR " + nrBilet.getText()});
                } else {
                    String[] s = b.split(" ");
                    model.addRow(new Object[]{s[0], s[1]});
                }
            }
        };
        return e;
    }

    public ActionListener stergeBilet() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ITicketService.stergeBilet(nrBilet.getText());
            }
        };
        return e;
    }

    public ActionListener veziToateBiletele() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> bilete = ITicketService.toateBiletele();

                DefaultTableModel model = (DefaultTableModel) tableBilete.getModel();

                model.setRowCount(0);
                if (bilete.size() == 0) {
                    model.addRow(new Object[]{"NU", "EXISTA", "BILETE"});
                } else {
                    for (String b : bilete) {
                        String[] s = b.split(" ");
                        model.addRow(new Object[]{s[0], s[1], s[2]});
                    }
                }
            }
        };
        return e;
    }

    public ActionListener updateBilet() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ITicketService.updateBilet(Integer.parseInt(nrZborBilet.getText()), nume.getText(), Integer.parseInt(nrBilet.getText()));
            }
        };
        return e;
    }

    public ActionListener cauta() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> zboruri = FlightServiceImpl.splitFlights( IFlightService.cautaZboruri(plecare.getText(), destinatie.getText()));

                DefaultTableModel model = (DefaultTableModel) tabelZbor.getModel();

                model.setRowCount(0);
                if (zboruri.size() == 0) {
                    model.addRow(new Object[]{"NU", "EXISTA", "ZBORURI"});
                } else {
                    for (String z : zboruri) {
                        String s[] = z.split(" ");
                        model.addRow(new Object[]{s[0], s[1], s[2], s[3]});
                    }
                }
            }
        };
        return e;
    }

//    public ActionListener veziBileteNume() {
//        ActionListener e = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                ArrayList<Bilet> bilete = RepoBilete.getBilet(nume.getText());
//
//                DefaultTableModel model = (DefaultTableModel) tableBilete.getModel();
//
//                model.setRowCount(0);
//                if (bilete.size() == 0) {
//                    model.addRow(new Object[]{"NU", "EZISTA", "ZBORURI"});
//                } else {
//                    for (Bilet b : bilete) {
//                        model.addRow(new Object[]{b.getNrBilet(), b.getNume(), b.getNrZbor()});
//                    }
//                }
//            }
//        };
//        return e;
//    }

    public ActionListener statistici() {
        ActionListener e = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightCharts charts = new FlightCharts();
                charts.setVisible(true);
            }
        };
        return e;

    }


    public class FlightCharts extends JFrame {
        public FlightCharts() {
            setTitle("Flight Charts");
            setSize(800, 600);
            setLayout(new GridLayout(1, 2)); // Setăm layout-ul pentru a afișa două grafice pe rând

            DefaultPieDataset datasetLocatiePlecare = createDatasetLocatiePlecare();
            JFreeChart chartLocatiePlecare = createChart("Numarul de zboruri per locatie de plecare", datasetLocatiePlecare);
            add(new ChartPanel(chartLocatiePlecare));

            DefaultPieDataset datasetDestinatie = createDatasetDestinatie();
            JFreeChart chartDestinatie = createChart("Numarul de zboruri per destinatie", datasetDestinatie);
            add(new ChartPanel(chartDestinatie));

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        private DefaultPieDataset createDatasetLocatiePlecare() {
            DefaultPieDataset dataset = new DefaultPieDataset();
            String url = "jdbc:mysql://localhost:3306/ps_tema2";
            String user = "root";
            String password = "j5TZyhHV5@";

            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT plecare, COUNT(*) AS nrZboruri FROM zboruri GROUP BY plecare")) {

                while (resultSet.next()) {
                    String plecare = resultSet.getString("plecare");
                    int nrZboruri = resultSet.getInt("nrZboruri");
                    dataset.setValue(plecare, nrZboruri);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return dataset;
        }

        private DefaultPieDataset createDatasetDestinatie() {
            DefaultPieDataset dataset = new DefaultPieDataset();
            String url = "jdbc:mysql://localhost:3306/ps_tema2";
            String user = "root";
            String password = "j5TZyhHV5@";

            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT destinatie, COUNT(*) AS nrZboruri FROM zboruri GROUP BY destinatie")) {

                while (resultSet.next()) {
                    String destinatie = resultSet.getString("destinatie");
                    int nrZboruri = resultSet.getInt("nrZboruri");
                    dataset.setValue(destinatie, nrZboruri);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return dataset;
        }

        private JFreeChart createChart(String title, DefaultPieDataset dataset) {
            JFreeChart chart = ChartFactory.createPieChart(
                    title,
                    dataset,
                    true,
                    true,
                    false
            );

            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setCircular(true);
            plot.setLabelGap(0.02);
            return chart;
        }
    }


}

