package com.swing.frame.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;

public class Filechooser {
	JFileChooser filechooser = new JFileChooser();

	/**
	 * Create the frame.
	 */
	public String choose() {
		if(filechooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			java.io.File file = filechooser.getSelectedFile();
			
			Thread t = new Thread() {
				public void run() {
					File f = new File("images/"+file.getName());
					if(!f.exists()) {
						InputStream in = null;
						OutputStream out = null;
						try {
							in = new FileInputStream(file);
							out = new FileOutputStream(f);
							byte[] buff = new byte[1024];
							
							int bytesRead ;
							
							while ((bytesRead = in.read(buff)) > 0) {
								out.write(buff, 0, bytesRead);
							}
							in.close();
							out.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
			};
			t.start();
			return file.getName();
		}
		return null;
	}

}
