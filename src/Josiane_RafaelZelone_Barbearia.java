

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Josiane_RafaelZelone_Barbearia {
	static Semaphore cadeira = new Semaphore(1);
	static String[] status;
	static String statusBarbeiro;
	static Random valor = new Random();
	static int[] cadeiras = new int[5];
	static int cont = 0;
	static int num;

	public static void main(String[] args) {
		status = new String[5];

		for (int i = 0; i < 5; i++) {
			status[i] = "Livre";
		}

		System.out.println(
				"_________________________________________________  BARBEARIA  _________________________________________________ \n");
		Thread barbeiro = new Barbeiro();
		barbeiro.start();
		Thread cliente = new Cliente();
		cliente.start();
	}
}

class Barbeiro extends Thread {
	Random rand = new Random();

	public Barbeiro() {
		Josiane_RafaelZelone_Barbearia.statusBarbeiro = "Dormindo zZz";
	}

	@Override
	public void run() {

		while (true) {

			if (Josiane_RafaelZelone_Barbearia.cadeira.availablePermits() == 1
					&& Josiane_RafaelZelone_Barbearia.cont == 0) {
				imprimir();
				Josiane_RafaelZelone_Barbearia.statusBarbeiro = "Dormindo zZz";

				try {
					sleep(rand.nextInt((2100 - 1000) + 1) + 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				Josiane_RafaelZelone_Barbearia.statusBarbeiro = "Cortando";
				cortarCabelo();
				Josiane_RafaelZelone_Barbearia.cadeira.release();
			}
		}
	}

	public synchronized void cortarCabelo() {
		try {
			Josiane_RafaelZelone_Barbearia.cadeira.acquire();
			Josiane_RafaelZelone_Barbearia.status[0] = "Livre";
			for (int i = 0; i < 4; i++) {
				Josiane_RafaelZelone_Barbearia.status[i] = Josiane_RafaelZelone_Barbearia.status[i + 1];
			}
			Josiane_RafaelZelone_Barbearia.status[4] = "Livre";
			Josiane_RafaelZelone_Barbearia.cont -= 1;
			if (Josiane_RafaelZelone_Barbearia.cont < 0) {
				Josiane_RafaelZelone_Barbearia.cont = 0;
			}
			imprimircliente();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notify();
		try {
			sleep(rand.nextInt((2600 - 1000) + 1) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void imprimir() {
		System.out.println("                 | Numero de clientes: " + (Josiane_RafaelZelone_Barbearia.cont + 1) + " | "
				+ Josiane_RafaelZelone_Barbearia.status[0] + " " + Josiane_RafaelZelone_Barbearia.status[1] + " "
				+ Josiane_RafaelZelone_Barbearia.status[2] + " " + Josiane_RafaelZelone_Barbearia.status[3] + " "
				+ Josiane_RafaelZelone_Barbearia.status[4] + " | Barbeiro: "
				+ Josiane_RafaelZelone_Barbearia.statusBarbeiro);
	}

	public void imprimircliente() {
		System.out.println("                 | Numero de clientes: " + (Josiane_RafaelZelone_Barbearia.cont + 1) + " | "
				+ Josiane_RafaelZelone_Barbearia.status[0] + " " + Josiane_RafaelZelone_Barbearia.status[1] + " "
				+ Josiane_RafaelZelone_Barbearia.status[2] + " " + Josiane_RafaelZelone_Barbearia.status[3] + " "
				+ Josiane_RafaelZelone_Barbearia.status[4] + " | Barbeiro: "
				+ Josiane_RafaelZelone_Barbearia.statusBarbeiro + " -> Cliente saindo ");

	}
}

class Cliente extends Thread {
	Random rand = new Random();

	public Cliente() {

	}

	@Override
	public void run() {

		while (true) {

			if (Josiane_RafaelZelone_Barbearia.statusBarbeiro.equals("Dormindo zZz")) {
				imprimircliente();
				Josiane_RafaelZelone_Barbearia.cadeira.release();
				Josiane_RafaelZelone_Barbearia.statusBarbeiro = "Cortando";
				imprimir();
				Josiane_RafaelZelone_Barbearia.status[0] = "Livre";
				try {
					Josiane_RafaelZelone_Barbearia.cadeira.acquire();
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (Josiane_RafaelZelone_Barbearia.cont < 5) {
				if (Josiane_RafaelZelone_Barbearia.cont <= 4) {
					imprimircliente();
					Josiane_RafaelZelone_Barbearia.status[Josiane_RafaelZelone_Barbearia.cont] = "Ocupado";
					Josiane_RafaelZelone_Barbearia.cont = Josiane_RafaelZelone_Barbearia.cont + 1;

				} else {
					imprimircliente();
					System.out.println("Saiu por falta de cadeira!");
				}
			} else {
				System.out.println("Saiu por falta de cadeira!");
			}
			try {
				imprimir();
				sleep(rand.nextInt((2100 - 1000) + 1) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void imprimir() {
		System.out.println("                 | Numero de clientes: " + (Josiane_RafaelZelone_Barbearia.cont + 1) + " | "
				+ Josiane_RafaelZelone_Barbearia.status[0] + " " + Josiane_RafaelZelone_Barbearia.status[1] + " "
				+ Josiane_RafaelZelone_Barbearia.status[2] + " " + Josiane_RafaelZelone_Barbearia.status[3] + " "
				+ Josiane_RafaelZelone_Barbearia.status[4] + " | Barbeiro: "
				+ Josiane_RafaelZelone_Barbearia.statusBarbeiro);
	}

	public void imprimircliente() {
		System.out.println("Cliente entrando | Numero de clientes: " + (Josiane_RafaelZelone_Barbearia.cont + 1) + " | "
				+ Josiane_RafaelZelone_Barbearia.status[0] + " " + Josiane_RafaelZelone_Barbearia.status[1] + " "
				+ Josiane_RafaelZelone_Barbearia.status[2] + " " + Josiane_RafaelZelone_Barbearia.status[3] + " "
				+ Josiane_RafaelZelone_Barbearia.status[4] + " | Barbeiro: "
				+ Josiane_RafaelZelone_Barbearia.statusBarbeiro);
	}
}
