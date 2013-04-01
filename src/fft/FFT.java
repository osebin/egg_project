package fft;

import java.io.*;
import java.util.*;

public class FFT {
	
	public Complex[] fft(Complex[] x) {
		int N = x.length;
		if(N == 1){
			return new Complex[] { x[0] };
		}	
		if(N % 2 != 0){
			throw new RuntimeException("N is not matched");
		}
		
		Complex[] even = new Complex[N/2];
		for(int k = 0; k < N/2; k++){
			even[k] = x[2*k];
		}
		Complex[] q = fft(even);
		
		Complex[] odd = even;
		for(int k = 0; k < N/2; k++){
			odd[k] = x[2*k + 1];
		}
		Complex[] r = fft(odd);
		
		Complex[] y = new Complex[N];
		for(int k = 0; k < N/2; k++){
			double kth = -2*k*Math.PI / N;
			Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
			y[k] = q[k].plus(wk.times(r[k]));
			y[k + N/2] = q[k].minus(wk.times(r[k]));
		}
		return y;
	}
	
	public Complex[] ifft(Complex[] x){
		int N = x.length;
		Complex[] y = new Complex[N];
		
		for(int i = 0; i < N; i++){
			y[i] = x[i].conjugate();
		}
		
		y = fft(y);
		
		for(int i = 0; i < N; i++){
			y[i] = y[i].times(1.0 / N);
		}
		
		return y;
	}
	
	public Complex[] cconvolve(Complex[] x, Complex[] y){
		if(x.length != y.length) {
			throw new RuntimeException("Dimensions don't matched");
		}
		
		int N = x.length;
		
		Complex[] a = fft(x);
		Complex[] b = fft(y);
		
		Complex[] c = new Complex[N];
		for(int i = 0; i < N; i++){
			c[i] = a[i].times(b[i]);
		}
		
		return ifft(c);
	}
	
	public Complex[] convolve(Complex[] x, Complex[] y){
		Complex ZERO = new Complex(0, 0);
		
		Complex[] a = new Complex[2*x.length];
		for(int i = 0; i < x.length; i++){		a[i] = x[i];		}
		for(int i = x.length; i < 2*x.length; i++){			a[i] = ZERO;		}

        Complex[] b = new Complex[2*y.length];
        
        for (int i = 0; i <   y.length; i++){
        	b[i] = y[i];
        }
        for (int i = y.length; i < 2*y.length; i++){
        	b[i] = ZERO;
        }

        return cconvolve(a, b);
	}
	
	public void show(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }
	
	public ArrayList<Double> Calculator(Complex[] x){
		int N = x.length;
		ArrayList<Double> list = new ArrayList<Double>();
		ArrayList<Double> out_list = new ArrayList<Double>();
		for(int i = 1; i < N/2; i++){
			list.add(Math.sqrt((x[i].re()*x[i].re() + x[i].im()*x[i].im())));
		}
		
		double out = 0;
		for(int i = 0; i < list.size(); i++){
			out = out + list.get(i);
			if(i % 9 == 0){
				out_list.add(out);
				out = 0;
			}
		}
		
		return out_list;
	}
	
}
