package socket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public void start() {
		try {
			// 创建ServerSocket
			System.out.println("尝试创建连接...");
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("创建成功 等待连接...");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("连接成功");
				
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
