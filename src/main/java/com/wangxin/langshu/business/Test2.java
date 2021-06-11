package com.wangxin.langshu.business;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

public class Test2 {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		//File file = new File("/Users/shenge/work/workspaces/workspace-wangxin/图片/第二课.mp4");
		//System.out.println("----->"+getVedioTime(file));
		
		//System.out.println(InetAddress.getLocalHost().getHostAddress());
		try {
			System.out.println(args[0]);
			
            String name = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(name);
            for (int i = 0; i < addresses.length; i++) {
                System.out.println(name + "[" + i + "]: "
                        + addresses[i].getHostAddress());
            }
        } catch (UnknownHostException uhe) {
            System.err.println("Unable to find: " + args[0]);
        }
	}
	
	/**
	 * 获取视频时长：秒
	 * 
	 * @param file
	 * @return
	 */
	public static Long getVedioTime(File file) {
		try {
			MultimediaObject instance = new MultimediaObject(file);
			MultimediaInfo result = instance.getInfo();
			long ls = result.getDuration() / 1000;
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	}


}
