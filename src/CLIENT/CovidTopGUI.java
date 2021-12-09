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
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
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
	static ArrayList<CovidTopModel> dataCovid;

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
	public static void getValue() {
		sendData = new byte[65536];
		receiveData = new byte[65536];
		String tmp = "$topcovid";
		
		try {
			clientSocket = new DatagramSocket();
			if (tmp.equalsIgnoreCase("bye")) {
				clientSocket.close();
				System.exit(0);
			}
			
			sendData = serialize(tmp.toString());
			InetAddress address = InetAddress.getByName("localhost");
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
			clientSocket.send(sendPacket);
			
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			dataCovid = (ArrayList) deserialize(receivePacket.getData());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1041, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnCases = new JButton("CASES");
		btnCases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationCovidTop.sortListByCases(dataCovid);
				String col[]= {"Số thứ tự", "Quốc gia", "Số ca",};
				Object [] row = new Object[3];
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(col);
				table.setModel(model);
				for (int i =0; i< dataCovid.size();i++) {
					String country = dataCovid.get(i).getCountry();
					int cases = dataCovid.get(i).getCases();
					row[0] = i+1;
					row[1] = country;
					row[2] = cases;
					model.addRow(row);
				}	
			}
		});
		btnCases.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCases.setBounds(509, 654, 85, 28);
		contentPane.add(btnCases);
		
		JButton btnDeaths = new JButton("DEATHS");
		btnDeaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationCovidTop.sortListbyDeaths(dataCovid);
				
				String col[]= {"Number", "Country", "Deaths"};
				Object [] row = new Object[3];
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(col);
				table.setModel(model);
				for (int i =0; i< dataCovid.size();i++) {
					String country = dataCovid.get(i).getCountry();
					int deaths = dataCovid.get(i).getDeaths();					
					row[0] = i+1;
					row[1] = country;
					row[2] = deaths;
					model.addRow(row);
				}	
			}
		});
		btnDeaths.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeaths.setBounds(604, 653, 103, 30);
		contentPane.add(btnDeaths);
		
		JPanel panel_left = new JPanel();
		panel_left.setBounds(10, 10, 492, 683);
		contentPane.add(panel_left);
		panel_left.setLayout(null);
		
		JPanel pn_piechart = new JPanel();
		pn_piechart.setBackground(Color.ORANGE);
		pn_piechart.setBounds(0, 293, 492, 390);
		panel_left.add(pn_piechart);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 0, 492, 293);
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
		scrollPane.setBounds(512, 10, 515, 634);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = 1;
				int row = table.getSelectedRow();
				value = table.getModel().getValueAt(row, column).toString();

				// thông tin covid				
				showInforCovid(lblName,lblCases, lblActive, lblDeaths, lblRecover, lblflag);

				// vẽ Pie Chart				
				ChartPanel chart = PieChart();
				chart.setBounds(0,0,1130,600);
				pn_piechart.removeAll();
				pn_piechart.add(chart);
				pn_piechart.updateUI();
			}
		});
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD , 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Số thứ tự", "Quốc gia", "Tổng số ca"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData = new byte[65536];
				receiveData = new byte[65536];
				String tmp = "$topcovid";
				
				try {
					clientSocket = new DatagramSocket();
					if (tmp.equalsIgnoreCase("bye")) {
						clientSocket.close();
						System.exit(0);
					}
					
					sendData = serialize(tmp.toString());
					InetAddress address = InetAddress.getByName("localhost");
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 3333);
					clientSocket.send(sendPacket);
					
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					dataCovid = (ArrayList) deserialize(receivePacket.getData());
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setFont(new Font("Arial", Font.BOLD, 14));
		btnLoad.setBounds(725, 654, 85, 27);
		contentPane.add(btnLoad);
	}
	
	static public ChartPanel PieChart() {
		double  act = 0, dea = 0, rec = 0, cases=0;
		double res_per = 0, act_per = 0, dea_per = 0, rec_per = 0;
		for (int i =0; i< dataCovid.size();i++) {
			if (dataCovid.get(i).getCountry().equals(value)){
				cases = dataCovid.get(i).getCases();
				act = (dataCovid.get(i).getActive()/cases)*100;
				dea = (dataCovid.get(i).getDeaths()/cases)*100;
				rec = (dataCovid.get(i).getRecovered()/cases)*100;
			}
		}
		act_per = ((double)Math.round(act * 100) / 100);
		dea_per = ((double)Math.round(dea * 100) / 100);
		rec_per = ((double)Math.round(rec * 100) / 100);
		
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		dataset.setValue("Tử vong "+String.valueOf(dea_per), dea_per);
		dataset.setValue("Điều trị "+String.valueOf(act_per), act_per);
		dataset.setValue("Đã khỏi "+String.valueOf(rec_per), rec_per);
		
		JFreeChart chart = ChartFactory.createPieChart3D("PieChart", dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	// hiển thị lá cờ
	static public ImageIcon showFlag (String link) {
		Image img = null;
        try {
            URL url = new URL(link);
            img = ImageIO.read(url);
        } 
        catch (IOException e1) {
        }
        
        Image newimg = img.getScaledInstance(80, 60, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(newimg);
		return imageIcon;	
	}
	
	static public void showInforCovid(JLabel lblName, JLabel lblCases, JLabel lblActive, JLabel lblDeaths, JLabel lblRecover, JLabel lblflag) {
		String country = null, flag =null;
		int cases =0, act =0,dea = 0, rec = 0;
		for (int i =0; i< dataCovid.size();i++) {
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
