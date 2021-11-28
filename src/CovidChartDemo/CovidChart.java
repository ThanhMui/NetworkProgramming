package CovidChartDemo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import CovidChartDemo.InfomationCovid;

import javax.swing.JScrollPane;

public class CovidChart extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CovidChart frame = new CovidChart();
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
	public CovidChart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1182, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane Panel = new JScrollPane();
		Panel.setBounds(10, 5, 1148, 620);
		contentPane.add(Panel);
		
		JButton btnLineChart = new JButton("LineChart");
		btnLineChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ChartPanel chart = LineChart();
					chart.setBounds(0,0,1148,620);
					Panel.removeAll();
					Panel.add(chart);
					Panel.updateUI();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		

		btnLineChart.setFont(new Font("Arial", Font.BOLD, 14));
		btnLineChart.setBounds(10, 630, 116, 29);
		contentPane.add(btnLineChart);
		
		JButton btnBarChart = new JButton("BarChart");
		btnBarChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ChartPanel chart = BarChart();
					chart.setBounds(0,0,1148,620);
					Panel.removeAll();
					Panel.add(chart);
					Panel.updateUI();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBarChart.setFont(new Font("Arial", Font.BOLD, 14));
		btnBarChart.setBounds(143, 631, 116, 29);
		contentPane.add(btnBarChart);
	}
	
	static public ChartPanel LineChart() throws IOException, InterruptedException {
		String confirm ="Confirmed";
		String deadths ="Deadths";
		String recovered = "Recovered";
		InfomationCovid c = new InfomationCovid();
		ArrayList<CovidInfo> listCovid = c.getDataCovid("Vietnam", "2021-08-01","2021-08-20");
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (CovidInfo covidInfor: listCovid) {
			String d = StringUtils.substringBefore(covidInfor.date, "T00:00:00.000Z");
			dataset.addValue(covidInfor.confirmed, confirm, d);
			dataset.addValue(covidInfor.deaths, deadths, d);
			dataset.addValue(covidInfor.recovered, recovered, d);		
		}
		
		JFreeChart chart = ChartFactory.createLineChart(  
	            "Covid", // Chart title  
	            "Date", // X-Axis Label  
	            "Value", // Y-Axis Label  
	            dataset  
	            );
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
	static public ChartPanel BarChart() throws IOException, InterruptedException {
		String confirm ="Confirmed";
		String deadths ="Deadths";
		String recovered = "Recovered";
		InfomationCovid c = new InfomationCovid();
		ArrayList<CovidInfo> listCovid = c.getDataCovid("Vietnam", "2021-03-01","2021-03-15");
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (CovidInfo covidInfor: listCovid) {
			String d = StringUtils.substringBefore(covidInfor.date, "T00:00:00.000Z");
			dataset.addValue(covidInfor.confirmed, confirm, d);
			dataset.addValue(covidInfor.deaths, deadths, d);
			dataset.addValue(covidInfor.recovered, recovered, d);		
		}
		
		JFreeChart chart = ChartFactory.createBarChart(
				"Coivd",
				"Date",
				"Value",
				dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
		
	}
}
