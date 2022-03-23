package pt.baie.vatreqservice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SFTPFileTrnasferUtils {

	private static String REMOTE_HOST;
	private static String REMOTE_PORT;
	private static String USERNAME;
	private static String PASSWORD;
	private static String SESSION_TIMEOUT;
	private static String CHANNEL_TIMEOUT;

	@Value("${baieuropa.app.sftpHost}")
	public void setSftpHost(String sftpHost) {
		REMOTE_HOST = sftpHost;
	}

	@Value("${baieuropa.app.sftpPort}")
	public void setSftpPort(String sftpPort) {
		REMOTE_PORT = sftpPort;
	}

	@Value("${baieuropa.app.sftpUsername}")
	public void setSftpUserName(String sftpUsername) {
		USERNAME = sftpUsername;
	}

	@Value("${baieuropa.app.sftpPassword}")
	public void setSftpPassword(String sftpPassword) {
		PASSWORD = sftpPassword;
	}

	@Value("${baieuropa.app.sftpSessionTimeout}")
	public void setSftpSessionTimeout(String sftpSessionTimeout) {
		SESSION_TIMEOUT = sftpSessionTimeout;
	}

	@Value("${baieuropa.app.sftpChannelTimeout}")
	public void setSftpChannelTimeout(String sftpChannelTimeout) {
		CHANNEL_TIMEOUT = sftpChannelTimeout;
	}

	public static Boolean sendFileSftp(String localFile, String remoteFile) {

		log.info("[SFTPFileTrnasferUtils][sendFileSftp] ========= Sending file...");

		Session jschSession = null;

		try {

			JSch jsch = new JSch();

			jschSession = jsch.getSession(USERNAME, REMOTE_HOST, Integer.parseInt(REMOTE_PORT));
			jschSession.setConfig("StrictHostKeyChecking", "no");

			// authenticate using password
			jschSession.setPassword(PASSWORD);

			// 10 seconds session timeout
			jschSession.connect(Integer.parseInt(SESSION_TIMEOUT));

			Channel sftp = jschSession.openChannel("sftp");

			// 10 seconds timeout
			sftp.connect(Integer.parseInt(CHANNEL_TIMEOUT));

			ChannelSftp channelSftp = (ChannelSftp) sftp;

			// transfer file from local to remote server
			channelSftp.put(localFile, remoteFile);

			// download file from remote server to local
			// channelSftp.get(remoteFile, localFile);

			channelSftp.exit();

			log.info("[SFTPFileTrnasferUtils][sendFileSftp] ========= File sent!");

			return true;

		} catch (Exception e) {
			// TODO: handle exception

			log.error("[SFTPFileTrnasferUtils][sendFileSftp][ERROR] ========= ERROR sendind file ->".concat(e.getMessage()));
			return false;
		}

	}

}
