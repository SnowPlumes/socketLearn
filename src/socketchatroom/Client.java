package socketchatroom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	boolean shouldContinue = true;

	@SuppressWarnings("resource")
	public void start() {
		try {
			// ����Socket
			System.out.println("���Խ�������...");
			Socket socket = new Socket("192.168.0.100", 2233);
			System.out.println("���ӳɹ�");

			// ���InputStream
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// ���OutputStream
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			Scanner sc = new Scanner(System.in);

			String clientMsg;
			InputHandler inputHandler = new InputHandler(input);
			inputHandler.start();

			while (shouldContinue) {
				clientMsg = sc.nextLine();
				output.write(clientMsg + "\r");
				output.flush();
			}

			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}

}
