package com.mls.pay.access.testdata;

import java.io.File;

public class MoveFile {
	public static void main(String[] args){
		File f=new File(System.getProperty("user.dir")+"/test-output/old/index.html");
		String newpath=System.getProperty("user.dir")+"/report";
		File fnewpath = new File(newpath); 
		if(!fnewpath.exists())
			fnewpath.mkdirs(); 
		File fn=new File(newpath,f.getName());
		f.renameTo(fn);
	}
}
