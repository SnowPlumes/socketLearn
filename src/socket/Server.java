package socket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public void start() {
		try {
			// ����ServerSocket
			System.out.println("���Դ�������...");
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("�����ɹ� �ȴ�����...");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("���ӳɹ�");
				
				SocketHandler handler = new SocketHandler(socket);
				handler.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

}
