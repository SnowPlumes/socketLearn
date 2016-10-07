package socketchatroom;

import java.util.ArrayList;
import java.util.List;

public class SocketFactory {

	private static final SocketFactory factory = new SocketFactory();
	private List<SocketHandler> list = new ArrayList<>();

	private SocketFactory() {
		super();
	}

	public static SocketFactory getInstance() {
		return factory;
	}

	public void addList(SocketHandler handler) {
		list.add(handler);
	}

	public List<SocketHandler> getList() {
		return list;
	}

	public void remove(SocketHandler handler) {
		list.remove(handler);
	}
}
