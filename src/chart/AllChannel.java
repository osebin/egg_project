package chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.*;
import org.jfree.ui.RectangleInsets;

import windowbuilder.EEG_Main;

import chart.TimeLineChart_1.*;

public class AllChannel {
	StringTokenizer []str = null;
	ValueGenerator gen;
	
	public static double []Pearson = new double[14];
	public static double []EEGDATA = new double[14];

	public AllChannel() {
		System.out.println("Time All Channel Start.....");
	}
	
	public void setData(StringTokenizer[] str){
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
		
		// 범위 고정 -100 ~ 100
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setRange(-1500, 1800);
		
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
		TimeSeries series2 = new TimeSeries("time");
		TimeSeries series3 = new TimeSeries("time");
		TimeSeries series4 = new TimeSeries("time");
		TimeSeries series5 = new TimeSeries("time");
		TimeSeries series6 = new TimeSeries("time");
		TimeSeries series7 = new TimeSeries("time");
		TimeSeries series8 = new TimeSeries("time");
		TimeSeries series9 = new TimeSeries("time");
		TimeSeries series10 = new TimeSeries("time");
		TimeSeries series11 = new TimeSeries("time");
		TimeSeries series12 = new TimeSeries("time");
		TimeSeries series13 = new TimeSeries("time");
		TimeSeries series14 = new TimeSeries("time");
		
		TimeSeriesCollection dataSet = new TimeSeriesCollection(); 
		dataSet.addSeries(series1);
		dataSet.addSeries(series2);
		dataSet.addSeries(series3);
		dataSet.addSeries(series4);
		dataSet.addSeries(series5);
		dataSet.addSeries(series6);
		dataSet.addSeries(series7);
		dataSet.addSeries(series8);
		dataSet.addSeries(series9);
		dataSet.addSeries(series10);
		dataSet.addSeries(series11);
		dataSet.addSeries(series12);
		dataSet.addSeries(series13);
		dataSet.addSeries(series14);
		
		return dataSet; 
	}

	class ValueGenerator extends Timer implements ActionListener { 
		private static final long serialVersionUID = 0x00L; 
		private XYPlot plot;
		private int number = 0;
		
		
		public ValueGenerator(XYPlot plot) { 
			super(60, null); 
			
			this.plot = plot;
			this.addActionListener(this);
		}

		public void actionPerformed(final ActionEvent event) { 
			TimeSeries series1 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(0);
			TimeSeries series2 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(1);
			TimeSeries series3 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(2);
			TimeSeries series4 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(3);
			TimeSeries series5 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(4);
			TimeSeries series6 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(5);
			TimeSeries series7 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(6);
			TimeSeries series8 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(7);
			TimeSeries series9 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(8);
			TimeSeries series10 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(9);
			TimeSeries series11 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(10);
			TimeSeries series12 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(11);
			TimeSeries series13 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(12);
			TimeSeries series14 = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(13);
			
			RegularTimePeriod period = RegularTimePeriod.createInstance(Millisecond.class, new Date(), TimeZone.getDefault());
			double []value = new double[14];
			
			if(str != null){
				for(int i = 0; i < 14; i++){
					if(str[i].hasMoreTokens()){
						value[i] = Double.valueOf(str[i].nextToken());
					}else{
						value[i] = 0;
					}
				}
			}else{
				for(int i = 0; i < 14; i++){
					value[i] = AllChannel.EEGDATA[i];
				}
				TimeLineChart_1.EEG_DATA_OK = true;
			}
			
			if(EEG_Main.DB_Click == true){
				EEG_Main.db.Init_DB_Data(value);
			}
			AllChannel.Pearson = value;
			
			series1.add(period, value[0] + 1400);
			series2.add(period, value[1] + 1200);
			series3.add(period, value[2] + 1000);
			series4.add(period, value[3] + 800);
			series5.add(period, value[4] + 600);
			series6.add(period, value[5] + 400);
			series7.add(period, value[6] + 200);
			series8.add(period, value[7]);
			series9.add(period, value[8] - 200);
			series10.add(period, value[9] - 400);
			series11.add(period, value[10] - 600);
			series12.add(period, value[11] - 800);
			series13.add(period, value[12] - 1000);
			series14.add(period, value[13] - 1200);
			
			if(series1.getItemCount() > 150) { 
				int remove = series1.getItemCount() - 150; 
				series1.delete(0, remove);
				series2.delete(0, remove);
				series3.delete(0, remove);
				series4.delete(0, remove);
				series5.delete(0, remove);
				series6.delete(0, remove);
				series7.delete(0, remove);
				series8.delete(0, remove);
				series9.delete(0, remove);
				series10.delete(0, remove);
				series11.delete(0, remove);
				series12.delete(0, remove);
				series13.delete(0, remove);
				series14.delete(0, remove);
			}
		}
	}
}