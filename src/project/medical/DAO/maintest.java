package project.medical.DAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class maintest {

	public static void main(String[] args) {
		try{
			Connection myCon;
			Properties prop = new Properties();
			prop.load(new FileInputStream("sql/person.properties"));
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String dburl = prop.getProperty("dburl");
			myCon= DriverManager.getConnection(dburl,user,password);
			System.out.println("myConect Successfull");
				
           	final String SQL = "SELECT height, id FROM weightheight where personID = 2";
	        final CategoryDataset dataset = new JDBCCategoryDataset(myCon, SQL);
	        
	  
	        
	        JFreeChart chart = ChartFactory.createLineChart("Report","Time","Weight", dataset, PlotOrientation.VERTICAL, false, false, false);
	        CategoryPlot catplot = chart.getCategoryPlot();
	        catplot.setRangeGridlinePaint(Color.BLACK);
	        
	        ChartFrame frame = new ChartFrame("test nah", chart);
	        frame.setVisible(true);
	        frame.setSize(450, 450);
	      }
	    catch(Exception e){
	        JOptionPane.showMessageDialog(null, e);
	    }

	}

}
