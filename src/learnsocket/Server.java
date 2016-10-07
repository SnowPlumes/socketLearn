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
			// ����ServerSocket
			System.out.println("���Դ�������...");
			ServerSocket serverSocket = new ServerSocket(23333);
			System.out.println("�����ɹ�");

			// ������������
			System.out.println("�ȴ�����...");
			Socket socket = serverSocket.accept();
			System.out.println("���ӳɹ�");

			// ���InputStream
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// ���OutputStream
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			Scanner sc = new Scanner(System.in);

			InputHandler thread = new InputHandler(input, this);
			thread.start();

			String serverMsg;

			while (shouldContinue) {
				serverMsg = sc.nextLine();
				System.out.println("������Ϣ:" + serverMsg);

				output.write(serverMsg + "\r");
				output.flush();

				if ("bye".equals(serverMsg)) {
					shouldContinue = false;
				}
			}

			thread.shouldContinue = false;
			sc.close();
			serverSocket.close();
			System.out.println("���˳�...");
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
