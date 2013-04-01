package fft;

import java.io.*;
import java.util.*;

public class FFT_Main {
	public static void main(String[] args) {
		FFT mFFT = new FFT();
		
		StringTokenizer str = null;
     	try{
    		File file = new File("data.txt");
			String data = "";

    		if(file.exists()){
    			BufferedReader inFile = new BufferedReader(new FileReader(file));
    			String line = null;
    			while((line = inFile.readLine()) != null){
    				if(line != null){
    					data = line;
    					System.out.println("Data input Complete");
    				}
    			}
    		}
    		str = new StringTokenizer(data, "\t");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
     	
     	int In_N = 128;
     	int N = 128 * 8; // 1024
        Complex[] pre_x = new Complex[In_N];
        Complex[] x = new Complex[N];
        
        // original data
        for (int i = 0; i < In_N; i++) {
            pre_x[i] = new Complex(i, 0);
//            x[i] = new Complex(-2*Math.random() + 1, 0);
            pre_x[i] = new Complex(Double.valueOf(str.nextToken()), 0);
        }
        
        int In = 0;
        int inn = 0;
        	for (inn = 0; inn < 1024; inn++) {
        		x[inn] = pre_x[In];
        		if(inn != 0 && inn % 8 == 0){
        			In++;
        		}
        	}

//        for (int i = 0; i < x.length; i++){
//        	System.out.print(i+1 + " : ");
//        	System.out.println(x[i]);
//        }
              
        System.out.println(x.length);
        // FFT of original data
        Complex[] y = mFFT.fft(x);
        ArrayList<Double> list_value = new ArrayList<Double>(mFFT.Calculator(y));
        									// 주파수별 파워 스펙트럼 크기//

        for (int i = 0; i < y.length; i++){
        	System.out.print(i+1 + " : ");
        	System.out.println(y[i]);
        }
        
        System.out.println("Calcaulator ----------->");
        int index = 0;
        while(index < list_value.size() - 1){
        	System.out.println(index+1 + " : " +list_value.get(index++));
        }
        
//        ArrayList<Double> power_list = new ArrayList<>();
//        double temp_sum = 0;
//        int count = 0;
//        index = 1;
//        while(index < list_value.size()){
//        	temp_sum += list_value.get(index - 1);
//        	if(index % 3 == 0){
//        		System.out.println(++count + " : " + temp_sum);
//        		power_list.add(temp_sum);
//        		temp_sum = 0;
//        	}
//        	index++;
//        }
	}

}
