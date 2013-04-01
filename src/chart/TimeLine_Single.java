package chart;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;

import windowbuilder.*;

public class TimeLine_Single {
	
	public static boolean ERP_Click = false;
	public static boolean ERP_DATA_OK = false;
	public static ArrayList<Double> ERP_Arr = new ArrayList<Double>();
	
	Animator3 animator3;

	public TimeLine_Single(){
		
	}
	private static DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(int i = 0; i < 40; i++){
			String number = String.valueOf(i);
			dataset.addValue(0, "Hz", number);
		}
	
		return dataset;
	}
	
	public static JFreeChart createChart(CategoryDataset dataset) {
			// create the chart...
			JFreeChart chart = ChartFactory.createLineChart(
				null, // chart title
				null, // domain axis label
				null, // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				false, // tooltips
				false // urls
		);
		
		chart.setBackgroundPaint(Color.white);
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.white);
		
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		return chart;
		}
	
	public JFreeChart createSinglePanel(){
		DefaultCategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		animator3 = new Animator3(dataset);
		animator3.start();
		
		return chart;
	}
	
	public void Timer_Stop(){
		animator3.stop();
	}
	
	@SuppressWarnings("serial")
	class Animator3 extends Timer implements ActionListener{
		
		private DefaultCategoryDataset dataset;
		
		public Animator3(DefaultCategoryDataset dataset) {
			super(500, null);
			this.dataset = dataset;
			addActionListener(this);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			if(TimeLine_Single.ERP_Arr.size() > 39){
				System.out.println("Single DATA - Complete");
				int Size = TimeLine_Single.ERP_Arr.size();
//					ArrayList<Double> ERP_Value = new ArrayList<>();
//					ERP_Value = TimeLine_Single.ERP_Arr;
				
				double MAX = 0;
				for(int i = 0; i < TimeLine_Single.ERP_Arr.size(); i++){
					if(TimeLine_Single.ERP_Arr.get(i) > MAX){
						MAX = TimeLine_Single.ERP_Arr.get(i);
					}
				}
				
				for(int i = 0; i < Size-1; i++){
					Comparable key = this.dataset.getColumnKey(i);
					Comparable rowkey = this.dataset.getRowKey(0);
					
					Double value =  TimeLine_Single.ERP_Arr.get(i);
					System.out.print(value + " ");
					dataset.setValue(0.0, rowkey, key);
					dataset.setValue(value, rowkey, key);
					value = 0.0;
				}
				System.out.println("");
				TimeLine_Single.ERP_Arr.clear();
			}
			
		}
		
	}
}

