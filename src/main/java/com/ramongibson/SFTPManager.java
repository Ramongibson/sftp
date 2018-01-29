package com.ramongibson;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FilenameUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPManager {
	private String hostIP;
	private final int port = 22;
	private String username;
	private String password;

	private Session session;
	private Channel channel;
	private ChannelSftp channelSftp;

	public SFTPManager(String ip, String username, String password) {
		this.hostIP = ip;
		this.username = username;
		this.password = password;
	}

	public boolean connect() {

		try {
			JSch shh = new JSch();
			session = shh.getSession(username, hostIP, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private boolean checkFileExtention(String file) {
		if (FilenameUtils.getExtension(file).equals("pdf")) {
			return true;
		}
		System.out.println("not a valid pdf file");
		return false;
	}

	public void sendFile(String fileToTransferDirectory, String transferToDirectory) {
		if (checkFileExtention(fileToTransferDirectory)) {
			try {
				channelSftp.cd(transferToDirectory);
				File file = new File(fileToTransferDirectory);
				channelSftp.put(new FileInputStream(file), file.getName());
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}
		
	}

}
