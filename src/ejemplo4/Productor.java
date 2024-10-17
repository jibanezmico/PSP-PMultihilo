package ejemplo4;

public class Productor extends Thread {

	private Almacen almacen;

	public Productor(Almacen almacen) {
		this.almacen = almacen;
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i < 10; i++) {
				String producto = "Producto " + i;
				this.almacen.producir(producto);
				Thread.sleep(1000);
				System.out.println("Producido producto: " + producto);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
