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

import windowbuilder.*;

@SuppressWarnings("serial")
public class BarChart_FFT { 
	
	Animator2 animator;
	
	public BarChart_FFT() {
		// TODO Auto-generated constructor stub
	}

	public static StringTokenizer str = null;
	public static ArrayList<Double> fft_data = null;
	
	public void setData(StringTokenizer str){
		BarChart_FFT.str = str;
	}
	
	public static void setData_fft(ArrayList<Double> data){
		BarChart_FFT.fft_data = data;
	}
    private static DefaultCategoryDataset createDataset() {   
           
        // row keys...   
        String []series1 = {"0", "1", "2", "3", "4"};
    
        // column keys...
        String []category = {"DELTA", "THETA", "ALPHA", "SMR", "BETA"};
   
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();   

    	dataset.addValue(0.0, series1[0], category[0]);
    	dataset.addValue(0.0, series1[1], category[1]);
    	dataset.addValue(0.0, series1[2], category[2]);
    	dataset.addValue(0.0, series1[3], category[3]);
    	dataset.addValue(0.0, series1[4], category[4]);

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
           
        GradientPaint Paint1 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint Paint2 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint Paint3 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
        GradientPaint Paint4 = new GradientPaint(0.0F, 0.0F, Color.yellow, 0.0F, 0.0F, new Color(64, 64, 0));
        GradientPaint Paint5 = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, new Color(64, 0, 64));
        
        localLayeredBarRenderer.setSeriesPaint(0, Paint1);
        localLayeredBarRenderer.setSeriesPaint(1, Paint2);
        localLayeredBarRenderer.setSeriesPaint(2, Paint3);
        localLayeredBarRenderer.setSeriesPaint(3, Paint4);
        localLayeredBarRenderer.setSeriesPaint(4, Paint5);
        
        localLayeredBarRenderer.setSeriesPaint(0, Paint1);
        
        return chart;              
    }
    
    static class CustomBarRenderer extends BarRenderer{
    	
    }

    public JFreeChart createDemoPanel() {   
        DefaultCategoryDataset dataset = createDataset();   
        JFreeChart chart = createChart(dataset);   
        animator = new Animator2(dataset);   
        
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
class Animator2 extends Timer implements ActionListener {
	double[] change = new double[5];// 0 1 2 3 4
    private DefaultCategoryDataset dataset;   
   
    Animator2(DefaultCategoryDataset dataset) {   
        super(500, null);
        this.dataset = dataset;   
        addActionListener(this);   
    }   

    public void actionPerformed(ActionEvent event) {
        for(int i = 0; i < 5; i ++){
        	if(BarChart_FFT.fft_data != null){
        		ArrayList<Double> indata = new ArrayList<Double>();
        		indata = BarChart_FFT.fft_data;
        		if(i == 0){
        			for(int in = 0; in < 4; in++){// 0 1 2 3 >> 1 ~ 4
            			change[i] += indata.get(in);
            		}
        		}else if(i == 1){
        			for(int in = 3; in < 8; in++){// 3 4 5 6 7 >> 4 ~ 8 
            			change[i] += indata.get(in);
            		}
        		}else if(i == 2){
        			for(int in = 7; in < 12; in++){// 7 8 9 10 11 >> 8 ~ 12 
            			change[i] += indata.get(in);
            		}
        		}else if(i == 3){
        			for(int in = 11; in < 15; in++){// 11 12 13 14 >> 12 ~ 15 
            			change[i] += indata.get(in);
            		}
        		}else if(i == 4){
        			for(int in = 14; in < 18; in++){// 14 15 16 17 >> 15 ~ 18 
            			change[i] += indata.get(in);
            		}
        		}
        	}else{
        		change[i] = 0;
        	}
        	
        	Comparable rowKey = this.dataset.getRowKey(i);
			Comparable key = this.dataset.getColumnKey(i);
        	dataset.setValue(change[i], rowKey, key);
        }
        State_Log.emoState[5] = (change[4] + change[3])/change[2];

        for(int i = 0; i < 5; i ++){
        	change[i] = 0;
        }
    }   
}   