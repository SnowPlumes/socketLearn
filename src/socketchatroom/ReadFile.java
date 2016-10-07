package socketchatroom;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile extends Thread {

	String fileName = "words.txt";
	List<String> list = new ArrayList<>();

	@Override
	public void run() {
		while (true) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String line = reader.readLine();

				while (line != null) {
					list.add(line);
					line = reader.readLine();
				}
				sleep(3000);
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public List<String> getList() {
		return list;
	}
}
