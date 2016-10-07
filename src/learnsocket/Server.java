package learnsocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements ShouldContinue{
	boolean shouldContinue = true;
	
	public void start() {
		try {
			// 创建ServerSocket
			System.out.println("尝试创建连接...");
			ServerSocket serverSocket = new ServerSocket(23333);
			System.out.println("创建成功");

			// 侦听连接请求
			System.out.println("等待连接...");
			Socket socket = serverSocket.accept();
			System.out.println("连接成功");

			// 获得InputStream
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 获得OutputStream
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			Scanner sc = new Scanner(System.in);

			InputHandler thread = new InputHandler(input, this);
			thread.start();

			String serverMsg;

			while (shouldContinue) {
				serverMsg = sc.nextLine();
				System.out.println("发出消息:" + serverMsg);

				output.write(serverMsg + "\r");
				output.flush();

				if ("bye".equals(serverMsg)) {
					shouldContinue = false;
				}
			}

			thread.shouldContinue = false;
			sc.close();
			serverSocket.close();
			System.out.println("已退出...");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	@Override
	public void setContinue(boolean shouldContinue) {
		this.shouldContinue = shouldContinue;
	}
}
