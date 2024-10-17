package ejemplo4;

import java.util.LinkedList;

public class Almacen {
	
	private LinkedList<String> productosAlmacenados = new LinkedList<String>();
	private static int CAPACIDAD = 10;
	
	public synchronized String consumir() throws InterruptedException {
		if(productosAlmacenados.isEmpty()) {
			wait();
		}
		String producto = productosAlmacenados.removeFirst();
		notify();
		return producto;
	}
	
	public synchronized void producir(String producto) throws InterruptedException {
		if(productosAlmacenados.size() >= CAPACIDAD) {
			wait();
		}
		notify();
		productosAlmacenados.add(producto);
	}

}
