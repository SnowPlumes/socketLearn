package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	boolean shouldContinue = true;
	
	public void start() {
		try {
			// ����Socket
			System.out.println("���Խ�������...");
			Socket socket = new Socket("localhost", 1234);
			System.out.println("���ӳɹ�");

			// ���InputStream
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// ���OutputStream
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			Scanner sc = new Scanner(System.in);

			String clientMsg;
			String serverMsg;
			
			while (shouldContinue) {
				clientMsg = sc.nextLine();
				System.out.println("������Ϣ:" + clientMsg);
				output.write(clientMsg + "\r");
				output.flush();

				serverMsg = input.readLine();
				System.out.println("�յ���Ϣ��" + serverMsg);
				
				if ("bye".equals(clientMsg)) {
					shouldContinue = false;
				}
			}

			sc.close();
			socket.close();
			System.out.println("���˳�...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}

}
