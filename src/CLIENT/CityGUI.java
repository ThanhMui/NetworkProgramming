/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIENT;

import static CLIENT.CountryGUI.clientSocket;
import SERVER.Model.City;
import java.awt.Font;
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
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class CityGUI extends javax.swing.JFrame {

	public static List<City> getInfoCityFulls = null;
	public static String hostname = "localhost";
	static DatagramSocket clientSocket;
	static Scanner sc;
	public static byte[] sendData;
	InetAddress address;
	public static byte[] receiveData;

	/**
	 * Creates new form CityGUI
	 */
	public CityGUI() throws SocketException {
		initComponents();
		tbCity.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
		tbCity.isEditing();
		tbCity.setDefaultEditor(Object.class, null);

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
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
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
		lbNotification1 = new javax.swing.JLabel();
		lbNotification2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(153, 153, 255));
		setResizable(false);

		btnSearch.setBackground(new java.awt.Color(0, 102, 153));
		btnSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		btnSearch.setText("Search");
		btnSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSearchActionPerformed(evt);
			}
		});

		txtCityName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		tbCity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		tbCity.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "City name", "Country name", "ID country", "Population", "Longitude", "Latitude",
				"Province name", "TimeZone" }) {
			Class[] types = new Class[] { java.lang.String.class, java.lang.String.class, java.lang.String.class,
					java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class,
					java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tbCity.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tbCityMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tbCity);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		jLabel1.setText("Weather Information");

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		jLabel2.setText("Information City");

		jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel3.setText("Speed Wind");

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel4.setText("Description Weather");

		lbDescription.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		lbMinTemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel7.setText("Min Temperature");

		jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel8.setText("Max Temperature");

		lbClouds.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel10.setText("Temperature");

		lbTemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		lbWind.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel13.setText("Clouds");

		lbMaxTemp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

		lbNotification1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		lbNotification1.setForeground(new java.awt.Color(204, 0, 51));

		lbNotification2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		lbNotification2.setForeground(new java.awt.Color(204, 0, 51));

		jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		jButton1.setForeground(new java.awt.Color(204, 0, 0));
		jButton1.setText("BACK");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(61, 61, 61)
												.addComponent(lbMaxTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 184,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 172,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(39, 39, 39)
												.addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE,
														200, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(94, 94, 94)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lbTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 126,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lbWind, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(49, 49, 49)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 164,
												Short.MAX_VALUE)
										.addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(lbMinTemp, javax.swing.GroupLayout.DEFAULT_SIZE, 150,
												Short.MAX_VALUE)
										.addComponent(lbClouds, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(24, 24, 24))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(34, 34, 34)
								.addComponent(lbNotification2, javax.swing.GroupLayout.PREFERRED_SIZE, 625,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(62, 62, 62)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 219,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbNotification1, javax.swing.GroupLayout.PREFERRED_SIZE, 643,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 508,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(55, 55, 55)
										.addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(25, 25, 25)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtCityName, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
						.addComponent(lbNotification1, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(26, 26, 26)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(39, 39, 39)
										.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(41, 41, 41))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbNotification2, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lbMinTemp, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 47,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
												.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 2, Short.MAX_VALUE))
								.addComponent(lbTemp, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
								.createSequentialGroup().addGap(69, 69, 69)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lbMaxTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lbClouds, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addGap(61, 61, 61).addComponent(lbWind,
												javax.swing.GroupLayout.PREFERRED_SIZE, 54,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(124, 124, 124))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSearchActionPerformed
		// TODO add your handling code here:
		// clear form
		tbCity.setModel(new DefaultTableModel(null, new String[] { "City name", "Country name", "ID country",
				"Population", "Longitude", "Latitude", "Province name", "TimeZone" }));

		Scanner stdIn = new Scanner(System.in);
		DatagramPacket receivePacket;
		InetAddress address;
		DatagramPacket sendPacket;
		String sendTmp = "hello";
		sendData = new byte[6553];
		receiveData = new byte[6553];
		SecretKey secretKey = null;
		try {
			clientSocket = new DatagramSocket();
			address = InetAddress.getByName("localhost");
			Map<String, List<byte[]>> listDataSends = new HashMap<>();
			Map<String, List<byte[]>> listDataReceives = new HashMap<>();
			Map<String, PublicKey> listPublicKeys = new HashMap<>();
			Map<String, List<byte[]>> listSecretKeys = new HashMap<>();

			List<byte[]> listTmps = new ArrayList<>();
			listTmps.add(sendTmp.getBytes());
			listDataSends.put("send1", listTmps);
			sendData = serialize(listDataSends);
			sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
//            System.out.println("Client sent " + sendTmp + " to " + address.getHostAddress()
//                    + " from port " + clientSocket.getLocalPort());
			clientSocket.send(sendPacket);
			// DatagramPacket receivePacket = new DatagramPacket(receiveData,
			// receiveData.length);
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			listPublicKeys = (HashMap) deserialize(receivePacket.getData());

			if (listPublicKeys.containsKey("publicKey") && listPublicKeys.size() == 1) {
				secretKey = Encrypt.AESUtils.generateKey();
				PublicKey publicKey = listPublicKeys.get("publicKey");
//            System.out.println("serec key: " + encrypt.Encrypt.convertSecretKeyToString(secretKey));
				String encodedKey = Encrypt.Convert.convertSecretKeyToString(secretKey);
				System.out.println("public key: " + listPublicKeys.get("publicKey"));
//                            System.out.println("string: "+ encodedKey);
//                           System.out.println("secret key: "+ secretKey.getFormat());
				// emã hóa private key dùng public key vừa nhận dược từ server

				byte[] encrypted = Encrypt.RSAUtils.encrypt(publicKey, encodedKey.getBytes());
				List<byte[]> listEncrypt = new ArrayList<>();
				listEncrypt.add(encrypted);
				listSecretKeys.put("secretKey", listEncrypt);
				sendData = serialize(listSecretKeys);
				sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
//            System.out.println("Client sent " + listSecretKeys.get("secretKey") + " to " + address.getHostAddress()
//                    + " from port " + clientSocket.getLocalPort());
				clientSocket.send(sendPacket);
			}

			String tmpMessage = txtCityName.getText().trim() + "$city";
			byte[] tm = tmpMessage.getBytes();
			byte[] encryptedMesage = Encrypt.AESUtils.encrypt(secretKey, tm);
			List<byte[]> listMessEnc = new ArrayList<>();
			listMessEnc.add(encryptedMesage);
			listDataSends.put("encMessage", listMessEnc);
			sendData = serialize(listDataSends);

			sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);

//                System.out.println("Client sent " + sendData + " to " + address.getHostAddress()
//                        + " from port " + clientSocket.getLocalPort());
			clientSocket.send(sendPacket);
			// receive message from server
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			listDataReceives = (HashMap) deserialize(receivePacket.getData());
			// getInfoCityFulls = (ArrayList)deserialize(receivePacket.getData());
			List<String> listCity = new ArrayList<>();
			if (listDataReceives.containsKey("sendMessage") && listDataReceives.size() > 0) {
				for (byte[] message : listDataReceives.get("sendMessage")) {
					System.out.println("message : " + message);
					System.out.println("secretKey : " + String.valueOf(secretKey));
					byte[] decryptMessage = Encrypt.AESUtils.decrypt(secretKey, message);
					System.out.println("decrypt message: " + new String(decryptMessage));
					listCity.add(new String(decryptMessage));
				}
			}
			if (listCity.size() > 0) {
				lbNotification1.setText("");
				lbNotification2.setText("");
				SERVER.Model.City city = new SERVER.Model.City();
				city.setName(listCity.get(0));
				city.setClouds(Float.parseFloat(listCity.get(1)));
				city.setCountry(listCity.get(2));
				city.setDescriptionWeather(listCity.get(3));
				city.setIdCountry(listCity.get(4));
				city.setIdProvince(listCity.get(5));
				city.setLatitude(Float.parseFloat(listCity.get(6)));
				city.setLongitude(Float.parseFloat(listCity.get(7)));
				city.setMax_Temperature(Float.parseFloat(listCity.get(8)));
				city.setMin_Temperature(Float.parseFloat(listCity.get(9)));
				city.setTemperature(Float.parseFloat(listCity.get(10)));
				city.setNameProvince(listCity.get(11));
				city.setPopulation(Integer.parseInt(listCity.get(12)));
				city.setSpeedWind(Float.parseFloat(listCity.get(13)));
				city.setTimezoneId(listCity.get(14));

				getInfoCityFulls = new ArrayList<>();
				getInfoCityFulls.add(new City(city.getName(), city.getLatitude(), city.getLongitude(),
						city.getIdCountry(), city.getCountry(), city.getPopulation(), city.getTimezoneId(),
						city.getIdProvince(), city.getNameProvince(), city.getDescriptionWeather(),
						city.getTemperature(), city.getMin_Temperature(), city.getMax_Temperature(),
						city.getSpeedWind(), city.getClouds()));
				DefaultTableModel model = (DefaultTableModel) tbCity.getModel();
				for (City cit : getInfoCityFulls) {
					System.out.println("name: " + cit.getName());
					System.out.println("id" + cit.getIdCountry());
					model.addRow(new Object[] { city.getName(), city.getCountry(), city.getIdCountry(),
							city.getPopulation(), city.getLongitude(), city.getLatitude(), city.getNameProvince(),
							city.getTimezoneId() });
				}
			} else {
				lbNotification1.setText("No search city information available " + getNameCitys(tmpMessage).get(0));
				lbNotification2.setText("No weather information of city " + getNameCitys(tmpMessage).get(0));
				lbDescription.setText("");
				lbMaxTemp.setText("");
				lbMinTemp.setText("");
				lbTemp.setText("");
				lbWind.setText("");
				lbClouds.setText("");
			}
		} catch (SocketException ex) {
			Logger.getLogger(CLIENT.CityGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(CLIENT.CityGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CLIENT.CityGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CityGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(CityGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		clientSocket.close();
	}// GEN-LAST:event_btnSearchActionPerformed

	private void tbCityMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tbCityMouseClicked
		// TODO add your handling code here:
		int row = tbCity.getSelectedRow();
		// String id = tbCountry.getValueAt(row, 1).toString();
		String name = tbCity.getValueAt(row, 0).toString();
		for (City city : getInfoCityFulls) {
			if (city.getName().equalsIgnoreCase(name)) {
				if (city.getClouds() == -6 && city.getTemperature() == -6 && city.getMax_Temperature() == -6
						&& city.getMin_Temperature() == -6 && city.getSpeedWind() == -6
						&& city.getDescriptionWeather().equals("")) {
					lbNotification2.setText("No search city information available " + city.getName());
					lbDescription.setText("");
					lbTemp.setText("");
					lbMinTemp.setText("");
					lbMaxTemp.setText("");
					lbWind.setText("");
					lbClouds.setText("");
				} else {
					if (city.getClouds() == -6) {
						lbClouds.setText("");
					} else if (city.getTemperature() == -6) {
						lbTemp.setText("");
					} else if (city.getMax_Temperature() == -6) {
						lbMaxTemp.setText("");
					} else if (city.getMin_Temperature() == -6) {
						lbMinTemp.setText("");
					} else if (city.getSpeedWind() == -6) {
						lbWind.setText("");
					} else if (city.getDescriptionWeather().equals("")) {
						lbDescription.setText("");
					} else {
						lbDescription.setText(city.getDescriptionWeather());
						lbTemp.setText(String.valueOf(city.getTemperature() + " °C"));
						lbMinTemp.setText(String.valueOf(city.getMin_Temperature() + " °C"));
						lbMaxTemp.setText(String.valueOf(city.getMax_Temperature() + " °C"));
						lbWind.setText(String.valueOf(city.getSpeedWind() + " m/s"));
						lbClouds.setText(String.valueOf(city.getClouds() + " %"));
					}
					break;
				}
			}
		}
	}// GEN-LAST:event_tbCityMouseClicked

	private static List<String> getNameCitys(String url) {
		StringTokenizer st = new StringTokenizer(url, "$");
		List<String> arr = new ArrayList<>();
		while (st.hasMoreTokens()) {
			// System.out.println(""+ st.nextToken());
			arr.add(st.nextToken());
		}
		return arr;
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		// ẩn cái form hiện tại
		this.setVisible(false);
		// mở form mới
		MainGUI main;
		main = new MainGUI();
		main.setVisible(true);
	}// GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) throws SocketException, ClassNotFoundException {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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
		// </editor-fold>

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
				// ArrayList<product> fromUser = new ArrayList<product>();
//                fromUser.add(new product("mui8", "23456"));
//                fromUser.add(new product("mui9", "2345"));
//                sendData = serialize(fromUser);
//        ArrayList<product> fromUser = new ArrayList<product>();
//                fromUser.add(new product("mui8", "23456"));
//                fromUser.add(new product("mui9", "2345"));
				System.out.println("Nhap vao: ");
				String tmp = sc.nextLine();
				if (tmp.equals("bye")) {
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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated catch block

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnSearch;
	private javax.swing.JButton jButton1;
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
	private javax.swing.JLabel lbNotification1;
	private javax.swing.JLabel lbNotification2;
	private javax.swing.JLabel lbTemp;
	private javax.swing.JLabel lbWind;
	private javax.swing.JTable tbCity;
	private javax.swing.JTextField txtCityName;
	// End of variables declaration//GEN-END:variables
}
