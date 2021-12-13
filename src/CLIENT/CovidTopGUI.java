package CLIENT;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xml.PieDatasetHandler;

import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import SERVER.Entity.InformationCovidTop;
import SERVER.Model.CovidTopModel;

public class CovidTopGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static String value;
	static DatagramSocket clientSocket;
	static Scanner sc;
	static byte[] sendData;
	InetAddress address;
	DatagramPacket sendPacket;
	static DatagramPacket receivePacket;
	static byte[] receiveData;
	static ArrayList<CovidTopModel> dataCovid =null;
	static int choose = 0;
	private JTextField txtSearch;

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

// nhận gói tin từ Server	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CovidTopGUI frame = new CovidTopGUI();
					frame.setVisible(true);
					// gọi API lunn
//					getValue();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CovidTopGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1041, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_left = new JPanel();
		panel_left.setBounds(10, 10, 532, 683);
		contentPane.add(panel_left);
		panel_left.setLayout(null);

		JPanel pn_piechart = new JPanel();
		pn_piechart.setBackground(Color.ORANGE);
		pn_piechart.setBounds(0, 259, 532, 424);
		panel_left.add(pn_piechart);

		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 0, 532, 260);
		panel_left.add(panel);
		panel.setLayout(null);

		JLabel lblName = new JLabel("Tên");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
		lblName.setBounds(67, 0, 134, 44);
		panel.add(lblName);

		JLabel lblNewLabel = new JLabel("Tổng số ca");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(85, 65, 103, 26);
		panel.add(lblNewLabel);

		JLabel lblTngSCa = new JLabel("Tổng số ca hồi phục");
		lblTngSCa.setFont(new Font("Arial", Font.BOLD, 14));
		lblTngSCa.setBounds(21, 101, 180, 26);
		panel.add(lblTngSCa);

		JLabel lblSCaT = new JLabel("Tổng số ca tử vong");
		lblSCaT.setFont(new Font("Arial", Font.BOLD, 14));
		lblSCaT.setBounds(31, 137, 140, 26);
		panel.add(lblSCaT);

		JLabel lblSCaHi = new JLabel("Tổng số ca điều trị");
		lblSCaHi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSCaHi.setFont(new Font("Arial", Font.BOLD, 14));
		lblSCaHi.setBounds(31, 173, 140, 26);
		panel.add(lblSCaHi);

		JLabel lblCases = new JLabel("Số liệu");
		lblCases.setFont(new Font("Arial", Font.BOLD, 14));
		lblCases.setBounds(247, 65, 103, 26);
		panel.add(lblCases);

		JLabel lblRecover = new JLabel("Số liệu");
		lblRecover.setFont(new Font("Arial", Font.BOLD, 14));
		lblRecover.setBounds(247, 101, 103, 26);
		panel.add(lblRecover);

		JLabel lblDeaths = new JLabel("Số liệu");
		lblDeaths.setFont(new Font("Arial", Font.BOLD, 14));
		lblDeaths.setBounds(247, 137, 103, 26);
		panel.add(lblDeaths);

		JLabel lblActive = new JLabel("Số liệu");
		lblActive.setFont(new Font("Arial", Font.BOLD, 14));
		lblActive.setBounds(247, 174, 103, 26);
		panel.add(lblActive);

		JLabel lblflag = new JLabel("");
		lblflag.setHorizontalAlignment(SwingConstants.CENTER);
		lblflag.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
		lblflag.setBounds(216, 0, 146, 44);
		panel.add(lblflag);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(552, 10, 475, 608);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 1;
				int row = table.getSelectedRow();
				value = table.getModel().getValueAt(row, column).toString();

				// thông tin covid
				showInforCovid(lblName, lblCases, lblActive, lblDeaths, lblRecover, lblflag);

				// vẽ Pie Chart
				ChartPanel chart = PieChart();
				chart.setBounds(0, 0, 1130, 600);
				pn_piechart.removeAll();
				pn_piechart.add(chart);
				pn_piechart.updateUI();
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Số thứ tự", "Quốc gia", "Tổng số ca" }) {
					Class[] columnTypes = new Class[] { Integer.class, String.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		scrollPane.setViewportView(table);

		JButton btnCases = new JButton("Cases");
		btnCases.setVisible(false);
		btnCases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose = 0;
				InformationCovidTop.sortListByCases(dataCovid);
				String col[] = { "Số thứ tự", "Quốc gia", "Số ca", };
				Object[] row = new Object[3];
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(col);
				table.setModel(model);
				for (int i = 0; i < dataCovid.size(); i++) {
					String country = dataCovid.get(i).getCountry();
					int cases = dataCovid.get(i).getCases();
					row[0] = i + 1;
					row[1] = country;
					row[2] = cases;
					model.addRow(row);
				}
			}
		});

		JButton btnDeaths = new JButton("Deaths");
		btnDeaths.setVisible(false);
		btnDeaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose = 1;
				InformationCovidTop.sortListbyDeaths(dataCovid);
				String col[] = { "Số thứ tự", "Quốc gia", "Số ca tử vong" };
				Object[] row = new Object[3];
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(col);
				table.setModel(model);
				for (int i = 0; i < dataCovid.size(); i++) {
					String country = dataCovid.get(i).getCountry();
					int deaths = dataCovid.get(i).getDeaths();
					row[0] = i + 1;
					row[1] = country;
					row[2] = deaths;
					model.addRow(row);
				}
			}
		});

		btnCases.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCases.setBounds(555, 654, 85, 30);
		contentPane.add(btnCases);
		btnDeaths.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeaths.setBounds(650, 654, 85, 30);
		contentPane.add(btnDeaths);


		JButton btnSearch = new JButton("Search");
		btnSearch.setVisible(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// table đang hiển thị ca tử vong
				if (txtSearch.equals("")) {
					JFrame frame = new JFrame();

					JOptionPane.showMessageDialog(frame, "Ô tìm kiếm rỗng", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (choose == 1) {
						String col[] = { "Số thứ tự", "Quốc gia", "Số ca tử vong" };
						Object[] row = new Object[3];
						DefaultTableModel model = new DefaultTableModel();
						model.setColumnIdentifiers(col);
						table.setModel(model);
						for (int i = 0; i < dataCovid.size(); i++) {
							if (dataCovid.get(i).getCountry().contains(txtSearch.getText())) {
								String country = dataCovid.get(i).getCountry();
								int deaths = dataCovid.get(i).getDeaths();
								row[0] = i + 1;
								row[1] = country;
								row[2] = deaths;
								model.addRow(row);
							}
						}
						if(model.getRowCount() <= 0 ) {
							lblName.setText("");
							lblCases.setText(String.valueOf(""));
							lblActive.setText(String.valueOf(""));
							lblDeaths.setText(String.valueOf(""));
							lblRecover.setText(String.valueOf(""));
							lblflag.setIcon(null);
							
							pn_piechart.removeAll();
							pn_piechart.updateUI();
							JFrame frame = new JFrame();

							JOptionPane.showMessageDialog(frame, "Không tìm thấy dữ liệu", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
					// table đang hiển thị ca nhiễm
					if (choose == 0) {
						String col[] = { "Số thứ tự", "Quốc gia", "Số ca" };
						Object[] row = new Object[3];
						DefaultTableModel model = new DefaultTableModel();
						model.setColumnIdentifiers(col);
						table.setModel(model);
						for (int i = 0; i < dataCovid.size(); i++) {
							if (dataCovid.get(i).getCountry().contains(txtSearch.getText())) {
								String country = dataCovid.get(i).getCountry();
								int deaths = dataCovid.get(i).getCases();
								row[0] = i + 1;
								row[1] = country;
								row[2] = deaths;
								model.addRow(row);
							}
						}
						
						if(model.getRowCount() <= 0 ) {
							lblName.setText("");
							lblCases.setText(String.valueOf(""));
							lblActive.setText(String.valueOf(""));
							lblDeaths.setText(String.valueOf(""));
							lblRecover.setText(String.valueOf(""));
							lblflag.setIcon(null);
							
							pn_piechart.removeAll();
							pn_piechart.updateUI();
							JFrame frame = new JFrame();
							
							JOptionPane.showMessageDialog(frame, "Không tìm thấy dữ liệu", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearch.setBounds(840, 654, 85, 30);
		contentPane.add(btnSearch);

		txtSearch = new JTextField();
		txtSearch.setVisible(false);
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSearch.setBounds(840, 625, 187, 25);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData = new byte[65536];
				receiveData = new byte[65536];
				DatagramPacket receivePacket;
				InetAddress address;
				DatagramPacket sendPacket;

				String sendTmp = "hello";
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

					// Gửi dữ liệu cho server thông báo truy cập vào lần đầu tiên gửi kèm chuỗi
					// send1
					listDataSends.put("send1", listTmps);
					sendData = serialize(listDataSends);
					sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
//		             System.out.println("Client sent " + sendTmp + " to " + address.getHostAddress()
//		                     + " from port " + clientSocket.getLocalPort());
					clientSocket.send(sendPacket);

					// Nhận public key từ server
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);

					listPublicKeys = (HashMap) deserialize(receivePacket.getData());

					// Sinh ra secretKey để gửi lại cho server
					if (listPublicKeys.containsKey("publicKey") && listPublicKeys.size() == 1) {
						secretKey = Encrypt.AESUtils.generateKey();
						PublicKey publicKey = listPublicKeys.get("publicKey");
//		             System.out.println("serec key: " + encrypt.Encrypt.convertSecretKeyToString(secretKey));
						String encodedKey = Encrypt.Convert.convertSecretKeyToString(secretKey);
						System.out.println("public key: " + listPublicKeys.get("publicKey"));
//		                             System.out.println("string: "+ encodedKey);
//		                            System.out.println("secret key: "+ secretKey.getFormat());

						// mã hóa sercetkey dùng public key vừa nhận dược từ server
						byte[] encrypted = Encrypt.RSAUtils.encrypt(publicKey, encodedKey.getBytes());
						List<byte[]> listEncrypt = new ArrayList<>();
						listEncrypt.add(encrypted);
						
						// Khi nào client đã nhận được listSecretKey thì thêm vào map listSecretKeys
						listSecretKeys.put("secretKey", listEncrypt);
						
						// Gửi sercretkey cho server.
						sendData = serialize(listSecretKeys);
						sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
//		             System.out.println("Client sent " + listSecretKeys.get("secretKey") + " to " + address.getHostAddress()
//		                     + " from port " + clientSocket.getLocalPort());
						clientSocket.send(sendPacket);
					}
					// System.out.println("public key: "+ listPublicKeys.get(""));

					// tạo private key aes
					// bắt đầu gửi tin nhán đến client sau khi cả 2 bên đẫ nhận được serect key
					// send message to server
					String tmp = "$topcovid";
					byte[] tm = tmp.getBytes();
					byte[] encryptedMesage = Encrypt.AESUtils.encrypt(secretKey, tm);
					List<byte[]> listMessEnc = new ArrayList<>();
					listMessEnc.add(encryptedMesage);
					listDataSends.put("encMessage", listMessEnc);
					sendData = serialize(listDataSends);

					sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
					clientSocket.send(sendPacket);

//		                 System.out.println("Client sent " + sendData + " to " + address.getHostAddress()
//		                         + " from port " + clientSocket.getLocalPort());

					// receive message from server
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					listDataReceives = (HashMap) deserialize(receivePacket.getData());
					List<CovidTopModel> listNewCovidTop = null;
					
					byte[] decryptMessage = Encrypt.AESUtils.decrypt(secretKey, listDataReceives.get("sendMessage").get(0));
					System.out.println("decrypt message: " + new String(decryptMessage));
					listNewCovidTop = (List<CovidTopModel>) deserialize(decryptMessage);
					System.out.println(listNewCovidTop.isEmpty());
					dataCovid = (ArrayList<CovidTopModel>) listNewCovidTop;
					
					if (dataCovid.isEmpty()) {
						JFrame frame = new JFrame();

						JOptionPane.showMessageDialog(frame, "Load dữ liệu thất bại", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JFrame frame = new JFrame();
						btnCases.setVisible(true);
						btnDeaths.setVisible(true);
						btnSearch.setVisible(true);
						txtSearch.setVisible(true);
						JOptionPane.showMessageDialog(frame, "Load dữ liệu thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setFont(new Font("Arial", Font.BOLD, 14));
		btnLoad.setBounds(745, 654, 85, 30);
		contentPane.add(btnLoad);
	}

	static public ChartPanel PieChart() {
		double act = 0, dea = 0, rec = 0, cases = 0;
		double res_per = 0, act_per = 0, dea_per = 0, rec_per = 0;
		for (int i = 0; i < dataCovid.size(); i++) {
			if (dataCovid.get(i).getCountry().equals(value)) {
				cases = dataCovid.get(i).getCases();
				act = (dataCovid.get(i).getActive() / cases) * 100;
				dea = (dataCovid.get(i).getDeaths() / cases) * 100;
				rec = (dataCovid.get(i).getRecovered() / cases) * 100;
			}
		}
		act_per = ((double) Math.round(act * 100) / 100);
		dea_per = ((double) Math.round(dea * 100) / 100);
		rec_per = ((double) Math.round(rec * 100) / 100);

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Tử vong " + String.valueOf(dea_per), dea_per);
		dataset.setValue("Điều trị " + String.valueOf(act_per), act_per);
		dataset.setValue("Đã khỏi " + String.valueOf(rec_per), rec_per);

		JFreeChart chart = ChartFactory.createPieChart3D("PieChart", dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	// hiển thị lá cờ
	static public ImageIcon showFlag(String link) {
		Image img = null;
		try {
			URL url = new URL(link);
			img = ImageIO.read(url);
		} catch (IOException e1) {
		}

		Image newimg = img.getScaledInstance(80, 60, Image.SCALE_DEFAULT);
		ImageIcon imageIcon = new ImageIcon(newimg);
		return imageIcon;
	}

	static public void showInforCovid(JLabel lblName, JLabel lblCases, JLabel lblActive, JLabel lblDeaths,
			JLabel lblRecover, JLabel lblflag) {
		String country = null, flag = null;
		int cases = 0, act = 0, dea = 0, rec = 0;
		for (int i = 0; i < dataCovid.size(); i++) {
			if (dataCovid.get(i).getCountry().equals(value)) {
				country = dataCovid.get(i).getCountry();
				cases = dataCovid.get(i).getCases();
				act = dataCovid.get(i).getActive();
				dea = dataCovid.get(i).getActive();
				rec = dataCovid.get(i).getRecovered();
				flag = dataCovid.get(i).getFlag();
			}
		}
		lblName.setText(country);
		lblCases.setText(String.valueOf(cases));
		lblActive.setText(String.valueOf(act));
		lblDeaths.setText(String.valueOf(dea));
		lblRecover.setText(String.valueOf(rec));
		lblflag.setIcon(showFlag(flag));
	}
}
