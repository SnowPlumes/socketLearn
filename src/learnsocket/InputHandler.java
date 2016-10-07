package learnsocket;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler extends Thread {
	BufferedReader reader;
	ShouldContinue mainContinue;
	public boolean shouldContinue = true;

	public InputHandler(BufferedReader reader, ShouldContinue mainContinue) {
		super();
		this.reader = reader;
		this.mainContinue = mainContinue;
	}

	@Override
	public void run() {
		while (shouldContinue) {
			try {
				String msg = reader.readLine();
				if ("bye".equals(msg)){
					shouldContinue = false;
					mainContinue.setContinue(false);
				}
				System.out.println("收到消息：" + msg);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
