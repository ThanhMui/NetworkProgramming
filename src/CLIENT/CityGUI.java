/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIENT;

import SERVER.Model.City;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class CityGUI extends javax.swing.JFrame {
    static DatagramSocket clientSocket ;
    static Scanner sc ;
     byte[] sendData ;
  InetAddress address;
   DatagramPacket sendPacket ;
    DatagramPacket receivePacket;
    byte[] receiveData;
   public static  List<City> getInfoCityFulls = null;
  
    /**
     * Creates new form CityGUI
     */
    public CityGUI() throws SocketException {
        initComponents();
       
        
    }
public static byte[] serialize(Object obj) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(out);
    os.writeObject(obj);
    return out.toByteArray();
}
public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream(data);
    ObjectInputStream is = new ObjectInputStream(in);
    return is.readObject();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSearch = new javax.swing.JButton();
        txtCityName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCity = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        lbMinTemp = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbClouds = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbTemp = new javax.swing.JLabel();
        lbWind = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbMaxTemp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtCityName.setForeground(new java.awt.Color(153, 153, 153));

        tbCity.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "City name", "Country name", "ID country", "Population", "Longitude", "Latitude", "Province name", "TimeZone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCityMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCity);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Weather Information");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Information City");

        jLabel3.setText("Speed Wind");

        jLabel4.setText("Description Weather");

        lbDescription.setText("jLabel3");

        lbMinTemp.setText("jLabel3");

        jLabel7.setText("Min Temperature");

        jLabel8.setText("Max Temperature");

        lbClouds.setText("jLabel3");

        jLabel10.setText("Temperature");

        lbTemp.setText("jLabel3");

        lbWind.setText("jLabel3");

        jLabel13.setText("Clouds");

        lbMaxTemp.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(txtCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(lbMaxTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbWind, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbMinTemp, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(lbClouds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMinTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(lbTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbMaxTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbClouds, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lbWind, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(124, 124, 124))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
         // clear form
         tbCity.setModel(new DefaultTableModel(null, new String[]{"City name","Country name", "ID country", "Population", "Longitude", 
             "Latitude",  "Province name", "TimeZone"
         }) );
        sendData = new byte[65536];
        receiveData = new byte[65536];
        String tmp = txtCityName.getText().trim()+ "$city";
        if( tmp.equalsIgnoreCase("bye")){
            clientSocket.close();
            System.exit(0);
        }
    try {
        sendData = serialize(tmp.toString());
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
        clientSocket.send(sendPacket);
        // DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
               
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                 clientSocket.receive(receivePacket);
                 getInfoCityFulls  = (ArrayList)deserialize(receivePacket.getData());
              for( City city : getInfoCityFulls){
                  System.out.println("name: "+ city.getName());
                  System.out.println("id"+ city.getIdCountry());
              }
             
              
              DefaultTableModel model = (DefaultTableModel) tbCity.getModel();
               for( City city : getInfoCityFulls){
                  System.out.println("name: "+ city.getName());
                  System.out.println("id"+ city.getIdCountry());
                  model.addRow(new Object[]{ city.getName(), city.getCountry(), city.getIdCountry(),
                      city.getPopulation(), city.getLongitude(), city.getLatitude(), city.getNameProvince(), city.getTimezoneId()
                  });
              }
              
    } catch (IOException ex) {
        Logger.getLogger(CityGUI.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(CityGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbCityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCityMouseClicked
        // TODO add your handling code here:
        int row = tbCity.getSelectedRow();
        String name = tbCity.getValueAt(row, 0).toString();
        for( City city: getInfoCityFulls){
            if( city.getName().equalsIgnoreCase(name)){
                if( city.getClouds()== -6){
                     lbClouds.setText("");
                }else if( city.getTemperature()== -6){
                     lbTemp.setText("");
                }else if(city.getMax_Temperature() == -6){
                    lbMaxTemp.setText("");
                }else if( city.getMin_Temperature() == -6){
                    lbMinTemp.setText("");
                }else  if( city.getSpeedWind() == -6){
                    lbWind.setText("");
                }else if( city.getClouds() == -6){
                    lbClouds.setText("");
                }else{
                    lbDescription.setText(city.getDescriptionWeather());
                lbTemp.setText(String.valueOf(city.getTemperature()+ " °C"));
                lbMinTemp.setText(String.valueOf(city.getMin_Temperature()+ " °C"));
                lbMaxTemp.setText(String.valueOf(city.getMax_Temperature()+ " °C"));
                lbWind.setText(String.valueOf(city.getSpeedWind() + " m/s"));
                lbClouds.setText(String.valueOf(city.getClouds()+ " %"));
                }
                break;
            }
        }
    }//GEN-LAST:event_tbCityMouseClicked
// public void startRunning()
//    {
//        try
//        {
//            server=new ServerSocket(port, totalClients);
//              
//                try
//                {
//                    status.setText(" Waiting for Someone to Connect...");
//                    connection=server.accept();
//                    status.setText(" Now Connected to "+connection.getInetAddress().getHostName());
//
//
//                    output = new ObjectOutputStream(connection.getOutputStream());
//                    output.flush();
//                    input = new ObjectInputStream(connection.getInputStream());
//                    System.out.println("Trong lôp");
//                    whileChatting();
//                }catch(EOFException eofException)
//                {
//                }
//        }
//        catch(IOException ioException)
//        {
//                ioException.printStackTrace();
//        }
//    }
//    
//   private void whileChatting() throws IOException
//   {
//        String message="";    
//        jTextField1.setEditable(true);
//        do{
//                try
//                {
//                        message = (String) input.readObject();
//                        chatArea.append("\n"+message);
//                }catch(ClassNotFoundException classNotFoundException)
//                {
//                        
//                }
//        }while(!message.equals("Client - END"));
//   }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SocketException, ClassNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CityGUI().setVisible(true);
                } catch (SocketException ex) {
                    Logger.getLogger(CityGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        clientSocket = new DatagramSocket();
        sc = new Scanner(System.in);
       
    Scanner sc = new Scanner(System.in);
    try {
        DatagramSocket clientSocket = new DatagramSocket();
        byte[] sendData = new byte[65536];
        byte[] receiveData = new byte[65536];
        while (true) {
            //                ArrayList<product> fromUser = new ArrayList<product>();
//                fromUser.add(new product("mui8", "23456"));
//                fromUser.add(new product("mui9", "2345"));
//                sendData = serialize(fromUser);
//        ArrayList<product> fromUser = new ArrayList<product>();
//                fromUser.add(new product("mui8", "23456"));
//                fromUser.add(new product("mui9", "2345"));
                    System.out.println("Nhap vao: ");
                    String tmp = sc.nextLine();
                    if( tmp.equals("bye")){
                        clientSocket.close();
                        break;
                    }
//            sendData = serialize(tmp.toString());
//            InetAddress address = InetAddress.getByName("localhost");
//            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
//            clientSocket.send(sendPacket);
//                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//                clientSocket.receive(receivePacket);
//                ArrayList<product> object = (ArrayList)deserialize(receivePacket.getData());
//              for( product p : object){
//                  System.out.println("name: "+ p.getName());
//              }
        }
    }catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
        // TODO Auto-generated catch block
        
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbClouds;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbMaxTemp;
    private javax.swing.JLabel lbMinTemp;
    private javax.swing.JLabel lbTemp;
    private javax.swing.JLabel lbWind;
    private javax.swing.JTable tbCity;
    private javax.swing.JTextField txtCityName;
    // End of variables declaration//GEN-END:variables
}
