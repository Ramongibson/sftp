package com.ramongibson;

public class Main {

	public static void main(String[] args) {
		SFTPManager sftpManager = new SFTPManager("192.168.1.11", "Ray", "4933");
		System.out.println(sftpManager.connect());
		sftpManager.sendFile("/users/user/downloads/Elastic.pdf", "/users/user/downloads/new");
	}
}
