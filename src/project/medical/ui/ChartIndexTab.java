package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import project.medical.DAO.HistoryMedicalDAO;
import project.medical.DAO.WeightHeightDAO;
import project.medical.core.WeightHeight;


public class ChartIndexTab extends JFrame {
	private WeightHeightDAO whDAO;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String personID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChartIndexTab frame = new ChartIndexTab();
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
	public ChartIndexTab(String theidPerson) {
		this();
		personID = theidPerson;
		whDAO = new WeightHeightDAO();
	}
	public ChartIndexTab() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String querry = "Select weight, date from weightheight where personID = 1 ";
		CategoryDataset dataset = JDBCCategoryDataset(WeightHeightDAO(), querry);
		JFreeChart chart = ChartFactory.createLineChart("My chart", "Weight ","Date", dataset, PlotOrientation.VERTICAL, false, true, true);
		BarRenderer  renderer = null;
		CategoryPlot plot = null;
		renderer = new BarRenderer();
		ChartFrame frame = new ChartFrame("hello", chart)
		frame.setVisible(true);
		frame.setSize(400,650);
	}

}
