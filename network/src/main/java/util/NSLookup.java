package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("> ");
			String url = sc.nextLine();

			if ("exit".equals(url))
				break;

			InetAddress[] allByName;
			try {
				allByName = InetAddress.getAllByName(url);
				for (InetAddress inetAddress : allByName) {
					System.out.println(inetAddress.getHostName() + " : " + inetAddress.getHostAddress());
				}
			} catch (Exception e) {
				System.out.println("error : " + e);
			}
		}
	}
}
