import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Trabalho01_02_Josiane_RafaelZelone {
	public static void main(String[] args) {
		int aux = 0;
		String nome;
		int tempotot;
		String ciclo;
		int quant = 0;
		int menor = 0;
		int espera = 0;
		String split[];
		int sjfcont = 0;
		int correto = 1;
		int entrousjf = 0;
		int quantciclos = 0;
		int esperamedio = 0;
		int sjfconttotal = 0;
		int turnaromedio = 0;
		int sjfmediatotalespera = 0;
		int sjfmediatotalprocessamento = 0;

		System.out.println("________________________PROCESSOS_________________________");

		while (correto != 0) {
			System.out.print("\nQuantos processos serao adicionados: ");
			Scanner leia = new Scanner(System.in);
			try {
				quant = leia.nextInt();

				String[] tempociclo = new String[quant];
				String[] nomeprocesso = new String[quant];
				int[] tempoesperasjf = new int[quant];
				int[] tempototal = new int[quant];
				int[] quantmax = new int[quant];
				int[] quantfeito = new int[quant];

				for (int i = 0; i < quant; i++) {
					System.out.print("\nDigite o nome do processo: ");
					nome = leia.next();
					System.out.print("Digite o tempo total do processo: ");
					tempotot = leia.nextInt();
					System.out.print("Digite os tempos do ciclos (1-9,1-9): ");
					ciclo = leia.next();

					tempociclo[i] = ciclo;
					tempototal[i] = tempotot;
					nomeprocesso[i] = nome;

				}
				System.out.println("____________________________FCFS_____________________________");
				int[] esperafcfs = new int[quant];
				int[] turnaro = new int[quant];

				// tempo de espera de cada um
				for (int i = 0; i < quant; i++) {
					esperafcfs[i] = espera;
					espera += tempototal[i];
				}

				// media de tempo de espera
				for (int i = 0; i < quant; i++) {
					esperamedio += esperafcfs[i];
				}

				// tempo de turnaroud cada um
				for (int i = 0; i < quant; i++) {
					turnaro[i] = esperafcfs[i] + tempototal[i];
				}

				// media de turnaround
				for (int i = 0; i < quant; i++) {
					turnaromedio += turnaro[i];
				}

				for (int i = 0; i < quant; i++) {
					System.out.println("\nNome: " + nomeprocesso[i]);
					System.out.println("Tempo de espera: " + esperafcfs[i]);
					System.out.println("Tempo total de processamento: " + turnaro[i]);
				}

				System.out.println("\nTempo de espera medio: " + esperamedio / quant);
				System.out.println("Tempo medio de processamento: " + turnaromedio / quant);

				System.out.println("\n____________________________SJF______________________________");
				for (int i = 0; i < quant; i++) {
					quantciclos += tempociclo[i].length();
				}

				String[] sjfnome = new String[quantciclos];
				String[] sjftempo = new String[quantciclos];
				String[] nometotalsjf = new String[quantciclos];
				int[] sjftempoprocessos = new int[quantciclos];
				int[] sjftempotoltal = new int[quant];

				for (int i = 0; i < quant; i++) {
					split = tempociclo[i].split(",");

					for (int j = 0; j < split.length; j++) {
						sjfnome[sjfcont] = nomeprocesso[i];
						sjftempo[sjfcont] = split[j];
						sjfcont += 1;
					}
				}
				for (int i = 0; i < sjfcont; i++) {
					if (sjftempo[i].equals(",")) {
					} else {
						sjftempoprocessos[sjfconttotal] = Integer.parseInt(sjftempo[i]);
						nometotalsjf[sjfconttotal] = sjfnome[i];
						sjfconttotal += 1;
					}
				}

				aux = 1;
				for (int i = 0; i < sjfconttotal; i++) {
					for (int j = 0; j < quant; j++) {
						if (nometotalsjf[i].equals(nomeprocesso[j])) {
							quantmax[j] += 1;
							j = quant;
						}
					}
				}
				while (menor != sjfconttotal) {
					for (int i = 0; i < sjfconttotal; i++) {
						if (sjftempoprocessos[i] == aux) {
							System.out.print(sjftempoprocessos[i] + "-");
							System.out.print(nometotalsjf[i]);
							System.out.print(" ");
							
							menor += 1;
							entrousjf = 1;
							for (int j = 0; j < quant; j++) {
								if (nometotalsjf[i].equals(nomeprocesso[j])) {
									quantfeito[j] += 1;
								} else if (quantfeito[j] < quantmax[j]) {
									tempoesperasjf[j] += sjftempoprocessos[i];
								}
							}
							if (entrousjf == 1 && i == sjfconttotal - 1) {
								aux += 1;
							}
						} else if (entrousjf == 1 && i == sjfconttotal - 1) {
							aux += 1;
						}
					}
					if (entrousjf != 1) {
						aux += 1;
					}
				}

				for (int i = 0; i < quant; i++) {
					sjftempotoltal[i] = tempoesperasjf[i] + tempototal[i];
				}
				for (int i = 0; i < quant; i++) {
					sjfmediatotalespera += tempoesperasjf[i];
					sjfmediatotalprocessamento += tempototal[i];
				}

				for (int i = 0; i < quant; i++) {
					System.out.println("\nNome: " + nomeprocesso[i]);
					System.out.println("Tempo de espera: " + tempoesperasjf[i]);
					System.out.println("Tempo de processamento: " + tempototal[i]);
					System.out.println("Tempo total de processamento: " + sjftempotoltal[i]);
				}

				System.out.println("\nTempo de espera medio: " + sjfmediatotalespera / quant);
				System.out.println("Tempo medio de processamento: " + sjfmediatotalprocessamento / quant);
				correto = 0;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
}