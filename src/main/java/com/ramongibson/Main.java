package com.ramongibson;

public class Main {

	public static void main(String[] args) {
		SFTPManager sftpManager = new SFTPManager("ip", "user", "pass");
		System.out.println(sftpManager.connect());
		sftpManager.sendFile("/users/user/downloads/Elastic.pdf", "/users/user/downloads/new");
	}
}
