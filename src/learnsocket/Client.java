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
			// ����Socket
			System.out.println("���Խ�������...");
			Socket socket = new Socket("localhost", 23333);
			System.out.println("���ӳɹ�");

			// ���InputStream
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// ���OutputStream
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			Scanner sc = new Scanner(System.in);
			InputHandler thread = new InputHandler(input, this);
			thread.start();

			String clientMsg;

			while (shouldContinue) {
				clientMsg = sc.nextLine();
				System.out.println("������Ϣ:" + clientMsg);
				output.write(clientMsg + "\r");
				output.flush();

				if ("bye".equals(clientMsg)) {
					shouldContinue = false;
				}
			}

			thread.shouldContinue = false;
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

	@Override
	public void setContinue(boolean shouldContinue) {
		this.shouldContinue = shouldContinue;
	}
}
