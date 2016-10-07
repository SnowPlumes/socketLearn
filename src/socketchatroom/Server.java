package socketchatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

	SocketFactory sf = SocketFactory.getInstance();
	Logger logger = LogManager.getLogger(Server.class.getName());

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	@SuppressWarnings("resource")
	public void start() {
		try {
			logger.info("创建连接...");
			ServerSocket serverSocket = new ServerSocket(2233);
			logger.info("连接成功 等待连接");

			int i = 1;
			while (true) {
				Socket socket = serverSocket.accept();
				logger.info("客户端" + i + "连接成功");

				SocketHandler handler = new SocketHandler(socket);
				handler.setName("客户端" + i);
				handler.start();

				SocketFactory.getInstance().addList(handler);
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
