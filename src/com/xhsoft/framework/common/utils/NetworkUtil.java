package com.xhsoft.framework.common.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkUtil {
	
	
	/**
	 * 模拟Ping命令
	 */
	public void ping(){}
	
	/**
	 * 设置本地连接
	 */
	public void setNetwork(){}
	
	public void ping(String host){
		try { 
            InetAddress address = InetAddress.getByName(host);
            System.out.println(address);
            System.out.println(address.isReachable(5000)); 
        } catch (UnknownHostException e){ 
            e.printStackTrace(); 
        } catch (IOException e){ 
            e.printStackTrace(); 
        }  
	}
	
	public void telnet(String ip,int ...port){
		Socket server = new Socket();
		int p = 23;
		if(port.length>0){
			p = port[0];
		}
        
		InetSocketAddress address = new InetSocketAddress(ip,p); 
        try {
			server.connect(address, 5000); 
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void openUrl(String url){
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("explorer "+url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			process.destroy();
		}
	}

}
