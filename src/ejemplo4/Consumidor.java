package ejemplo4;

public class Consumidor extends Thread {

	private Almacen almacen;

	public Consumidor(Almacen almacen) {
		this.almacen = almacen;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				String producto = this.almacen.consumir();
				Thread.sleep(1500);
				System.out.println("Consumido el producto: " + producto);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
