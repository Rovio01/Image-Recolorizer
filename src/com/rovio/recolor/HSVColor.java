package com.rovio.recolor;

import java.awt.Color;

public class HSVColor {
	
	private double h; //Hue (base color)
	private double s; //Saturation (lightness)
	private double v; //Value (darkness)
	
	public HSVColor(Color c) {
		this(c.getRed(), c.getGreen(), c.getBlue());
	}
	
	public HSVColor(boolean differentiatorThatDoesntCareWhatValueItGets, double h, double s, double v) {
		this.h=h;
		this.s=s;
		this.v=v;
	}
	
	public HSVColor(int r, int g, int b) {
		double rP=r/255;
		double gP=g/255;
		double bP=b/255;
		double cMax=Math.max(Math.max(rP, gP), bP);
		double cMin=Math.min(Math.min(rP, gP), bP);
		double delta=cMax-cMin;
		
		if (delta==0) {
			h=0;
		}
		else if (cMax==rP) {
			h=60*(((gP-bP)/delta)%6);
		}
		else if (cMax==gP) {
			h=60*((bP-rP)/delta+2);
		}
		else if (cMax==bP) {
			h=60*((rP-gP)/delta+4);
		}
		else {
			//Should never get here, value just set so that the compiler will be happy
			h=0;
			throw new NumberFormatException("RGB values may hav been parsed incorrectly");
		}
		if (cMax==0) {
			s=0;
		}
		else {
			s=delta/cMax;
		}
		v=cMax;
	}
	
	public Color getRGBColor() {
		double c=v*s;
		double x=c*(1-Math.abs((h/60)%2-1));
		double m=v-c;
		
		//Normalize h to be between 0 inclusive and 360 exclusive
		double hNormalized;
		if (0<=h&&h<360) {
			hNormalized=h;
		}
		else if (h<0) {
			double hTemp=h;
			while (hTemp<0) {
				hTemp+=360;
			}
			hNormalized=hTemp;
		}
		else {//if 360<=h
			double hTemp=h;
			while (360<=hTemp) {
				hTemp-=360;
			}
			hNormalized=hTemp;
		}
		
		double rP;
		double gP;
		double bP;
		
		if (0<=hNormalized&&hNormalized<60) {
			rP=c;
			gP=x;
			bP=0;
		}
		else if (60<=hNormalized&&hNormalized<120) {
			rP=x;
			gP=c;
			bP=0;
		}
		else if (120<=hNormalized&&hNormalized<180) {
			rP=0;
			gP=c;
			bP=x;
		}
		else if (180<=hNormalized&&hNormalized<240) {
			rP=0;
			gP=x;
			bP=c;
		}
		else if (240<=hNormalized&&hNormalized<300) {
			rP=x;
			gP=0;
			bP=c;
		}
		else if (300<=hNormalized&&hNormalized<360) {
			rP=c;
			gP=0;
			bP=x;
		}
		else {
			//Again, shouldn't ever be triggered, but the compiler will get TRIGGERED regardless
			rP=0;
			gP=0;
			bP=0;
		}
		return new Color((int)(rP+m)*255,(int)(gP+m)*255,(int)(bP+m)*255);
	}
	
	public Color getRGBColor(int alpha) {
		Color nonAlpha=getRGBColor();
		return new Color(nonAlpha.getRed(),nonAlpha.getGreen(),nonAlpha.getBlue(),alpha);
	}
	
	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}
}
