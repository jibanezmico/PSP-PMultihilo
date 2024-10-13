package ejemplo1;
import java.util.LinkedList;

class Almacen {
    private LinkedList<Integer> productos = new LinkedList<>();
    private final int CAPACIDAD = 5;

    public synchronized void producir(int producto) throws InterruptedException {
        while (productos.size() == CAPACIDAD) {
            wait(); // Esperar si el almacén está lleno
        }
        productos.add(producto);
        System.out.println("Producido: " + producto);
        notify(); // Notificar a los consumidores
    }

    public synchronized int consumir() throws InterruptedException {
        while (productos.isEmpty()) {
            wait(); // Esperar si el almacén está vacío
        }
        int producto = productos.removeFirst();
        System.out.println("Consumido: " + producto);
        notify(); // Notificar a los productores
        return producto;
    }
}

class Productor extends Thread {
    private Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                almacen.producir(i);
                Thread.sleep(100); // Simular tiempo de producción
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumidor extends Thread {
    private Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                almacen.consumir();
                Thread.sleep(150); // Simular tiempo de consumo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        new Productor(almacen).start();
        new Consumidor(almacen).start();
    }
}