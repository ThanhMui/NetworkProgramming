package CLIENT;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
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
import java.awt.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import SERVER.Model.City;
import SERVER.Model.CovidInfoModel;
import SERVER.Entity.InfomationCovid;

//import CovidChartDemo.InfomationCovid;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CovidChartGUI extends JFrame {

	private JPanel contentPane;
	private JTable tb_Cases;
	private JTable tb_Deaths;
	private JTable tb_Recoverd;
	private JTextField txtCountry;
	private JTextField txtDateStart;
	private JTextField textDateEnd;
	private JOptionPane pane;

	ArrayList<CovidInfoModel> listCovid = null;

	static DatagramSocket clientSocket;
	static Scanner sc;
	static byte[] sendData;
	InetAddress address;
	DatagramPacket sendPacket;
	DatagramPacket receivePacket;
	static byte[] receiveData;

	// hàm chuyển object sang byte
	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(); // mảng byte
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	// Hàm chuyển byte sang object
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CovidChartGUI frame = new CovidChartGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CovidChartGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 880);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane Panel_Cases = new JScrollPane();
		Panel_Cases.setBounds(10, 43, 488, 230);
		contentPane.add(Panel_Cases);

		JScrollPane Panel_Deaths = new JScrollPane();
		Panel_Deaths.setBounds(10, 313, 488, 230);
		contentPane.add(Panel_Deaths);

		JScrollPane Panel_Recoverd = new JScrollPane();
		Panel_Recoverd.setBounds(10, 581, 488, 230);
		contentPane.add(Panel_Recoverd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(574, 43, 367, 230);
		contentPane.add(scrollPane);

		txtCountry = new JTextField();
		txtCountry.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCountry.setBounds(975, 181, 116, 27);
		contentPane.add(txtCountry);
		txtCountry.setColumns(10);

		JLabel lblCountry = new JLabel("Tên quốc gia");
		lblCountry.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCountry.setBounds(975, 140, 96, 31);
		contentPane.add(lblCountry);

		JLabel lblDateStart = new JLabel("Ngày bắt đầu");
		lblDateStart.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDateStart.setBounds(975, 234, 96, 31);
		contentPane.add(lblDateStart);

		txtDateStart = new JTextField();
		txtDateStart.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDateStart.setColumns(10);
		txtDateStart.setBounds(975, 275, 116, 27);
		contentPane.add(txtDateStart);

		JLabel lblNgyKtThc = new JLabel("Ngày kết thúc");
		lblNgyKtThc.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNgyKtThc.setBounds(975, 327, 96, 31);
		contentPane.add(lblNgyKtThc);

		textDateEnd = new JTextField();
		textDateEnd.setFont(new Font("Arial", Font.PLAIN, 14));
		textDateEnd.setColumns(10);
		textDateEnd.setBounds(975, 368, 116, 27);
		contentPane.add(textDateEnd);
		tb_Cases = new JTable();
		tb_Cases.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Ngày", "Số ca" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tb_Cases);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(574, 313, 367, 230);
		contentPane.add(scrollPane_1);

		tb_Deaths = new JTable();
		tb_Deaths.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Ngày", "Số ca tử vong" }));
		scrollPane_1.setViewportView(tb_Deaths);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(574, 581, 367, 230);
		contentPane.add(scrollPane_2);

		tb_Recoverd = new JTable();
		tb_Recoverd.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Ngày", "Số ca hồi phục" }));
		scrollPane_2.setViewportView(tb_Recoverd);

		// Hàm xử lý khi tìm theo biểu đồ đường
		JButton btnLineChart = new JButton("LineChart");
		btnLineChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData = new byte[65536];
				receiveData = new byte[65536];
				if ((txtCountry.getText().equals("")) || (txtDateStart.getText().equals(""))
						|| (textDateEnd.getText().equals(""))) {
					JFrame frame = new JFrame();

					JOptionPane.showMessageDialog(frame, "Thiếu dữ liệu nhập vào", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// tạo chuỗi để gửi sang server
					String tmp = txtCountry.getText().trim() + "," + txtDateStart.getText().trim() + ","
							+ textDateEnd.getText().trim() + "$covid";

					try {
					//////////////////////////////////////////////////
					//////////////////////////////////////////////////
						listCovid = (ArrayList<CovidInfoModel>) maHoa(tmp);
						// số ca

						if (listCovid.size() <= 0) {
							JFrame frame = new JFrame();

							JOptionPane.showMessageDialog(frame, "Không có dữ liệu", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							ChartPanel chart = LineChartCases("Số ca", listCovid);
							chart.setBounds(0, 0, 480, 230);
							Panel_Cases.removeAll();
							Panel_Cases.add(chart);
							Panel_Cases.updateUI();
							// thêm dữ liệu vào table

							ChartPanel chart2 = LineChartDeaths("Số ca tử vong", listCovid);
							chart2.setBounds(0, 0, 480, 230);
							Panel_Deaths.removeAll();
							Panel_Deaths.add(chart2);
							Panel_Deaths.updateUI();

							ChartPanel chart3 = LineChartRecover("Số ca hồi phục", listCovid);
							chart3.setBounds(0, 0, 480, 230);
							Panel_Recoverd.removeAll();
							Panel_Recoverd.add(chart3);
							Panel_Recoverd.updateUI();

							// thêm dữ liệu vào bảng
							DefaultTableModel model = new DefaultTableModel();
							DefaultTableModel model2 = new DefaultTableModel();
							DefaultTableModel model3 = new DefaultTableModel();

							String col[] = { "Ngày", "Số ca" };
							String col2[] = { "Ngày", "Số ca tử vong" };
							String col3[] = { "Ngày", "Số ca hồi phục" };

							model.setColumnIdentifiers(col);
							model2.setColumnIdentifiers(col2);
							model3.setColumnIdentifiers(col3);
							Object[] row = new Object[2];
							Object[] row2 = new Object[2];
							Object[] row3 = new Object[2];
							tb_Cases.setModel(model);
							tb_Deaths.setModel(model2);
							tb_Recoverd.setModel(model3);

							for (int i = 0; i < listCovid.size(); i++) {
								int cases = listCovid.get(i).getConfirmed();
								int deaths = listCovid.get(i).getDeaths();
								int recoverd = listCovid.get(i).getRecovered();

								row[0] = row2[0] = row3[0] = StringUtils.substringBefore(listCovid.get(i).getDate(),
										"T00:00:00.000Z");
								row[1] = cases;
								row2[1] = deaths;
								row3[1] = recoverd;

								model.addRow(row);
								model2.addRow(row2);
								model3.addRow(row3);
							}
						}
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnLineChart.setFont(new Font("Arial", Font.BOLD, 14));
		btnLineChart.setBounds(975, 43, 116, 29);
		contentPane.add(btnLineChart);

		// Hàm xử lí biểu đồ cột
		JButton btnBarChart = new JButton("BarChart");
		btnBarChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData = new byte[65536];
				receiveData = new byte[65536];
				if ((txtCountry.getText().equals("")) || (txtDateStart.getText().equals(""))
						|| (textDateEnd.getText().equals(""))) {
					JFrame frame = new JFrame();

					JOptionPane.showMessageDialog(frame, "Thiếu dữ liệu nhập vào", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String tmp = txtCountry.getText().trim() + "," + txtDateStart.getText().trim() + ","
							+ textDateEnd.getText().trim() + "$covid";

					try {
						//////////////////////////////////////////////////
						//////////////////////////////////////////////////
						listCovid = (ArrayList<CovidInfoModel>) maHoa(tmp);
						if (listCovid.size() <= 0) {
							JFrame frame = new JFrame();

							JOptionPane.showMessageDialog(frame, "Không có dữ liệu", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
						} else {

							// số ca
							ChartPanel chart = BarChartCases("Số ca", listCovid);
							chart.setBounds(0, 0, 480, 230);
							Panel_Cases.removeAll();
							Panel_Cases.add(chart);
							Panel_Cases.updateUI();
							// thêm dữ liệu vào table

							ChartPanel chart2 = BarChartDeaths("Số ca tử vong", listCovid);
							chart2.setBounds(0, 0, 480, 230);
							Panel_Deaths.removeAll();
							Panel_Deaths.add(chart2);
							Panel_Deaths.updateUI();

							ChartPanel chart3 = BarChartRecover("Số ca hồi phục", listCovid);
							chart3.setBounds(0, 0, 480, 230);
							Panel_Recoverd.removeAll();
							Panel_Recoverd.add(chart3);
							Panel_Recoverd.updateUI();

							// thêm dữ liệu vào bảng
							DefaultTableModel model = new DefaultTableModel();
							DefaultTableModel model2 = new DefaultTableModel();
							DefaultTableModel model3 = new DefaultTableModel();

							String col[] = { "Ngày", "Số ca" };
							String col2[] = { "Ngày", "Số ca tử vong" };
							String col3[] = { "Ngày", "Số ca hồi phục" };

							model.setColumnIdentifiers(col);
							model2.setColumnIdentifiers(col2);
							model3.setColumnIdentifiers(col3);
							Object[] row = new Object[2];
							Object[] row2 = new Object[2];
							Object[] row3 = new Object[2];
							tb_Cases.setModel(model);
							tb_Deaths.setModel(model2);
							tb_Recoverd.setModel(model3);

							for (int i = 0; i < listCovid.size(); i++) {
								int cases = listCovid.get(i).getConfirmed();
								int deaths = listCovid.get(i).getDeaths();
								int recoverd = listCovid.get(i).getRecovered();

								row[0] = row2[0] = row3[0] = StringUtils.substringBefore(listCovid.get(i).getDate(),
										"T00:00:00.000Z");
								row[1] = cases;
								row2[1] = deaths;
								row3[1] = recoverd;

								model.addRow(row);
								model2.addRow(row2);
								model3.addRow(row3);
							}
						}
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnBarChart.setFont(new Font("Arial", Font.BOLD, 14));
		btnBarChart.setBounds(1114, 43, 116, 29);
		contentPane.add(btnBarChart);

		JButton btnChiTiet = new JButton("Chi tiết");
		btnChiTiet.setFont(new Font("Arial", Font.BOLD, 14));
		btnChiTiet.setBounds(975, 98, 116, 32);
		contentPane.add(btnChiTiet);

	}

// Biểu đồ đường số ca nhiễm
	static public ChartPanel LineChartCases(String title, ArrayList<CovidInfoModel> listCovid)
			throws IOException, InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listCovid.size(); i++) {
			String d = StringUtils.substringBefore(listCovid.get(i).getDate(), "T00:00:00.000Z");
			dataset.addValue(listCovid.get(i).getConfirmed(), title, d);
		}
		JFreeChart chart = ChartFactory.createLineChart(title, // Chart title
				"Ngày", // X-Axis Label
				"Người", // Y-Axis Label
				dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

// Biểu đồ đường số ca tử vong	
	static public ChartPanel LineChartDeaths(String title, ArrayList<CovidInfoModel> listCovid)
			throws IOException, InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listCovid.size(); i++) {
			String d = StringUtils.substringBefore(listCovid.get(i).getDate(), "T00:00:00.000Z");
			dataset.addValue(listCovid.get(i).getDeaths(), title, d);
		}
		JFreeChart chart = ChartFactory.createLineChart(title, // Chart title
				"Ngày", // X-Axis Label
				"Người", // Y-Axis Label
				dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

// Biểu đồ đường số ca hồi phục	
	static public ChartPanel LineChartRecover(String title, ArrayList<CovidInfoModel> listCovid)
			throws IOException, InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listCovid.size(); i++) {
			String d = StringUtils.substringBefore(listCovid.get(i).getDate(), "T00:00:00.000Z");
			dataset.addValue(listCovid.get(i).getRecovered(), title, d);
		}
		JFreeChart chart = ChartFactory.createLineChart(title, // Chart title
				"Ngày", // X-Axis Label
				"Người", // Y-Axis Label
				dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

// Biểu đồ cột số ca nhiễm	
	static public ChartPanel BarChartCases(String title, ArrayList<CovidInfoModel> listCovid)
			throws IOException, InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listCovid.size(); i++) {
			String d = StringUtils.substringBefore(listCovid.get(i).getDate(), "T00:00:00.000Z");
			dataset.addValue(listCovid.get(i).getConfirmed(), title, d);
		}
		JFreeChart chart = ChartFactory.createBarChart(title, // Chart title
				"Ngày", // X-Axis Label
				"Người", // Y-Axis Label
				dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

// Biểu đồ cột số ca tử vong
	static public ChartPanel BarChartDeaths(String title, ArrayList<CovidInfoModel> listCovid)
			throws IOException, InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listCovid.size(); i++) {
			String d = StringUtils.substringBefore(listCovid.get(i).getDate(), "T00:00:00.000Z");
			dataset.addValue(listCovid.get(i).getDeaths(), title, d);
		}
		JFreeChart chart = ChartFactory.createBarChart(title, // Chart title
				"Ngày", // X-Axis Label
				"Người", // Y-Axis Label
				dataset);
		chart.getPlot().setBackgroundPaint(Color.YELLOW);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

// Biểu đồ cột số ca hồi phục
	static public ChartPanel BarChartRecover(String title, ArrayList<CovidInfoModel> listCovid)
			throws IOException, InterruptedException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < listCovid.size(); i++) {
			String d = StringUtils.substringBefore(listCovid.get(i).getDate(), "T00:00:00.000Z");
			dataset.addValue(listCovid.get(i).getRecovered(), title, d);
		}
		JFreeChart chart = ChartFactory.createBarChart(title, // Chart title
				"Ngày", // X-Axis Label
				"Người", // Y-Axis Label
				dataset);
		chart.getPlot().setBackgroundPaint(Color.BLUE);

		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	public static List<CovidInfoModel> maHoa(String tmp) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
		DatagramPacket receivePacket;
        InetAddress address;
        DatagramPacket sendPacket;
        String sendTmp = "hello";
        sendData = new byte[6553];
        receiveData = new byte[6553];
        SecretKey secretKey = null;
        clientSocket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
         Map<String, List<byte[]>> listDataSends = new HashMap<>();
         Map<String, List<byte[]>> listDataReceives = new HashMap<>();
         Map<String, PublicKey> listPublicKeys = new HashMap<>();
         Map<String,List<byte[]>> listSecretKeys = new HashMap<>();
        
        List<byte[]> listTmps = new ArrayList<>();
        listTmps.add(sendTmp.getBytes());
        listDataSends.put("send1",listTmps);
        sendData = serialize(listDataSends);
        sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
//        System.out.println("Client sent " + sendTmp + " to " + address.getHostAddress()
//                + " from port " + clientSocket.getLocalPort());
        clientSocket.send(sendPacket);
        // DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        listPublicKeys =(HashMap) deserialize(receivePacket.getData());
        
        if( listPublicKeys.containsKey("publicKey") && listPublicKeys.size()== 1){
             secretKey = Encrypt.AESUtils.generateKey();
             PublicKey publicKey = listPublicKeys.get("publicKey");
//        System.out.println("serec key: " + encrypt.Encrypt.convertSecretKeyToString(secretKey));
        String encodedKey = Encrypt.Convert.convertSecretKeyToString(secretKey);
        System.out.println("public key: "+ listPublicKeys.get("publicKey"));
//                        System.out.println("string: "+ encodedKey);
//                       System.out.println("secret key: "+ secretKey.getFormat());
        // emã hóa private key dùng public key vừa nhận dược từ server
        
        byte[] encrypted = Encrypt.RSAUtils.encrypt(publicKey, encodedKey.getBytes());
        List<byte[]> listEncrypt = new ArrayList<>();
        listEncrypt.add(encrypted);
        listSecretKeys.put("secretKey",listEncrypt );
        sendData = serialize(listSecretKeys);
        sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
//        System.out.println("Client sent " + listSecretKeys.get("secretKey") + " to " + address.getHostAddress()
//                + " from port " + clientSocket.getLocalPort());
        clientSocket.send(sendPacket);
        }
     
            //////////String tmp hereeeee////////////
            byte[]tm= tmp.getBytes();
             byte[] encryptedMesage = Encrypt.AESUtils.encrypt(secretKey, tm);
             List<byte[]> listMessEnc =new ArrayList<>();
             listMessEnc.add(encryptedMesage);
             listDataSends.put("encMessage", listMessEnc);
            sendData = serialize(listDataSends);
             
            sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
            
//            System.out.println("Client sent " + sendData + " to " + address.getHostAddress()
//                    + " from port " + clientSocket.getLocalPort());
            clientSocket.send(sendPacket);
        // receive message from server
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        listDataReceives =(HashMap)deserialize(receivePacket.getData());
        // getInfoCityFulls  = (ArrayList)deserialize(receivePacket.getData());
         List<CovidInfoModel> listnew = null;
        if( listDataReceives.containsKey("sendMessage") && listDataReceives.size()> 0){
        	
            for( byte[] message: listDataReceives.get("sendMessage")){
                System.out.println("message : "+ message);
                System.out.println("secretKey : "+String.valueOf(secretKey));
                byte[] decryptMessage = Encrypt.AESUtils.decrypt(secretKey, message);
                System.out.println("decrypt message: " + new String(decryptMessage));
                listnew = (List<CovidInfoModel>) deserialize(decryptMessage);
            }
        }
        return listnew;
	}
}
