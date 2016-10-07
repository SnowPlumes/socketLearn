package learnsocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ShouldContinue{
	boolean shouldContinue = true;
	
	public void start() {
		try {
			// 创建Socket
			System.out.println("尝试建立连接...");
			Socket socket = new Socket("localhost", 23333);
			System.out.println("连接成功");

			// 获得InputStream
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 获得OutputStream
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			Scanner sc = new Scanner(System.in);
			InputHandler thread = new InputHandler(input, this);
			thread.start();

			String clientMsg;

			while (shouldContinue) {
				clientMsg = sc.nextLine();
				System.out.println("发出消息:" + clientMsg);
				output.write(clientMsg + "\r");
				output.flush();

				if ("bye".equals(clientMsg)) {
					shouldContinue = false;
				}
			}

			thread.shouldContinue = false;
			sc.close();
			socket.close();
			System.out.println("已退出...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}

	@Override
	public void setContinue(boolean shouldContinue) {
		this.shouldContinue = shouldContinue;
	}
}
