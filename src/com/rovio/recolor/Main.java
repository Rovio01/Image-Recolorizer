package com.rovio.recolor;

import java.awt.Color;
import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		/*
		Scanner scan=new Scanner(System.in);
		System.out.println("This program takes a picture and three RGB values, and replaces each pixel's difference from the background");
		System.out.println("color to the subject color with the difference from the background color to the new color.");
		System.out.println("Please enter a filename:");
		String filename=scan.nextLine();
		System.out.println("Please enter the specified background color in the form \"r,g,b\" or \"r,g,b,a\":");
		String backgroundString=scan.nextLine();
		System.out.println("Please enter the specified foreground color in the form \"r,g,b\" or \"r,g,b,a\":");
		String foregroundString=scan.nextLine();
		System.out.println("Please enter the new foreground color in the form \"r,g,b\" or \"r,g,b,a\":");
		String newColorString=scan.nextLine();
		*/
		
		String filename="C:\\Users\\Rovio\\Desktop\\test2.png";
		String backgroundString="0,0,0";
		String foregroundString="255,255,255";
		String newColorString="255,0,0";
		String outputFilename="C:\\Users\\Rovio\\Desktop\\test2Done.png";
		
		Picture picture=new Picture(new File(filename));
		//picture.show();
		String[] bgStringArr=backgroundString.split(",");
		String[] fgStringArr=foregroundString.split(",");
		String[] ncStringArr=newColorString.split(",");
		int[] bgRGB=new int[3];
		int[] fgRGB=new int[3];
		int[] ncRGB=new int[3];
		for (int i=0;i<3;i++) {
			bgRGB[i]=Integer.valueOf(bgStringArr[i]);
			fgRGB[i]=Integer.valueOf(fgStringArr[i]);
			ncRGB[i]=Integer.valueOf(ncStringArr[i]);
		}
		Color background=new Color(bgRGB[0],bgRGB[1],bgRGB[2]);
		Color foreground=new Color(fgRGB[0],fgRGB[1],fgRGB[2]);
		Color newColor=new Color(ncRGB[0],ncRGB[1],ncRGB[2]);
		Picture finalPicture=changePrimaryColor(picture,background,foreground,newColor);
		finalPicture.save(new File(outputFilename));
		//scan.close();
	}
	
	public static Picture changePrimaryColor(Picture base, Color background, Color foreground, Color newColor) {
		Picture out=(Picture)base.cloneThis();
		HSVColor backgroundHSV=new HSVColor(background);
		HSVColor foregroundHSV=new HSVColor(foreground);
		HSVColor newHSV=new HSVColor(newColor);
		
		for (int h=0;h<out.height();h++) {
			for (int w=0;w<out.width();w++) {
				HSVColor initialHSV=new HSVColor(out.get(w, h));
				HSVColor finalHSV=mixHSVColor(initialHSV,backgroundHSV,foregroundHSV,newHSV);
				Color pixelColor=finalHSV.getRGBColor();
				out.set(w, h, pixelColor);
			}
		}
		
		return out;
	}
	
	private static HSVColor mixHSVColor(HSVColor i, HSVColor b, HSVColor s, HSVColor n) {
		return new HSVColor(true,
				mix(i.getH(),b.getH(),s.getH(),n.getH()),
				i.getS(),
				i.getV()
				);
	}
	
	private static double mix(double i, double b, double s, double n) {
		return ((i-b)/(s-b))*n;
	}
}
