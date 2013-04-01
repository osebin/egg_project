package database;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import windowbuilder.EEG_Main;

import com.sun.opengl.impl.windows.BITMAPINFO;

public class DB {
	class Channel{
		double ch[];
	}
	
	File[] files;
	Robot robot;
	
	private ArrayList<Channel> DB_EEG = new ArrayList<Channel>();
	
	public DB(){
		
	}
	
	public void Init_DB_Data(double data[]){
		Channel channel = new Channel();
		channel.ch = new double[14];
		
		for(int i = 0; i < 14; i++){
			channel.ch[i] = data[i];
		}
		
		DB_EEG.add(channel);
	}
	
	public void free_data(){
		DB_EEG.clear();
	}
	//�대뜑�덉뿉 txt�뚯씪 湲몄씠
	public int getFileCount(){
		return files.length;
	}
	//�대뜑�덉뿉 txt�뚯씪 諛섑솚
	public String getFileList(int i){
		return files[i].getName();		
	}
	//�대뜑 議댁옱 �щ��뺤씤
	public boolean isFolder(File filename){
		if(!filename.exists()){	//�대뜑媛�議댁옱�섏� �딆쓣��
//			System.out.println("�대뜑 �놁쓬");
			return true;
		}else{	//�대뜑媛�議댁옱 �좊븣
//			System.out.println("�대뜑 �덉쓬");
			return false;
		}
	}
	 
	public void createFolder(String name){
		File file1=new File(name);	
		String routefold=file1.getAbsolutePath();

		if(isFolder(file1))	//�대뜑 �놁쓣���대뜑 �앹꽦
		{
			file1.mkdir();			
		}
		
		//�뚯씪 �앹꽦
		createFile(name+day(),routefold);
		//�대뜑�덉뿉 txt �쒖떆
		divide(routefold);
	}
	
	public void createFolder2(String name){
		File fileimg = new File(name);	
		String routefold = fileimg.getAbsolutePath();
		
		if(isFolder(fileimg))	//�대뜑 �놁쓣���대뜑 �앹꽦
		{
			fileimg.mkdir();			
		}
		
		createImg(name+day(), routefold);
		divide(routefold);
	}
	
	public void createImg(String name, String routefold){
		File f = new File(routefold);
		
		SaveImg(f, name);
	}
	public void createFile(String name,String routefold){	
		File f=new File(routefold);
		
		//�뚯씪 �앹꽦
		SaveText(f,name);
	}
	
	public void SaveImg(File f1, String name){
		File fileimg = new File(f1.getAbsolutePath(), name+".jpg");
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try{
			Rectangle area = new Rectangle(EEG_Main.frame.getBounds().x+290, EEG_Main.frame.getBounds().y+100, 
			EEG_Main.panel_2.getWidth()-10, EEG_Main.panel_2.getHeight()-40);
			
			BufferedImage bfImage = new BufferedImage(EEG_Main.panel_2.getWidth(), EEG_Main.panel_2.getHeight(), BufferedImage.TYPE_INT_BGR);
			bfImage = robot.createScreenCapture(area);

			ImageIO.write(bfImage, "jpg", fileimg);
			System.out.println("Image ��옣 �깃났");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void SaveText(File f1,String name){
		File file=new File(f1.getAbsolutePath(),name+".eeg");
		
		try {
			  BufferedWriter out = new BufferedWriter(new FileWriter(file));
			  
			  for(int j = 0; j < 14; j++){
				  for(int i = 0; i < DB_EEG.size(); i++){				  
					  out.write(String.valueOf(DB_EEG.get(i).ch[j]) + "\t");
				  }
				  out.newLine();
			  } 
			  DB_EEG.clear();
		      out.close();
			      
		    } catch (IOException e) {
		        System.err.println(e); // �먮윭媛��덈떎硫�硫붿떆吏�異쒕젰
		    }
		
	}
	
	public void divide(String dir1){
		File f=new File(dir1);
		//.txt�뚯씪 嫄몃윭�닿린
		files=f.listFiles(new FilenameFilter() {			
			@Override
			public boolean accept(File dir, String name) {
				boolean fileOK = true;
				// TODO Auto-generated method stub
				 if ("eeg" != null)
				      fileOK &= name.endsWith('.' + "eeg");				 
				return fileOK;
			}
		});
		//�뚯씪 異쒕젰(�뺤씤)
		for(int i=0;i<files.length;i++)
			System.out.println(files[i].getName());
	}
	//�좎쭨 �쒓린!
	public String today() {
	      SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd",
	        Locale.KOREA);
	      Date current = new Date();
	      String date = formater.format(current);
	      Calendar cal = new GregorianCalendar();
	      int mHour = cal.get(Calendar.HOUR_OF_DAY);
	      int mMinute = cal.get(Calendar.MINUTE);
	      int mSecond = cal.get(Calendar.SECOND);
	      // yyyy-MM-dd HH:mm:ss
	      String nowDate = date + " " + mHour + ":" + mMinute + ":" + mSecond;
	      return nowDate;
    }
	
	public String day() {
	      SimpleDateFormat formater = new SimpleDateFormat("(MMdd)", Locale.KOREA);
	      Date current = new Date();
	      Calendar cal = new GregorianCalendar();
	      
	      String date = formater.format(current) +
	    		  cal.get(Calendar.HOUR_OF_DAY) + "." +
	    		  cal.get(Calendar.MINUTE) + "." +
	    		  cal.get(Calendar.SECOND);
	      return date;
	}
	
}
