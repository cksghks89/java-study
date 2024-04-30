package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PhoneList01 {
	public static void main(String[] args) {
		try {
			// 파일 정보 자체를 다루는 클래스 (크기, 생성일, 수정일 등등 메타데이터)
			File file = new File("./phone.txt");
			if (!file.exists()) {
				System.out.println("file not found");
				return;
			}

			FileInputStream fis = new FileInputStream(file);
			fis.read();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
}
