import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        CRS crs = new CRS();

        int musteriCounter[] = {0} ;
        int appointmentCounter[] = {0};

        //Şuan sistemde 10 adet çamaşır makinesi 10 adet kurutma makinesi ve 10 adet ütü var
        //Sistemde kayıtlı kullanıcı veya rezervasyon yok

        crs.loadAppointments();
        crs.loadCustomers();
        crs.loadDryers();
        crs.loadIroners();
        crs.loadWashingMachines();




            JFrame frame = new JFrame();
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(550, 700);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            JLabel mainText = new JLabel();
            mainText.setBounds(40, 30, 600, 50);
            mainText.setText("Çamaşırhane Sistemi");
            mainText.setFont(new Font(null, Font.PLAIN, 40));
            JLabel instructions = new JLabel();
            instructions.setBounds(40, 90, 600, 25);
            instructions.setText("Yapmak istediğinin işlemi seçiniz:");
            instructions.setFont(new Font(null, Font.PLAIN, 20));
            JPanel mainPanel = new JPanel();
            mainPanel.add(mainText);
            mainPanel.add(instructions);
            mainPanel.setLayout(null);
            mainPanel.setBounds(20, 20, 600, 600);
            frame.add(mainPanel);
            JButton button1 = new JButton();
            JButton button2 = new JButton();
            JButton button3 = new JButton();
            JButton button4 = new JButton();
            JButton button5 = new JButton();
            JButton button6 = new JButton();
            button1.setBounds(40, 140, 400, 60);
            button2.setBounds(40, 215, 400, 60);
            button3.setBounds(40, 290, 400, 60);
            button4.setBounds(40, 365, 400, 60);
            button5.setBounds(40, 440, 400, 60);
            button6.setBounds(40, 515, 400, 60);
            button1.setText("Randevu Yap");
            button1.setFont(new Font(null, Font.PLAIN, 20));
            button2.setText("Randevu İptal Et");
            button2.setFont(new Font(null, Font.PLAIN, 20));
            button3.setText("Tüm Randevuları Listele");
            button3.setFont(new Font(null, Font.PLAIN, 20));
            button4.setText("Müşterinin Randevularını Listele");
            button4.setFont(new Font(null, Font.PLAIN, 20));
            button5.setText("Bakiye Yükle");
            button5.setFont(new Font(null, Font.PLAIN, 20));
            button6.setText("Tüm Müşterileri Listele");
            button6.setFont(new Font(null, Font.PLAIN, 20));
            button1.setFocusable(Boolean.FALSE);
            button2.setFocusable(Boolean.FALSE);
            button3.setFocusable(Boolean.FALSE);
            button4.setFocusable(Boolean.FALSE);
            button5.setFocusable(Boolean.FALSE);
            button6.setFocusable(Boolean.FALSE);
            mainPanel.add(button1);
            mainPanel.add(button2);
            mainPanel.add(button3);
            mainPanel.add(button4);
            mainPanel.add(button5);
            mainPanel.add(button6);

        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame randevuEkle = new JFrame();
                    randevuEkle.setLayout(null);
                    randevuEkle.setSize(630, 760);
                    randevuEkle.setResizable(false);
                    randevuEkle.setVisible(true);
                    randevuEkle.setLocationRelativeTo(null);
                    randevuEkle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JPanel randevuPanel = new JPanel();
                    randevuPanel.setLayout(null);
                    randevuPanel.setBounds(0, 0, 630, 760);
                    randevuEkle.add(randevuPanel);
                    JLabel randevuEkle1 = new JLabel("Randevu için Gerekli Bilgileri Giriniz:");
                    randevuEkle1.setFont(new Font(null, Font.PLAIN, 25));
                    randevuEkle1.setBounds(20, 20, 480, 50);
                    randevuPanel.add(randevuEkle1);
                    JLabel musteri = new JLabel("Müşteri ID:");
                    musteri.setFont(new Font(null, Font.PLAIN, 20));
                    musteri.setBounds(20, 80, 480, 50);
                    JLabel musteriKayit = new JLabel("Müşteri Kayıtlı Değil ise:");
                    JButton kayitButton = new JButton("Kayıt");
                    kayitButton.setFocusable(Boolean.FALSE);
                    kayitButton.setFont(new Font(null, Font.PLAIN, 14));
                    kayitButton.setBounds(420, 127, 70, 25);
                    musteriKayit.setFont(new Font(null, Font.PLAIN, 17));
                    musteriKayit.setBounds(20, 112, 480, 50);
                    JLabel yikama = new JLabel("Yıkama İstiyor Musunuz?");
                    yikama.setFont(new Font(null, Font.PLAIN, 20));
                    yikama.setBounds(20, 150, 480, 50);
                    JLabel kurutma = new JLabel("Kurutma İstiyor Musunuz?");
                    kurutma.setFont(new Font(null, Font.PLAIN, 20));
                    kurutma.setBounds(20, 195, 480, 50);
                    JLabel utuleme = new JLabel("Ütüleme İstiyor Musunuz?");
                    utuleme.setFont(new Font(null, Font.PLAIN, 20));
                    utuleme.setBounds(20, 240, 480, 50);
                    JLabel randevuVakit = new JLabel("Randevu Vakti(yyyy-MM-dd HH:mm):");
                    randevuVakit.setFont(new Font(null, Font.PLAIN, 20));
                    randevuVakit.setBounds(20, 550, 480, 50);
                    JLabel kiyafet = new JLabel("Yıkayacağınız Kıyafetleri Seçiniz:");
                    kiyafet.setFont(new Font(null, Font.PLAIN, 20));
                    kiyafet.setBounds(20, 285, 480, 50);
                    JLabel tshirtLabel = new JLabel("T-Shirt");
                    tshirtLabel.setFont(new Font(null, Font.PLAIN, 16));
                    tshirtLabel.setBounds(30, 320, 480, 50);
                    JCheckBox tshirtCheck = new JCheckBox();
                    tshirtCheck.setFont(new Font(null, Font.PLAIN, 20));
                    tshirtCheck.setBounds(130, 330, 40, 30);
                    JTextField tshirtRenkliText = new JTextField("0");
                    tshirtRenkliText.setFont(new Font(null, Font.PLAIN, 15));
                    tshirtRenkliText.setBounds(270, 335, 100, 20);
                    randevuPanel.add(tshirtRenkliText);
                    tshirtRenkliText.setVisible(false);
                    JLabel tshirtRenkliMiktarLabel = new JLabel("Renkli Miktar:");
                    tshirtRenkliMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    tshirtRenkliMiktarLabel.setBounds(170, 318, 480, 50);
                    randevuPanel.add(tshirtRenkliMiktarLabel);
                    tshirtRenkliMiktarLabel.setVisible(false);
                    JTextField tshirtBeyazText = new JTextField("0");
                    tshirtBeyazText.setFont(new Font(null, Font.PLAIN, 15));
                    tshirtBeyazText.setBounds(480, 335, 100, 20);
                    randevuPanel.add(tshirtBeyazText);
                    tshirtBeyazText.setVisible(false);
                    JLabel tshirtBeyazMiktarLabel = new JLabel("Beyaz Miktar:");
                    tshirtBeyazMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    tshirtBeyazMiktarLabel.setBounds(380, 318, 480, 50);
                    randevuPanel.add(tshirtBeyazMiktarLabel);
                    tshirtBeyazMiktarLabel.setVisible(false);
                    tshirtCheck.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (tshirtCheck.isSelected()) {
                                tshirtRenkliMiktarLabel.setVisible(true);
                                tshirtRenkliText.setVisible(true);
                                tshirtBeyazMiktarLabel.setVisible(true);
                                tshirtBeyazText.setVisible(true);
                            } else {
                                tshirtRenkliMiktarLabel.setVisible(false);
                                tshirtRenkliText.setVisible(false);
                                tshirtBeyazMiktarLabel.setVisible(false);
                                tshirtBeyazText.setVisible(false);
                            }
                        }
                    });
                    randevuPanel.add(tshirtCheck);
                    randevuPanel.add(tshirtLabel);
                    JLabel gomlekLabel = new JLabel("Gömlek");
                    gomlekLabel.setFont(new Font(null, Font.PLAIN, 16));
                    gomlekLabel.setBounds(30, 350, 480, 50);
                    JCheckBox gomlekCheck = new JCheckBox();
                    gomlekCheck.setFont(new Font(null, Font.PLAIN, 20));
                    gomlekCheck.setBounds(130, 360, 40, 30);
                    randevuPanel.add(gomlekCheck);
                    randevuPanel.add(gomlekLabel);
                    JTextField gomlekRenkliText = new JTextField("0");
                    gomlekRenkliText.setFont(new Font(null, Font.PLAIN, 15));
                    gomlekRenkliText.setBounds(270, 365, 100, 20);
                    randevuPanel.add(gomlekRenkliText);
                    gomlekRenkliText.setVisible(false);
                    JLabel gomlekRenkliMiktarLabel = new JLabel("Renkli Miktar:");
                    gomlekRenkliMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    gomlekRenkliMiktarLabel.setBounds(170, 348, 480, 50);
                    randevuPanel.add(gomlekRenkliMiktarLabel);
                    gomlekRenkliMiktarLabel.setVisible(false);
                    JTextField gomlekBeyazText = new JTextField("0");
                    gomlekBeyazText.setFont(new Font(null, Font.PLAIN, 15));
                    gomlekBeyazText.setBounds(480, 365, 100, 20);
                    randevuPanel.add(gomlekBeyazText);
                    gomlekBeyazText.setVisible(false);

                    JLabel gomlekBeyazMiktarLabel = new JLabel("Beyaz Miktar:");
                    gomlekBeyazMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    gomlekBeyazMiktarLabel.setBounds(380, 348, 480, 50);
                    randevuPanel.add(gomlekBeyazMiktarLabel);
                    gomlekBeyazMiktarLabel.setVisible(false);

                    gomlekCheck.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (gomlekCheck.isSelected()) {
                                gomlekRenkliMiktarLabel.setVisible(true);
                                gomlekRenkliText.setVisible(true);
                                gomlekBeyazMiktarLabel.setVisible(true);
                                gomlekBeyazText.setVisible(true);
                            } else {
                                gomlekRenkliMiktarLabel.setVisible(false);
                                gomlekRenkliText.setVisible(false);
                                gomlekBeyazMiktarLabel.setVisible(false);
                                gomlekBeyazText.setVisible(false);
                            }
                        }
                    });

                    JLabel etekLabel = new JLabel("Etek");
                    etekLabel.setFont(new Font(null, Font.PLAIN, 16));
                    etekLabel.setBounds(30, 380, 480, 50);
                    JCheckBox etekCheck = new JCheckBox();
                    etekCheck.setFont(new Font(null, Font.PLAIN, 20));
                    etekCheck.setBounds(130, 390, 40, 30);
                    randevuPanel.add(etekCheck);
                    randevuPanel.add(etekLabel);
                    JTextField etekRenkliText = new JTextField("0");
                    etekRenkliText.setFont(new Font(null, Font.PLAIN, 15));
                    etekRenkliText.setBounds(270, 395, 100, 20);
                    randevuPanel.add(etekRenkliText);
                    etekRenkliText.setVisible(false);
                    JLabel etekRenkliMiktarLabel = new JLabel("Renkli Miktar:");
                    etekRenkliMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    etekRenkliMiktarLabel.setBounds(170, 378, 480, 50);
                    randevuPanel.add(etekRenkliMiktarLabel);
                    etekRenkliMiktarLabel.setVisible(false);
                    JTextField etekBeyazText = new JTextField("0");
                    etekBeyazText.setFont(new Font(null, Font.PLAIN, 15));
                    etekBeyazText.setBounds(480, 395, 100, 20);
                    randevuPanel.add(etekBeyazText);
                    etekBeyazText.setVisible(false);

                    JLabel etekBeyazMiktarLabel = new JLabel("Beyaz Miktar:");
                    etekBeyazMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    etekBeyazMiktarLabel.setBounds(380, 378, 480, 50);
                    randevuPanel.add(etekBeyazMiktarLabel);
                    etekBeyazMiktarLabel.setVisible(false);

                    etekCheck.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (etekCheck.isSelected()) {
                                etekRenkliMiktarLabel.setVisible(true);
                                etekRenkliText.setVisible(true);
                                etekBeyazMiktarLabel.setVisible(true);
                                etekBeyazText.setVisible(true);
                            } else {
                                etekRenkliMiktarLabel.setVisible(false);
                                etekRenkliText.setVisible(false);
                                etekBeyazMiktarLabel.setVisible(false);
                                etekBeyazText.setVisible(false);
                            }
                        }
                    });

                    JLabel pantolonLabel = new JLabel("Pantolon");
                    pantolonLabel.setFont(new Font(null, Font.PLAIN, 16));
                    pantolonLabel.setBounds(30, 410, 480, 50);
                    JCheckBox pantolonCheck = new JCheckBox();
                    pantolonCheck.setFont(new Font(null, Font.PLAIN, 20));
                    pantolonCheck.setBounds(130, 420, 40, 30);
                    randevuPanel.add(pantolonCheck);
                    randevuPanel.add(pantolonLabel);
                    JTextField pantolonRenkliText = new JTextField("0");
                    pantolonRenkliText.setFont(new Font(null, Font.PLAIN, 15));
                    pantolonRenkliText.setBounds(270, 425, 100, 20);
                    randevuPanel.add(pantolonRenkliText);
                    pantolonRenkliText.setVisible(false);
                    JLabel pantolonRenkliMiktarLabel = new JLabel("Renkli Miktar:");
                    pantolonRenkliMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    pantolonRenkliMiktarLabel.setBounds(170, 408, 480, 50);
                    randevuPanel.add(pantolonRenkliMiktarLabel);
                    pantolonRenkliMiktarLabel.setVisible(false);
                    JTextField pantolonBeyazText = new JTextField("0");
                    pantolonBeyazText.setFont(new Font(null, Font.PLAIN, 15));
                    pantolonBeyazText.setBounds(480, 425, 100, 20);
                    randevuPanel.add(pantolonBeyazText);
                    pantolonBeyazText.setVisible(false);

                    JLabel pantolonBeyazMiktarLabel = new JLabel("Beyaz Miktar:");
                    pantolonBeyazMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    pantolonBeyazMiktarLabel.setBounds(380, 408, 480, 50);
                    randevuPanel.add(pantolonBeyazMiktarLabel);
                    pantolonBeyazMiktarLabel.setVisible(false);

                    pantolonCheck.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (pantolonCheck.isSelected()) {
                                pantolonRenkliMiktarLabel.setVisible(true);
                                pantolonRenkliText.setVisible(true);
                                pantolonBeyazMiktarLabel.setVisible(true);
                                pantolonBeyazText.setVisible(true);
                            } else {
                                pantolonRenkliMiktarLabel.setVisible(false);
                                pantolonRenkliText.setVisible(false);
                                pantolonBeyazMiktarLabel.setVisible(false);
                                pantolonBeyazText.setVisible(false);
                            }
                        }
                    });
                    JLabel kazakLabel = new JLabel("Kazak");
                    kazakLabel.setFont(new Font(null, Font.PLAIN, 16));
                    kazakLabel.setBounds(30, 440, 480, 50);
                    JCheckBox kazakCheck = new JCheckBox();
                    kazakCheck.setFont(new Font(null, Font.PLAIN, 20));
                    kazakCheck.setBounds(130, 450, 40, 30);
                    randevuPanel.add(kazakCheck);
                    randevuPanel.add(kazakLabel);
                    JTextField kazakRenkliText = new JTextField("0");
                    kazakRenkliText.setFont(new Font(null, Font.PLAIN, 15));
                    kazakRenkliText.setBounds(270, 455, 100, 20);
                    randevuPanel.add(kazakRenkliText);
                    kazakRenkliText.setVisible(false);
                    JLabel kazakRenkliMiktarLabel = new JLabel("Renkli Miktar:");
                    kazakRenkliMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    kazakRenkliMiktarLabel.setBounds(170, 438, 480, 50);
                    randevuPanel.add(kazakRenkliMiktarLabel);
                    kazakRenkliMiktarLabel.setVisible(false);
                    JTextField kazakBeyazText = new JTextField("0");
                    kazakBeyazText.setFont(new Font(null, Font.PLAIN, 15));
                    kazakBeyazText.setBounds(480, 455, 100, 20);
                    randevuPanel.add(kazakBeyazText);
                    kazakBeyazText.setVisible(false);

                    JLabel kazakBeyazMiktarLabel = new JLabel("Beyaz Miktar:");
                    kazakBeyazMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    kazakBeyazMiktarLabel.setBounds(380, 438, 480, 50);
                    randevuPanel.add(kazakBeyazMiktarLabel);
                    kazakBeyazMiktarLabel.setVisible(false);

                    kazakCheck.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (kazakCheck.isSelected()) {
                                kazakRenkliMiktarLabel.setVisible(true);
                                kazakRenkliText.setVisible(true);
                                kazakBeyazMiktarLabel.setVisible(true);
                                kazakBeyazText.setVisible(true);
                            } else {
                                kazakRenkliMiktarLabel.setVisible(false);
                                kazakRenkliText.setVisible(false);
                                kazakBeyazMiktarLabel.setVisible(false);
                                kazakBeyazText.setVisible(false);
                            }
                        }
                    });
                    JLabel ceketLabel = new JLabel("Ceket");
                    ceketLabel.setFont(new Font(null, Font.PLAIN, 16));
                    ceketLabel.setBounds(30, 470, 480, 50);
                    JCheckBox ceketCheck = new JCheckBox();
                    ceketCheck.setFont(new Font(null, Font.PLAIN, 20));
                    ceketCheck.setBounds(130, 480, 40, 30);
                    randevuPanel.add(ceketCheck);
                    randevuPanel.add(ceketLabel);
                    JTextField ceketRenkliText = new JTextField("0");
                    ceketRenkliText.setFont(new Font(null, Font.PLAIN, 15));
                    ceketRenkliText.setBounds(270, 485, 100, 20);
                    randevuPanel.add(ceketRenkliText);
                    ceketRenkliText.setVisible(false);
                    JLabel ceketRenkliMiktarLabel = new JLabel("Renkli Miktar:");
                    ceketRenkliMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    ceketRenkliMiktarLabel.setBounds(170, 468, 480, 50);
                    randevuPanel.add(ceketRenkliMiktarLabel);
                    ceketRenkliMiktarLabel.setVisible(false);
                    JTextField ceketBeyazText = new JTextField("0");
                    ceketBeyazText.setFont(new Font(null, Font.PLAIN, 15));
                    ceketBeyazText.setBounds(480, 485, 100, 20);
                    randevuPanel.add(ceketBeyazText);
                    ceketBeyazText.setVisible(false);

                    JLabel ceketBeyazMiktarLabel = new JLabel("Beyaz Miktar:");
                    ceketBeyazMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    ceketBeyazMiktarLabel.setBounds(380, 468, 480, 50);
                    randevuPanel.add(ceketBeyazMiktarLabel);
                    ceketBeyazMiktarLabel.setVisible(false);

                    ceketCheck.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (ceketCheck.isSelected()) {
                                ceketRenkliMiktarLabel.setVisible(true);
                                ceketRenkliText.setVisible(true);
                                ceketBeyazMiktarLabel.setVisible(true);
                                ceketBeyazText.setVisible(true);
                            } else {
                                ceketRenkliMiktarLabel.setVisible(false);
                                ceketRenkliText.setVisible(false);
                                ceketBeyazMiktarLabel.setVisible(false);
                                ceketBeyazText.setVisible(false);
                            }
                        }
                    });
                    JLabel takimLabel = new JLabel("Takim");
                    takimLabel.setFont(new Font(null, Font.PLAIN, 16));
                    takimLabel.setBounds(30, 500, 480, 50);
                    JCheckBox takimCheck = new JCheckBox();
                    takimCheck.setFont(new Font(null, Font.PLAIN, 20));
                    takimCheck.setBounds(130, 510, 40, 30);
                    randevuPanel.add(takimCheck);
                    randevuPanel.add(takimLabel);
                    JTextField takimRenkliText = new JTextField("0");
                    takimRenkliText.setFont(new Font(null, Font.PLAIN, 15));
                    takimRenkliText.setBounds(270, 515, 100, 20);
                    randevuPanel.add(takimRenkliText);
                    takimRenkliText.setVisible(false);
                    JLabel takimRenkliMiktarLabel = new JLabel("Renkli Miktar:");
                    takimRenkliMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    takimRenkliMiktarLabel.setBounds(170, 498, 480, 50);
                    randevuPanel.add(takimRenkliMiktarLabel);
                    takimRenkliMiktarLabel.setVisible(false);
                    JTextField takimBeyazText = new JTextField("0");
                    takimBeyazText.setFont(new Font(null, Font.PLAIN, 15));
                    takimBeyazText.setBounds(480, 515, 100, 20);
                    randevuPanel.add(takimBeyazText);
                    takimBeyazText.setVisible(false);

                    JLabel takimBeyazMiktarLabel = new JLabel("Beyaz Miktar:");
                    takimBeyazMiktarLabel.setFont(new Font(null, Font.PLAIN, 16));
                    takimBeyazMiktarLabel.setBounds(380, 498, 480, 50);
                    randevuPanel.add(takimBeyazMiktarLabel);
                    takimBeyazMiktarLabel.setVisible(false);

                    takimCheck.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (takimCheck.isSelected()) {
                                takimRenkliMiktarLabel.setVisible(true);
                                takimRenkliText.setVisible(true);
                                takimBeyazMiktarLabel.setVisible(true);
                                takimBeyazText.setVisible(true);
                            } else {
                                takimRenkliMiktarLabel.setVisible(false);
                                takimRenkliText.setVisible(false);
                                takimBeyazMiktarLabel.setVisible(false);
                                takimBeyazText.setVisible(false);
                            }
                        }
                    });
                    randevuPanel.add(kayitButton);
                    randevuPanel.add(randevuVakit);
                    randevuPanel.add(yikama);
                    randevuPanel.add(kurutma);
                    randevuPanel.add(utuleme);
                    randevuPanel.add(musteri);
                    randevuPanel.add(musteriKayit);
                    randevuPanel.add(kiyafet);
                    JCheckBox yika = new JCheckBox();
                    yika.setBounds(418, 160, 50, 30);
                    randevuPanel.add(yika);
                    JCheckBox kurut = new JCheckBox();
                    kurut.setBounds(418, 205, 50, 30);
                    randevuPanel.add(kurut);
                    JCheckBox utu = new JCheckBox();
                    utu.setBounds(418, 250, 50, 30);
                    randevuPanel.add(utu);
                    yika.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (!yika.isSelected()) {
                                kurut.setSelected(false);
                            }
                            if (yika.isSelected() && utu.isSelected() && !kurut.isSelected()) {
                                utu.setSelected(false);
                            }
                        }
                    });
                    kurut.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (kurut.isSelected()) {
                                yika.setSelected(true);
                            }
                            if (!kurut.isSelected() && yika.isSelected() && utu.isSelected()) {
                                utu.setSelected(false);
                            }
                        }
                    });

                    utu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (utu.isSelected()) {
                                if (yika.isSelected() && !kurut.isSelected()) {
                                    yika.setSelected(false);
                                }
                            }
                        }
                    });
                    JLabel musteriBakiye = new JLabel("Bakiye: 0.0 TL");
                    musteriBakiye.setFont(new Font(null, Font.PLAIN, 20));
                    musteriBakiye.setBounds(20, 590, 300, 50);
                    randevuPanel.add(musteriBakiye);
                    JTextField musteriIdYaz = new JTextField();
                    musteriIdYaz.setFont(new Font(null, Font.PLAIN, 17));
                    musteriIdYaz.setBounds(420, 93, 100, 25);
                    musteriIdYaz.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            Customer customer = crs.findCustomerById(Integer.parseInt(musteriIdYaz.getText()));
                            if (customer != null) {
                                musteriBakiye.setText("Bakiye: " + customer.getBalance() + " TL");
                            } else {
                                musteriBakiye.setText("Bakiye: 0.0 TL");
                            }
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            Customer customer = crs.findCustomerById(Integer.parseInt(musteriIdYaz.getText()));
                            if (customer != null) {
                                musteriBakiye.setText("Bakiye: " + customer.toString() + " TL");
                            } else {
                                musteriBakiye.setText("Bakiye: 0.0 TL");
                            }
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            Customer customer = crs.findCustomerById(Integer.parseInt(musteriIdYaz.getText()));
                            if (customer != null) {
                                musteriBakiye.setText("Bakiye: " + customer.toString() + " TL");
                            } else {
                                musteriBakiye.setText("Bakiye: 0.0 TL");
                            }
                        }
                    });
                    randevuPanel.add(musteriIdYaz);
                    JButton confirm = new JButton("Ekle");
                    confirm.setFont(new Font(null, Font.PLAIN, 15));
                    confirm.setBounds(225, 605, 180, 50);
                    confirm.setFocusable(Boolean.FALSE);
                    randevuPanel.add(confirm);
                    JTextField dateYaz = new JTextField("2025-12-03 13:45");
                    dateYaz.setFont(new Font(null, Font.PLAIN, 17));
                    dateYaz.setBounds(420, 563, 160, 25);
                    randevuPanel.add(dateYaz);
                    JLabel errorMessage = new JLabel();
                    errorMessage.setFont(new Font(null, Font.PLAIN, 18));
                    errorMessage.setForeground(Color.RED);
                    errorMessage.setBounds(40, 660, 550, 50);
                    errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
                    randevuPanel.add(errorMessage);
                    confirm.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String customerId = musteriIdYaz.getText();
                                boolean washing = yika.isSelected();
                                boolean drying = kurut.isSelected();
                                boolean ironing = utu.isSelected();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                Date desiredDate = sdf.parse(dateYaz.getText());
                                Map<String, Integer> clothingQuantitiesColor = new HashMap<>();
                                clothingQuantitiesColor.put("T-Shirt", tshirtCheck.isSelected() ? Integer.parseInt(tshirtRenkliText.getText()) : 0);
                                clothingQuantitiesColor.put("Gömlek", gomlekCheck.isSelected() ? Integer.parseInt(gomlekRenkliText.getText()) : 0);
                                clothingQuantitiesColor.put("Etek", etekCheck.isSelected() ? Integer.parseInt(etekRenkliText.getText()) : 0);
                                clothingQuantitiesColor.put("Pantolon", pantolonCheck.isSelected() ? Integer.parseInt(pantolonRenkliText.getText()) : 0);
                                clothingQuantitiesColor.put("Kazak", kazakCheck.isSelected() ? Integer.parseInt(kazakRenkliText.getText()) : 0);
                                clothingQuantitiesColor.put("Ceket", ceketCheck.isSelected() ? Integer.parseInt(ceketRenkliText.getText()) : 0);
                                clothingQuantitiesColor.put("Takım Elbise", takimCheck.isSelected() ? Integer.parseInt(takimRenkliText.getText()) : 0);

                                Map<String, Integer> clothingQuantitiesWhite = new HashMap<>();
                                clothingQuantitiesWhite.put("T-Shirt", tshirtCheck.isSelected() ? Integer.parseInt(tshirtBeyazText.getText()) : 0);
                                clothingQuantitiesWhite.put("Gömlek", gomlekCheck.isSelected() ? Integer.parseInt(gomlekBeyazText.getText()) : 0);
                                clothingQuantitiesWhite.put("Etek", etekCheck.isSelected() ? Integer.parseInt(etekBeyazText.getText()) : 0);
                                clothingQuantitiesWhite.put("Pantolon", pantolonCheck.isSelected() ? Integer.parseInt(pantolonBeyazText.getText()) : 0);
                                clothingQuantitiesWhite.put("Kazak", kazakCheck.isSelected() ? Integer.parseInt(kazakBeyazText.getText()) : 0);
                                clothingQuantitiesWhite.put("Ceket", ceketCheck.isSelected() ? Integer.parseInt(ceketBeyazText.getText()) : 0);
                                clothingQuantitiesWhite.put("Takım Elbise", takimCheck.isSelected() ? Integer.parseInt(takimBeyazText.getText()) : 0);
                                if (!clothingQuantitiesWhite.values().stream().anyMatch(v -> v > 0) && !clothingQuantitiesColor.values().stream().anyMatch(v -> v > 0)) {
                                    throw new NoCommand("Lütfen yıkanacak kıyafet giriniz");
                                }
                                Appointment appointment = crs.createAppointment(customerId, desiredDate, washing, drying, ironing, clothingQuantitiesColor, clothingQuantitiesWhite, appointmentCounter[0]++);
                                try {
                                    FileWriter writer = new FileWriter("counters.txt", false);
                                    writer.write(musteriCounter[0] + "\n" + appointmentCounter[0] + "\n");
                                    writer.close();
                                } catch (IOException e4) {
                                }

                                errorMessage.setForeground(new Color(100, 200, 100));
                                Customer customer = crs.findCustomerById(Integer.parseInt(musteriIdYaz.getText()));
                                if (customer != null) {
                                    musteriBakiye.setText("Bakiye: " + customer.getBalance() + " TL");
                                } else {
                                    musteriBakiye.setText("Bakiye: 0.0 TL");
                                }
                                errorMessage.setText("<html>Randevu başarıyla eklendi<br>Randevu No: " + appointment.getId());
                            } catch (ParseException e1) {
                                errorMessage.setForeground(Color.RED);
                                errorMessage.setText("Tarih formatı hatalı. Doğru format: yyyy-MM-dd HH:mm");
                            } catch (IDException e1) {
                                errorMessage.setForeground(Color.RED);
                                errorMessage.setText(e1.getMessage());
                            } catch (NoCommand e1) {
                                errorMessage.setForeground(Color.RED);
                                errorMessage.setText(e1.getMessage());
                            } catch (YetersizBakiye e1) {
                                errorMessage.setForeground(Color.RED);
                                errorMessage.setText(e1.getMessage());
                            } catch (NoAvailableMachine e1) {
                                errorMessage.setForeground(Color.RED);
                                errorMessage.setText(e1.getMessage());
                            } catch (Exception e2) {
                                errorMessage.setForeground(Color.RED);
                                errorMessage.setText("Beklenmeyen Hata: " + e2.getMessage());
                            }
                        }
                    });
                    kayitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFrame kayitFrame = new JFrame();
                            kayitFrame.setResizable(false);
                            kayitFrame.setSize(500, 380);
                            kayitFrame.setLocationRelativeTo(null);
                            kayitFrame.setVisible(true);
                            kayitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            JPanel kayitPanel = new JPanel();
                            kayitPanel.setLayout(null);
                            kayitPanel.setBounds(0, 0, 300, 360);
                            kayitFrame.add(kayitPanel);
                            JLabel kayitLabel = new JLabel("Müşterinin bilgilerini giriniz:");
                            kayitPanel.add(kayitLabel);
                            kayitLabel.setFont(new Font(null, Font.PLAIN, 22));
                            kayitLabel.setBounds(40, 20, 300, 30);
                            JLabel nationalIdLabel = new JLabel("Ulusal ID:");
                            kayitPanel.add(nationalIdLabel);
                            nationalIdLabel.setFont(new Font(null, Font.PLAIN, 18));
                            nationalIdLabel.setBounds(40, 60, 300, 30);
                            JTextField nationalIdField = new JTextField();
                            nationalIdField.setFont(new Font(null, Font.PLAIN, 17));
                            nationalIdField.setBounds(220, 60, 140, 25);
                            kayitPanel.add(nationalIdField);
                            JLabel nameLabel = new JLabel("İsim: ");
                            kayitPanel.add(nameLabel);
                            nameLabel.setFont(new Font(null, Font.PLAIN, 18));
                            nameLabel.setBounds(40, 95, 300, 30);
                            JTextField nameField = new JTextField();
                            nameField.setFont(new Font(null, Font.PLAIN, 17));
                            nameField.setBounds(220, 95, 200, 25);
                            kayitPanel.add(nameField);
                            JLabel telefonNumaraLabel = new JLabel("Telefon numarası: ");
                            kayitPanel.add(telefonNumaraLabel);
                            telefonNumaraLabel.setFont(new Font(null, Font.PLAIN, 18));
                            telefonNumaraLabel.setBounds(40, 130, 300, 30);
                            JTextField telefonNumaraField = new JTextField();
                            telefonNumaraField.setFont(new Font(null, Font.PLAIN, 17));
                            telefonNumaraField.setBounds(220, 130, 130, 25);
                            kayitPanel.add(telefonNumaraField);
                            JLabel emailLabel = new JLabel("E-mail: ");
                            kayitPanel.add(emailLabel);
                            emailLabel.setFont(new Font(null, Font.PLAIN, 18));
                            emailLabel.setBounds(40, 165, 300, 30);
                            JTextField emailField = new JTextField();
                            emailField.setFont(new Font(null, Font.PLAIN, 17));
                            emailField.setBounds(220, 165, 200, 25);
                            kayitPanel.add(emailField);
                            JLabel butceLabel = new JLabel("Bakiye: ");
                            kayitPanel.add(butceLabel);
                            butceLabel.setFont(new Font(null, Font.PLAIN, 18));
                            butceLabel.setBounds(40, 200, 300, 30);
                            JTextField butceField = new JTextField();
                            butceField.setFont(new Font(null, Font.PLAIN, 17));
                            butceField.setBounds(220, 200, 100, 25);
                            kayitPanel.add(butceField);
                            JButton kayitConf = new JButton("Kayıt");
                            kayitConf.setFocusable(Boolean.FALSE);
                            kayitConf.setFont(new Font(null, Font.PLAIN, 17));
                            kayitConf.setBounds(210, 245, 75, 30);
                            kayitPanel.add(kayitConf);
                            JLabel errorLabel = new JLabel();
                            errorLabel.setForeground(Color.RED);
                            kayitPanel.add(errorLabel);
                            errorLabel.setFont(new Font(null, Font.PLAIN, 16));
                            errorLabel.setBounds(102, 285, 300, 50);
                            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            kayitConf.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        Customer customer = new Customer(Double.parseDouble(butceField.getText()), nationalIdField.getText(),
                                                nameField.getText(), telefonNumaraField.getText(), emailField.getText(), musteriCounter[0]++);
                                        crs.addCustomer(customer);
                                        errorLabel.setForeground(new Color(100, 200, 100));
                                        errorLabel.setText("<html>Müşteri Başarıyla Kaydedildi<br>Müşteri Id: " + customer.getCustomerId());
                                        crs.saveCustomers();
                                        try {
                                            FileWriter writer = new FileWriter("counters.txt", false);
                                            writer.write(musteriCounter[0] + "\n" + appointmentCounter[0] + "\n");
                                            writer.close();
                                        } catch (IOException e4) {
                                        }
                                    } catch (Exception e2) {
                                        errorLabel.setForeground(Color.RED);
                                        errorLabel.setText("Hata: " + e2.getMessage());

                                    }
                                }
                            });
                        }
                    });

                }
            });
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame cancelFrame = new JFrame();
                    cancelFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    cancelFrame.setSize(510, 260);
                    cancelFrame.setLocationRelativeTo(null);
                    cancelFrame.setVisible(true);
                    cancelFrame.setResizable(false);
                    JPanel cancelPanel = new JPanel();
                    cancelPanel.setLayout(null);
                    cancelPanel.setBounds(0, 0, 510, 270);
                    cancelFrame.add(cancelPanel);
                    JLabel cancelLabel = new JLabel("Randevu İptal");
                    cancelLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    cancelPanel.add(cancelLabel);
                    cancelLabel.setFont(new Font(null, Font.PLAIN, 30));
                    cancelLabel.setBounds(100, 10, 300, 55);
                    JLabel idGiriniz = new JLabel("İptal Edilecek Randevunun ID'sini Giriniz:");
                    cancelPanel.add(idGiriniz);
                    idGiriniz.setFont(new Font(null, Font.PLAIN, 17));
                    idGiriniz.setBounds(20, 70, 500, 30);
                    JTextField girinizField = new JTextField();
                    girinizField.setFont(new Font(null, Font.PLAIN, 17));
                    girinizField.setBounds(340, 76, 130, 25);
                    cancelPanel.add(girinizField);
                    JButton randSilConf = new JButton("Sil");
                    randSilConf.setFocusable(Boolean.FALSE);
                    randSilConf.setFont(new Font(null, Font.PLAIN, 17));
                    randSilConf.setBounds(210, 130, 100, 30);
                    cancelPanel.add(randSilConf);
                    JLabel silError = new JLabel("");
                    silError.setForeground(Color.RED);
                    silError.setFont(new Font(null, Font.PLAIN, 17));
                    cancelPanel.add(silError);
                    silError.setBounds(0, 170, 510, 30);
                    silError.setHorizontalAlignment(SwingConstants.CENTER);
                    randSilConf.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int silinecekId = Integer.parseInt(girinizField.getText());
                                crs.cancelAppointment(silinecekId);
                                silError.setForeground(new Color(100, 200, 100));
                                silError.setText("Randevu Başarıyla Silindi: " + silinecekId);
                            } catch (IDException e2) {
                                silError.setForeground(Color.RED);
                                silError.setText(e2.getMessage());
                            } catch (NumberFormatException e2) {
                                silError.setForeground(Color.RED);
                                silError.setText("Lütfen Uygun Bir Sayı Giriniz");
                            } catch (Exception e2) {
                                silError.setForeground(Color.RED);
                                silError.setText("Hata: " + e2.getMessage());
                            }
                        }
                    });
                }
            });
            button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame listFrame = new JFrame("Tüm Randevular");
                    listFrame.setSize(700, 600);
                    listFrame.setLocationRelativeTo(null);
                    listFrame.setResizable(false);
                    listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    JTextArea textArea = new JTextArea();
                    textArea.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                    textArea.setEditable(false);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setBounds(20, 20, 640, 500);

                    List<Appointment> appointments = crs.getAppointments();

                    if (appointments.isEmpty()) {
                        textArea.setText("Hiç randevu bulunamadı.");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (Appointment a : appointments) {
                            sb.append(a.toString()).append("\n------------------------------------------------------------------------------------------\n");
                        }
                        textArea.setText(sb.toString());
                    }

                    listFrame.setLayout(null);
                    listFrame.add(scrollPane);
                    listFrame.setVisible(true);
                }
            });

            button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame mustListFrame = new JFrame();
                    mustListFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    mustListFrame.setSize(660, 260);
                    mustListFrame.setLocationRelativeTo(null);
                    mustListFrame.setVisible(true);
                    mustListFrame.setResizable(false);
                    JPanel mustListPanel = new JPanel();
                    mustListPanel.setLayout(null);
                    mustListPanel.setBounds(0, 0, 660, 270);
                    mustListFrame.add(mustListPanel);
                    JLabel mustListLabel = new JLabel("Müşteri Randevu Listele");
                    mustListLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    mustListPanel.add(mustListLabel);
                    mustListLabel.setFont(new Font(null, Font.PLAIN, 30));
                    mustListLabel.setBounds(0, 10, 660, 55);
                    JLabel mustIdGiriniz = new JLabel("Randevuları Listelenecek Müşterinin Müşteri ID'sini Giriniz:");
                    mustListPanel.add(mustIdGiriniz);
                    mustIdGiriniz.setFont(new Font(null, Font.PLAIN, 17));
                    mustIdGiriniz.setBounds(20, 70, 500, 30);
                    JTextField mustGirinizField = new JTextField();
                    mustGirinizField.setFont(new Font(null, Font.PLAIN, 17));
                    mustGirinizField.setBounds(490, 76, 130, 25);
                    mustListPanel.add(mustGirinizField);
                    JButton mustListConf = new JButton("Listele");
                    mustListConf.setFocusable(Boolean.FALSE);
                    mustListConf.setFont(new Font(null, Font.PLAIN, 17));
                    mustListConf.setBounds(280, 130, 100, 30);
                    mustListPanel.add(mustListConf);
                    JLabel mustListError = new JLabel("");
                    mustListError.setForeground(Color.RED);
                    mustListError.setFont(new Font(null, Font.PLAIN, 17));
                    mustListPanel.add(mustListError);
                    mustListError.setBounds(0, 170, 660, 30);
                    mustListError.setHorizontalAlignment(SwingConstants.CENTER);
                    mustListConf.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Customer customerLister = crs.findCustomerById(Integer.parseInt(mustGirinizField.getText()));
                                if (customerLister == null)
                                    throw new IDException("Bu ID'ye sahip müşteri bulunamadı: " + mustGirinizField.getText());
                                JFrame listMustFrame = new JFrame(customerLister.getName() + " İsimli Müşterinin Randevuları");
                                listMustFrame.setSize(700, 600);
                                listMustFrame.setLocationRelativeTo(null);
                                listMustFrame.setResizable(false);
                                listMustFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                                JTextArea textArea = new JTextArea();
                                textArea.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                                textArea.setEditable(false);

                                JScrollPane scrollPane = new JScrollPane(textArea);
                                scrollPane.setBounds(20, 20, 640, 500);

                                List<Appointment> appointments = crs.getAppointmentsByCustomerId(customerLister.getId());

                                if (appointments.isEmpty()) {
                                    textArea.setText("Hiç randevu bulunamadı.");
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    for (Appointment a : appointments) {
                                        sb.append(a.toString()).append("\n------------------------------------------------------------------------------------------\n");
                                    }
                                    textArea.setText(sb.toString());
                                }

                                listMustFrame.setLayout(null);
                                listMustFrame.add(scrollPane);
                                listMustFrame.setVisible(true);
                                mustListFrame.dispose();
                            } catch (IDException e1) {
                                mustListError.setForeground(Color.RED);
                                mustListError.setText(e1.getMessage());
                            } catch (Exception e2) {
                                mustListError.setForeground(Color.RED);
                                mustListError.setText("Hata: " + e2.getMessage());
                            }
                        }
                    });
                }
            });
            button5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame bakiyeFrame = new JFrame("Bakiye Yükle");
                    bakiyeFrame.setSize(750, 400);
                    bakiyeFrame.setLocationRelativeTo(null);
                    bakiyeFrame.setResizable(false);
                    bakiyeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JPanel bakiyePanel = new JPanel();
                    bakiyePanel.setLayout(null);
                    bakiyePanel.setBounds(0, 0, 680, 400);
                    bakiyeFrame.add(bakiyePanel);
                    bakiyeFrame.setVisible(true);
                    JLabel bakiyeLabel = new JLabel("Bakiye Yükle");
                    bakiyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    bakiyePanel.add(bakiyeLabel);
                    bakiyeLabel.setFont(new Font(null, Font.PLAIN, 30));
                    bakiyeLabel.setBounds(0, 30, 680, 55);
                    JLabel yuklenecekMust = new JLabel("Bakiye yüklenecek Müşterinin Müşteri ID'sini giriniz:");
                    yuklenecekMust.setFont(new Font(null, Font.PLAIN, 23));
                    yuklenecekMust.setBounds(20, 90, 600, 30);
                    JTextField yuklenecekMustField = new JTextField();
                    yuklenecekMustField.setFont(new Font(null, Font.PLAIN, 17));
                    yuklenecekMustField.setBounds(570, 94, 130, 25);
                    bakiyePanel.add(yuklenecekMust);
                    bakiyePanel.add(yuklenecekMustField);
                    JLabel mustIsim = new JLabel("İsim: ");
                    mustIsim.setFont(new Font(null, Font.PLAIN, 23));
                    bakiyePanel.add(mustIsim);
                    mustIsim.setBounds(20, 125, 500, 30);
                    JLabel mustBakiye = new JLabel("Bakiye: ");
                    mustBakiye.setFont(new Font(null, Font.PLAIN, 21));
                    bakiyePanel.add(mustBakiye);
                    mustBakiye.setBounds(20, 155, 500, 30);
                    yuklenecekMustField.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            Customer customer = crs.findCustomerById(Integer.parseInt(yuklenecekMustField.getText()));
                            if (customer != null) {
                                mustBakiye.setText("Bakiye:     " + customer.getBalance() + " TL");
                                mustIsim.setText("İsim:       " + customer.getName());
                            } else {
                                mustBakiye.setText("Bakiye:");
                                mustIsim.setText("İsim:");
                            }
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            Customer customer = crs.findCustomerById(Integer.parseInt(yuklenecekMustField.getText()));
                            if (customer != null) {
                                mustBakiye.setText("Bakiye:     " + customer.toString() + " TL");
                                mustIsim.setText("İsim:       " + customer.getName());
                            } else {
                                mustBakiye.setText("Bakiye:");
                                mustIsim.setText("İsim:");
                            }
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            Customer customer = crs.findCustomerById(Integer.parseInt(yuklenecekMustField.getText()));
                            if (customer != null) {
                                mustBakiye.setText("Bakiye:     " + customer.toString() + " TL");
                                mustIsim.setText("İsim:       " + customer.getName());
                            } else {
                                mustBakiye.setText("Bakiye:");
                                mustIsim.setText("İsim:");
                            }
                        }
                    });
                    JLabel yuklenecekMiktar = new JLabel("Yüklenecek miktarı giriniz:");
                    yuklenecekMiktar.setFont(new Font(null, Font.PLAIN, 23));
                    yuklenecekMiktar.setBounds(20, 200, 600, 30);
                    bakiyePanel.add(yuklenecekMiktar);
                    JTextField yuklenecekMiktarField = new JTextField();
                    yuklenecekMiktarField.setBounds(316, 206, 130, 25);
                    yuklenecekMiktarField.setFont(new Font(null, Font.PLAIN, 17));
                    bakiyePanel.add(yuklenecekMiktarField);
                    JLabel tl = new JLabel("TL");
                    tl.setFont(new Font(null, Font.PLAIN, 21));
                    bakiyePanel.add(tl);
                    tl.setBounds(453, 202, 600, 30);
                    JButton bakiyeButton = new JButton("Bakiye Yükle");
                    bakiyeButton.setFont(new Font(null, Font.PLAIN, 18));
                    bakiyeButton.setBounds(300, 255, 150, 40);
                    bakiyePanel.add(bakiyeButton);
                    JLabel bakiyeError = new JLabel("");
                    bakiyeError.setForeground(Color.RED);
                    bakiyeError.setFont(new Font(null, Font.PLAIN, 17));
                    bakiyeError.setBounds(0, 305, 750, 30);
                    bakiyeError.setHorizontalAlignment(SwingConstants.CENTER);
                    bakiyePanel.add(bakiyeError);
                    bakiyeButton.setFocusable(Boolean.FALSE);
                    bakiyeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Customer customerBakiye = crs.findCustomerById(Integer.parseInt(yuklenecekMustField.getText()));
                                if (customerBakiye == null)
                                    throw new IDException("Bu ID'ye sahip müşteri bulunamadı: " + yuklenecekMustField.getText());
                                customerBakiye.setBalance(customerBakiye.getBalance() + Integer.parseInt(yuklenecekMiktarField.getText()));
                                crs.saveCustomers();
                                bakiyeError.setForeground(new Color(100, 200, 100));
                                bakiyeError.setText("Bakiye başarıyla eklendi");
                                mustBakiye.setText("Bakiye:     " + customerBakiye.getBalance() + " TL");
                            } catch (IDException e1) {
                                bakiyeError.setForeground(Color.RED);
                                bakiyeError.setText(e1.getMessage());
                            } catch (Exception e1) {
                                bakiyeError.setForeground(Color.RED);
                                bakiyeError.setText("Hata: " + e1.getMessage());
                            }
                        }
                    });
                }
            });
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame customerListFrame = new JFrame("Tüm Müşteriler");
                customerListFrame.setSize(700, 600);
                customerListFrame.setLocationRelativeTo(null);
                customerListFrame.setResizable(false);
                customerListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setBounds(20, 20, 640, 500);

                List<Customer> customers = crs.getCustomers();

                if (customers.isEmpty()) {
                    textArea.setText("Hiç müşteri bulunamadı.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Customer c : customers) {
                        sb.append("Müşteri ID: ").append(c.getCustomerId()).append("\n")
                                .append("İsim: ").append(c.getName()).append("\n")
                                .append("Telefon: ").append(c.getTelephone()).append("\n")
                                .append("Email: ").append(c.getEmail()).append("\n")
                                .append("Ulusal ID: ").append(c.getNationalid()).append("\n")
                                .append("Bakiye: ").append(c.getBalance()).append(" TL\n")
                                .append("-------------------------------------------------\n");
                    }
                    textArea.setText(sb.toString());
                }

                customerListFrame.setLayout(null);
                customerListFrame.add(scrollPane);
                customerListFrame.setVisible(true);
            }
        });

    }
    }
