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
			logger.info("��������...");
			ServerSocket serverSocket = new ServerSocket(2233);
			logger.info("���ӳɹ� �ȴ�����");

			int i = 1;
			while (true) {
				Socket socket = serverSocket.accept();
				logger.info("�ͻ���" + i + "���ӳɹ�");

				SocketHandler handler = new SocketHandler(socket);
				handler.setName("�ͻ���" + i);
				handler.start();

				SocketFactory.getInstance().addList(handler);
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
