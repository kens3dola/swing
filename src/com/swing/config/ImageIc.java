package com.swing.config;

import java.awt.Image;

import javax.swing.ImageIcon;


public class ImageIc {

	public static ImageIcon getImgIcon(Class<?> c,String src,int  width,int heigth) {
		return new ImageIcon(new ImageIcon(c.getResource(src)).getImage()
				.getScaledInstance(width, heigth, Image.SCALE_SMOOTH));
	}
}
