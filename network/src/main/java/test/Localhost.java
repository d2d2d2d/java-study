package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Localhost {
	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();	//나의 컴퓨터 네트워크 정보
			
			String hostname = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			byte[] addresses = inetAddress.getAddress();
			for(byte address : addresses) {
				System.out.println(address & 0x000000ff);
			}
			//System.out.println(Arrays.toString(addresses));
			
			System.out.println(hostname);
			System.out.println(hostAddress);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
	}

}
