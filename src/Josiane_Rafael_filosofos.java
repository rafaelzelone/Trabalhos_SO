
import java.util.concurrent.Semaphore;

public class Josiane_Rafael_filosofos {
	static String[] nomes;
	static String[] garfos;
	static String[] status;
	static int[] filosofos;
	static Filosofos[] filo;
	static Semaphore[] semaforos = new Semaphore[5];

	public static void main(String[] args) {
		nomes = new String[5];
		filosofos = new int[5];
		status = new String[5];
		filo = new Filosofos[5];

		// indica o nome de cada filosofo
		nomes[0] = "Socrates";
		nomes[1] = "Platao";
		nomes[2] = "Ari";
		nomes[3] = "Tales";
		nomes[4] = "Parmenides";

		System.out.println(
				"__________________________________________O JANTAR DOS FILOSOFOS_________________________________________\n");

		// cria os semaforos
		for (int i = 0; i < 5; i++) {
			semaforos[i] = new Semaphore(1);
		}

		// indica status como pensando
		status[0] = "Pensando";
		status[1] = "Pensando";
		status[2] = "Pensando";
		status[3] = "Pensando";
		status[4] = "Pensando";

		// indica filosofos como 0 = pensando
		filosofos[0] = 0;
		filosofos[1] = 0;
		filosofos[2] = 0;
		filosofos[3] = 0;
		filosofos[4] = 0;

		// instancia a thread
		for (int i = 0; i < 5; i++) {
			filo[i] = new Filosofos(i);
		}

		// starta as threads
		for (int i = 0; i < 5; i++) {
			filo[i].start();
		}
	}
}

// thread de filosofos
class Filosofos extends Thread {
	int id;

	// construtor de filosofos
	public Filosofos(int id) {
		this.id = id;
	}

	// imprime e verifica o status para chamar o metodo correspondente
	@Override
	public void run() {
		imprimir();
		while (true) {

			if (Josiane_Rafael_filosofos.filosofos[this.id] == 0) {
				pensar();
			} else if (Josiane_Rafael_filosofos.filosofos[this.id] == 1) {
				faminto();
			}
		}
	}

	// sincroniza semaforo para pegar garfo esquerdo
	public synchronized void pegarGarfoEsquerda() {
		try {
			if (id == 4) {
				Josiane_Rafael_filosofos.semaforos[0].acquire();
				notify();
			} else {
				Josiane_Rafael_filosofos.semaforos[id + 1].acquire();
				notify();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// sincroniza semaforo para pegar garfo direito
	public synchronized void pegarGarfoDireita() {
		try {
			// puxa um semaforo
			Josiane_Rafael_filosofos.semaforos[id].acquire();
			notify();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// tenta pegar os garfos, se conseguir muda para comendo, imprime, espera um seg
	// e chama o devolverGarfo
	public void comer() {

		pegarGarfoDireita();
		pegarGarfoEsquerda();

		// verifica se o status do filosofo e 2 e muda para comendo
		try {
			Josiane_Rafael_filosofos.filosofos[id] = 2;
			Josiane_Rafael_filosofos.status[id] = "Comendo";
			// imprime resultado e espera um seg para executar o devolverGarfo
			imprimir();
			sleep(1000);
			devolverGarfo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// devolve garfo, se o filosofo for 4, se nao a posicao dele e mais 1
	// libera o semaforo e muda status para pensando
	public void devolverGarfo() {

		if (id == 4) {

			Josiane_Rafael_filosofos.filosofos[id] = 0;
			Josiane_Rafael_filosofos.semaforos[id].release();
			Josiane_Rafael_filosofos.semaforos[0].release();
			Josiane_Rafael_filosofos.status[this.id] = "Pensando";

		} else {

			Josiane_Rafael_filosofos.filosofos[id] = 0;
			Josiane_Rafael_filosofos.semaforos[id].release();
			Josiane_Rafael_filosofos.semaforos[id + 1].release();
			Josiane_Rafael_filosofos.status[this.id] = "Pensando";
		}
	}

	// espera um segundo para mudar o status para faminto e depois imprime
	public void pensar() {
		try {

			sleep(1000);
			Josiane_Rafael_filosofos.filosofos[id] = 1;
			Josiane_Rafael_filosofos.status[id] = "Faminto";
			imprimir();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// executa o comer
	public void faminto() {
		comer();
	}

	// imprime cada troca de status
	public void imprimir() {
		System.out.println(" | " + Josiane_Rafael_filosofos.nomes[0] + ": " + Josiane_Rafael_filosofos.status[0] + " | " + Josiane_Rafael_filosofos.nomes[1] + ":  "
				+ Josiane_Rafael_filosofos.status[1] + " | " + Josiane_Rafael_filosofos.nomes[2] + ": " + Josiane_Rafael_filosofos.status[2] + " | " + Josiane_Rafael_filosofos.nomes[3] + ": "
				+ Josiane_Rafael_filosofos.status[3] + " | " + Josiane_Rafael_filosofos.nomes[4] + ": " + Josiane_Rafael_filosofos.status[4] + " | \n");
	}
}
