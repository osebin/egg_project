package chart;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JInternalFrame;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

import windowbuilder.*;

public class TimeLineChart_1{
	StringTokenizer str = null;
	ValueGenerator gen;
	public static double[] TimeLineChart_1_EEGDATA = new double[14];
	public static int EEG_Channel = 99;
	public static boolean EEG_DATA_OK = false;
	
	public TimeLineChart_1() {
		System.out.println("Time Line Chart.....");
		TimeLineChart_1.TimeLineChart_1_EEGDATA = AllChannel.EEGDATA;
	}
	
	public void setData(StringTokenizer str){
		this.str = null;
		this.str = str;
		System.out.println("Setting EEG Data.....");
	}
	
	public JFreeChart getChart() {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
			null, // title 
			null, // timeAxisLabel 
			null, // valueAxisLabel 
			getDataSet(), 
			false, // legend 
			false, // tooltip 
			false); // urls
		
		chart.setBorderPaint(Color.black);
		
		XYPlot plot = (XYPlot) chart.getPlot(); 
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
		
		ValueAxis rangeAxis = plot.getRangeAxis();
//		rangeAxis.setRange(-200, 200);
		
		DateAxis timeAxis = (DateAxis) plot.getDomainAxis(); 
		timeAxis.setDateFormatOverride(new SimpleDateFormat("m:s"));
		
		gen = new ValueGenerator(plot); 
		
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart));
		
		return chart; 
	}

	public void Timer_Stop(){
		gen.stop();
	}
	public void Timer_Start(){
		gen.start();
	}

	private XYDataset getDataSet() { 
		TimeSeries series1 = new TimeSeries("time");
		TimeSeriesCollection dataSet = new TimeSeriesCollection(); 
		dataSet.addSeries(series1);		
		return dataSet; 
	}

	public class ValueGenerator extends Timer implements ActionListener { 
		private static final long serialVersionUID = 0x00L; 
		private XYPlot plot;
		private int number = 0;
		private ArrayList<Double> arr = new ArrayList<Double>();
		
		public ValueGenerator(XYPlot plot) { 
			super(60, null); 
			
			this.plot = plot;
			this.addActionListener(this);
		}

		public void actionPerformed(final ActionEvent event) {
			TimeSeries series1 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(0);
			
			RegularTimePeriod period = RegularTimePeriod.createInstance(Millisecond.class, new Date(), TimeZone.getDefault());
			double value;
			
			if(str != null && TimeLineChart_1.EEG_Channel == 99){
				if(str.hasMoreTokens()){
					value = Double.valueOf(str.nextToken());
				}else{
					value = 0;
				}
			}else if(TimeLineChart_1.EEG_Channel != 99){
				value = TimeLineChart_1_EEGDATA[TimeLineChart_1.EEG_Channel];
			}else{
				value = 0;
			}
			
			series1.add(period, value);
			
			if(series1.getItemCount() > 500) { 
				int remove = series1.getItemCount() - 500; 
				series1.delete(0, remove);
			}
		}
	}
}
