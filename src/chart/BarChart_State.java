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
import org.jfree.ui.*;

import panel.State_Log;
import processing.Circle;

import windowbuilder.*;

@SuppressWarnings("serial")
public class BarChart_State { 
	
	Animator4 animator;
	
	public BarChart_State() {
		// TODO Auto-generated constructor stub
	}

	public static StringTokenizer str = null;
	public static ArrayList<Double> fft_data = null;
	public static void setData(StringTokenizer str){
		BarChart_State.str = str;
	}
	
	public static void setData_fft(ArrayList<Double> data){
		BarChart_State.fft_data = data;
	}
    private static DefaultCategoryDataset createDataset() {   
           
        // row keys...   
        String []series1 = {"0", "1", "2", "3", "4", "5"};
    
        // column keys...
        String []category = {"Excitement-S", "Excitement-L", "Boredom", "Frustration", "Meditation","Concentration"};
   
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();   

    	dataset.addValue(0.0, series1[0], category[0]);
    	dataset.addValue(0.0, series1[1], category[1]);
    	dataset.addValue(0.0, series1[2], category[2]);
    	dataset.addValue(0.0, series1[3], category[3]);
    	dataset.addValue(0.0, series1[4], category[4]);
    	dataset.addValue(0.0, series1[5], category[5]);
    	
        return dataset;   
    }   
       
    public static JFreeChart createChart(CategoryDataset dataset) {   
           
        // create the chart...   
        JFreeChart chart = ChartFactory.createBarChart(   
            null,         // chart title   
            null,               // domain axis label   
            null,                  // range axis label   
            dataset,                  // data   
            PlotOrientation.VERTICAL, // orientation   
            false,                     // include legend   
            false,                     // tooltips?   
            false                     // URLs?   
        );
        
        CategoryPlot localCategoryPlot = (CategoryPlot) chart.getPlot();
        localCategoryPlot.setDomainGridlinesVisible(true);
        localCategoryPlot.setRangePannable(true);
        localCategoryPlot.setRangeZeroBaselineVisible(true);
        
        NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
        localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        localNumberAxis.setRange(0, 1.1);
        
        BarRenderer localLayeredBarRenderer = new CustomBarRenderer();
        localLayeredBarRenderer.setDrawBarOutline(false);
        localLayeredBarRenderer.setBarPainter(new StandardBarPainter());
        
        localCategoryPlot.setRenderer(localLayeredBarRenderer);
        
        chart.setBackgroundPaint(Color.white);      
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);   
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);   
        plot.setRangeGridlinePaint(Color.lightGray);

        localLayeredBarRenderer.setItemMargin(-2);
        localLayeredBarRenderer.setShadowVisible(true);
        
        GradientPaint Paint1 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0)); 
        GradientPaint Paint2 = new GradientPaint(0.0F, 0.0F, Color.orange, 0.0F, 0.0F, new Color(234, 108, 61));
        GradientPaint Paint3 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint Paint4 = new GradientPaint(0.0F, 0.0F, Color.yellow, 0.0F, 0.0F, new Color(64, 64, 0));
        GradientPaint Paint5 = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, new Color(64, 0, 64));
        GradientPaint Paint6 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
        
        localLayeredBarRenderer.setSeriesPaint(0, Paint1);
        localLayeredBarRenderer.setSeriesPaint(1, Paint2);
        localLayeredBarRenderer.setSeriesPaint(2, Paint3);
        localLayeredBarRenderer.setSeriesPaint(3, Paint4);
        localLayeredBarRenderer.setSeriesPaint(4, Paint5);
        localLayeredBarRenderer.setSeriesPaint(5, Paint6);
        
        localLayeredBarRenderer.setSeriesPaint(0, Paint1);
        
        return chart;              
    }
    
    static class CustomBarRenderer extends BarRenderer{
    	
    }

    public JFreeChart createDemoPanel() {   
        DefaultCategoryDataset dataset = createDataset();   
        JFreeChart chart = createChart(dataset);   
        animator = new Animator4(dataset);     
        
        return chart;   
    }
    
    public void Timer_Stop(){
    	animator.stop();
    }
    
    public void Timer_Start(){
    	animator.start();
    }
}   
   
@SuppressWarnings("serial")
class Animator4 extends Timer implements ActionListener {
	double[] change = new double[6];// 0 1 2 3 4
    private DefaultCategoryDataset dataset;   
    Random random = new Random();
    
    Animator4(DefaultCategoryDataset dataset) {   
        super(100, null);
        this.dataset = dataset;   
        addActionListener(this);   
    }   

    public void actionPerformed(ActionEvent event) {
        for(int i = 0; i < 6; i ++){
        	if(State_Log.emoState[i] != 0 && State_Log.emoState != null){
           		change[i] = State_Log.emoState[i];
        	}else{
        		change[i] = 0;
        	}

        	Comparable rowKey = this.dataset.getRowKey(i);
			Comparable key = this.dataset.getColumnKey(i);
        	dataset.setValue(change[i], rowKey, key);
        }
        
        if(change[0] > change[4] && change[0] > change[2]){
        	Circle.P_b = 0;
        	Circle.P_g = 0;
        	Circle.P_r = 255;
        }else if(change[2] > change[4] && change[2] > change[0]){
        	Circle.P_r = 0;
        	Circle.P_g = 0;
        	Circle.P_b = 255;
        }else if(change[3] > change[4] && change[3] > change[0]){
        	Circle.P_r = 255;
        	Circle.P_g = 255;
        	Circle.P_b = 0;
        }
        
        if(change[5] > change[4] && change[5] > change[2]){
        	Circle.P_b = 0;
        	Circle.P_g = 255;
        	Circle.P_r = 0;
        }
    }   
}
