package project.medical.ui;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;


public class DrawChart {
	private String personID;
	private Connection myCon;
	public DrawChart(String thepersonID) throws FileNotFoundException, SQLException, IOException {
		this();
		this.personID = thepersonID;
		
	}
	public  DrawChart() throws SQLException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
		System.out.println("myConect Successfull");
	}
	public void drawingHeightChart() {
		try{
           	final String SQL = "SELECT height, id FROM weightheight where personID = " + this.personID;
	        final CategoryDataset dataset = new JDBCCategoryDataset(myCon, SQL);
	     
	        JFreeChart chart = ChartFactory.createLineChart("Height Chart","Time","Height", dataset, PlotOrientation.VERTICAL, false, false, false);
	        CategoryPlot catplot = chart.getCategoryPlot();
	        catplot.setRangeGridlinePaint(Color.BLACK);
	        
	        ChartFrame frame = new ChartFrame("Chart of kid", chart);
	        frame.setVisible(true);
	        frame.setSize(450, 450);
	      }
	    catch(Exception e){
	        JOptionPane.showMessageDialog(null, e);
	    }
	}
	public void drawingWeightChart() {
		try{
           	final String SQL = "SELECT weight, id FROM weightheight where personID = " + this.personID;
	        final CategoryDataset dataset = new JDBCCategoryDataset(myCon, SQL);
	     
	        JFreeChart chart = ChartFactory.createLineChart("Weight Chart","Time","Weight", dataset, PlotOrientation.VERTICAL, false, false, false);
	        CategoryPlot catplot = chart.getCategoryPlot();
	        catplot.setRangeGridlinePaint(Color.BLACK);
	        
	        ChartFrame frame = new ChartFrame("Chart of kid", chart);
	        frame.setVisible(true);
	        frame.setSize(450, 450);
	      }
	    catch(Exception e){
	        JOptionPane.showMessageDialog(null, e);
	    }
	}
}
