


import java.util.Scanner;

public class Josiane_RafaelZelone_Trabalho_1 {
	public static void main(String[] args) {
		int num;
		int soma;
		int aux = 0;
		int quant = 0;
		long tempoth = 0;
		long start1 = 0;
		long elapsed1 =0;
		long elapsed2=0;
		long elapsed3 = 0;
		long valorfinal=0;
		boolean finalr = true; 

		while(finalr != false) {
		System.out.print("Digite o numero que sera processado: ");
		// leia para ler a variavel de entrada
		Scanner leia = new Scanner(System.in);
		try {
			
			num = leia.nextInt();

			// iniciacao do processamento sequencial
			
			System.out.println(
					"\n__________________________________ Processamento sequencial __________________________________");

			// fatorial do numero em loop ate chegar ao 1
			soma = num;
			start1 = System.currentTimeMillis();
			System.out.println("Fatorial de: " + num);
			if (num <= 12) {
			System.out.println("Iniciando fatoracao...");
			for (int i = num - 1; i >= 1; i--) {
				aux = soma * i;
				System.out.println("Fatorial: " + soma + " x " + i + " = " + aux);
				soma = aux;

			}
			if (num == 1) {
				System.out.println("Nao existe Fatorial de 1");
			}} else if ( num > 12) {
				System.out.println("Nao sera possivel fazer fatorial por motivos de poblemas de variaveis em java");
				System.out.println("Entao sera feito com 12 que e o numero maximo possivel");
				soma=12;
				for (int i = 11; i >= 1; i--) {
					aux = soma * i;
					System.out.println("Fatorial: " + soma + " x " + i + " = " + aux);
					soma = aux;

				}
			}
			elapsed1 = System.currentTimeMillis() - start1;
			System.out.println("Tempo de conclusao fatorial: " + elapsed1 + " ms");
			System.out.println();
			
			start1 = System.currentTimeMillis();
			System.out.println("\nTabuado do: " + num);
			System.out.println("Iniciando tabuada...");

			// tabuada ate o 10 do numero
			for (int j = 1; j <= 10; j++) {
				System.out.println("Tabuada:" + num + " x " + j + " = " + (num * j));

			}
			elapsed2 = System.currentTimeMillis() - start1;
			System.out.println("Tempo de conclusao Tabuada: " + elapsed2 + " ms");
			System.out.println();
			
			start1 = System.currentTimeMillis();
			System.out.println("\nNumeros primos de " + num + " a  0");
			System.out.println("Iniciando numeros primos...");

			// verifica se o numero e divisivel por 1 e por ele mesmo
			for (int i = 1; i <= num; i++) {
				for (int j = 1; j <= i; j++) {
					if (i % j == 0) {
						quant += 1;
					}
				}
				// se for 2 vezes divisiveis entre todos os numeros ele e primo e imprime
				if (quant == 2) {
					System.out.println("Primo: " + i + " e primo");
					quant = 0;
				} else {
					quant = 0;
				}
			}
			
			if (num == 1) {
				System.out.println("Nao existe Numero primos de 1 a 1");
			}
			// verifica o tempo que foi corrido desde o inicio desde o processo 
			 elapsed3 = System.currentTimeMillis() - start1;
			 
			 System.out.println("Tempo de conclusao Primos: " + elapsed3 + " ms");
				System.out.println();
			 
			 valorfinal = elapsed1+ elapsed2+ elapsed3;
			System.out.println("\nTempo sequencial final: " + valorfinal + " ms");

			// inicia o processamento em threads
			System.out.println(
					"\n__________________________________  Processamento com threads __________________________________");

			// chama o metodo que inicia e instancia as threads
			tempoth = new Josiane_RafaelZelone_Trabalho_1().executarthreads(num);

			// mostra a diferença de tempo corrida entre as duas fromas de processos
			
			if(tempoth > valorfinal) {
			System.out.println("\nDiferenca foi de:  " + (tempoth - valorfinal) + " ms para o processamento sequencial em difereca da threads");
			} else if (tempoth < valorfinal) {
				System.out.println("\nDiferenca foi de:  " + (valorfinal - tempoth) + " ms para o processamento de threads em difereca ao sequencial");
			}  else {
				System.out.println("O tempo de processamento foi igual para amos os processos");
			}
			
			finalr =false;

		
		} catch (Exception e) {
			System.out.println("Digite um valor valido");
		}}}
		

	// Criando threads
	public long executarthreads(int num) {
		long elapsed = 0;
		long start = System.currentTimeMillis();
		Thread primeirat = new CriarJavaThreads1( num);
		Thread segundat = new CriarJavaThreads2(num);
		Thread terThre = new CriarJavaThreads3( num);

		// Iniciando threads
		primeirat.start();
		segundat.start();
		terThre.start();

		// Fechando threads
		try {
			primeirat.join();
			segundat.join();
			terThre.join();
			elapsed = System.currentTimeMillis() - start;
			System.out.println("\nTempo das threads final: " + elapsed + " ms");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return elapsed;
	}
}

// classe que cria Thread1
class CriarJavaThreads1 extends Thread {	
	int num;
	int soma = 1;

	public CriarJavaThreads1( int num) {
		
		this.num = num;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		int aux = 0;
		soma = num;
		
		if (num <= 12) {
			System.out.println("Fatorial de: " + num);
		System.out.println("Iniciando fatoracao...");
		for (int i = num - 1; i >= 1; i--) {
			aux = soma * i;
			System.out.println("Fatorial: " + soma + " x " + i + " = " + aux);
			soma = aux;

		}
		if (num == 1) {
			System.out.println("Nao existe Fatorial de 1");
		}} else if ( num > 12) {
			System.out.println("Nao sera possivel fazer fatorial por motivos de poblemas de variaveis em java");
			System.out.println("Entao sera feito com 12 que e o numero maximo possivel");
			System.out.println("Iniciando fatoracao...");
			soma=12;
			for (int i = 11; i >= 1; i--) {
				aux = soma * i;
				System.out.println("Fatorial: " + soma + " x " + i + " = " + aux);
				soma = aux;

			}
		}
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Tempo de conclusao fatorial: " + elapsed + " ms");
	}
}

//classe que cria Thread2
class CriarJavaThreads2 extends Thread {

	int num;

	public CriarJavaThreads2( int num) {
		
		this.num = num;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		System.out.println("Tabuada do: " + num);
		System.out.println("Iniciando tabuada...");

		for (int j = 1; j <= 10; j++) {
			System.out.println("Tabuada: " + num + " x " + j + " = " + (num * j));

		}
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Tempo de conclusao tabuada: " + elapsed + " ms");
	}
}

//classe que cria Thread3
class CriarJavaThreads3 extends Thread {
	
	int num;

	public CriarJavaThreads3( int num) {
	
		this.num = num;
	}

	public long retorno() {
		return num;

	}

	@Override
	public void run() {
		int quant = 0;
		long start = System.currentTimeMillis();
		System.out.println("\nNumeros primos de " + num + " a  0");
		System.out.println("Iniciando primos...");

		for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= i; j++) {
				if (i % j == 0) {
					quant += 1;
				}
			}
			if (quant == 2) {
				System.out.println("Primo: " + i + " e primo");
				quant = 0;
			} else {
				quant = 0;
			}
		}
		if (num == 1) {
			System.out.println("Nao existe Numero primos de 1 a 1");
		}
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("Tempo de conclusao primo: " + elapsed + " ms");

	}
}