package socketchatroom;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler extends Thread {

	BufferedReader reader;
	public boolean shouldContinue = true;

	public InputHandler(BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public void run() {
		while (shouldContinue) {
			try {
				String msg = reader.readLine();
				// if ("bye".equals(msg)) {
				// shouldCoutinue = false;
				// }
				System.out.println(msg);
			} catch (IOException e) {
				System.out.println("·þÎñÆ÷±ÀÁË...");
				shouldContinue = false;
				//e.printStackTrace();
			}
		}
	}
}
