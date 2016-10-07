package socketchatroom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketHandler extends Thread {
	Socket socket;
	BufferedReader input;
	BufferedWriter output;
	SocketFactory sf = SocketFactory.getInstance();
	Logger logger = LogManager.getFormatterLogger(Server.class.getName());

	public SocketHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		boolean shouldContinue = true;
		String msg;
		BufferedReader input;

		ReadFile file = new ReadFile();
		file.start();
		
		while (shouldContinue) {
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				msg = input.readLine();
				System.out.println("收到了" + Thread.currentThread().getName() + "的消息：" + msg);

				for (SocketHandler handler : sf.getList()) {
					if (!handler.getName().equals(this.getName())) {
						output = new BufferedWriter(new OutputStreamWriter(handler.socket.getOutputStream()));

						for (String string : file.getList()) {
							if (msg.contains(string)) {
								msg = Thread.currentThread().getName() + "有敏感词汇 已被屏蔽";
								logger.warn(msg);
							}
						}
						
						output.write("收到了" + Thread.currentThread().getName() + "的消息：" + msg + "\r");
						output.flush();
					}
				}
			} catch (IOException e) {
				logger.info(Thread.currentThread().getName() + "已退出...");
				SocketFactory.getInstance().remove(this);
				shouldContinue = false;
				// e.printStackTrace();
			}
		}
	}
}
