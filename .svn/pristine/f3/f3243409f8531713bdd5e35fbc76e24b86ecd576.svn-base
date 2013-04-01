package eegdata;

import java.io.*;
import java.util.*;

public class EEG_Data {
	
	StringTokenizer []str = new StringTokenizer[14];
	String []data = new String[14]; // TOTAL 14 Channel
	String []data_fft = new String[25]; // 0 ~ 24
	
	public EEG_Data(String path){
		try{
			File file = new File(path);		
			if(file.exists()){
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String line = null;
				int count = 0;
				
				while((line = inFile.readLine()) != null){
					if(line != null){
						data[count] = line;
						count++;
					}
				}
			}else{
				System.out.println("File Not Founded....!!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(data != null){
			for(int i = 0; i < 14; i++){
				str[i] = new StringTokenizer(data[i], "\t");
			}
		}
	}
	
	public StringTokenizer getData(int number){
		return str[number];
	}
	
	public StringTokenizer[] getDate_all(){
		return str;
	}
	
	public double getValue(int number){
		return Double.valueOf(str[number].nextToken());
	}
	public int getNumber(int number){
		if(str != null){
			return str[number].countTokens();
		}
		return -1;
	}
}
