import java.util.Scanner;

public class Trabalho02_02_Josiane_RafaelZelone {
	static int[] guardado = new int[500];
	static String[] nome = new String[500];
	static String[] cor = new String[500];
	static int quant = 1;

	public static void main(String[] args) {
		Trabalho02_02_Josiane_RafaelZelone ops = new Trabalho02_02_Josiane_RafaelZelone();
		int op = 1;

		for (int i = 0; i < 500; i++) {
			guardado[i] = 0;
		}
		for (int i = 0; i < 500; i++) {
			nome[i] = " ";
		}
		for (int i = 0; i < 500; i++) {
			if (i == 0) {
				cor[i] = "[ L ]";
			} else {
				cor[i] = "[   ]";
			}
		}

		while (op != 0) {
			Scanner leia = new Scanner(System.in);
			try {
				ops.imprimir();

				System.out.println(
						"\n___________Menu______________________________________________________________________________________________________________");
				System.out.println("1 - Adicionar arquivo");
				System.out.println("2 - Excluir arquivo");
				System.out.println("0 - Sair");
				System.out.print("Digite a opcao: ");
				op = leia.nextInt();

				switch (op) {
				case 1:
					System.out.println("Digite o nome do arquivo: ");
					String auxnome = leia.next();

					String[] seppalavra;
					int igual = 0;

					if (quant > 1) {

						for (int j = 0; j < quant - 1; j++) {
							seppalavra = nome[j].split(" ");
							if (seppalavra[0].equals(auxnome)) {
								igual = 1;
								j = quant;
							}
						}
					}
					if (igual == 0) {

						for (int i = 0; i < quant; i++) {
							if (nome[i].equals(" ")) {
								nome[i] = auxnome;
								nome[i] += " " + quant;
								quant += 1;
								i = 500;
							}
						}

						System.out.println("Digite o tamanho do arquivo: ");
						int tamanho = leia.nextInt();

						if ((tamanho % 512) == 0) {
							ops.adicionar((tamanho / 512), quant - 1);

						} else {
							ops.adicionar((int) Math.round((tamanho / 512) + 0.5), quant - 1);

						}
					} else {
						System.out.println("Arquivo com mesmo nome");
					}
					break;
				case 2:
					System.out.println("Qual o nome do processo que deseja excluir: ");
					auxnome = leia.next();
					ops.excluir(auxnome, quant);
					break;
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println("Opcao invalida");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void adicionar(int qttespacos, int numprocesso) {
		if (qttespacos == 0) {
			qttespacos = 1;
		}
		int cont = 0;

		for (int i = 0; i < qttespacos + 1; i++) {

			for (int j = 0; j < 500; j++) {
				if (cont == qttespacos) {
					for (int j2 = 0; j2 < 500; j2++) {
						if (guardado[j2] == 0) {
							cor[j2] = "[ L ]";
							j=500;
							j2=500;
							i=qttespacos+1;
						}
					}
					
				} else {
					if (guardado[j] == 0) {
						cont += 1;
						guardado[j] = numprocesso;
						if (i == 0 && qttespacos > 1 ) {
							cor[j] = "[" + numprocesso + "-C]";
						} else if (i == qttespacos - 1 && qttespacos > 1 ) {
							cor[j] = "[" + numprocesso + "-F]";
						} else if (qttespacos == 1 ) {
							cor[j] = "[" + numprocesso + "-U]";
						} else if ( cont <= qttespacos) {
							cor[j] = "[" + numprocesso + "-A]";
						}
						j=500;
					}
				}

			}
		}
	}

	public void excluir(String nom, int quant) {
		String[] seppalavra;
		int excluir = 0;
		int cont = 0;
		for (int j = 0; j < quant; j++) {
			seppalavra = nome[j].split(" ");
			if (seppalavra[0].equals(nom)) {
				excluir = Integer.parseInt(seppalavra[1]);
				j = quant;
			}
		}
		for (int i = 0; i < 500; i++) {
			if (guardado[i] == excluir) {
				if (cont == 0) {
					cont += 1;
					guardado[i] = 0;
					cor[i] = "[ L ]";
					for (int j = i+1; j < 500; j++) {
						if(cor[j].equals("[ L ]")) {
							cor[j]="[   ]";
						}
						
					}
				} else {
					cont += 1;
					guardado[i] = 0;
					cor[i] = "[   ]";
				}
			}
		}
	}

	public void imprimir() {
		System.out.println(
				"\n______________________________________________________Nome num arquivos______________________________________________________");

		for (int i = 0; i < quant - 1; i++) {
			System.out.println(nome[i]);
		}
		System.out.println("");

		int qttimprime = 0;
		for (int i = 0; i < 500; i++) {
			if (qttimprime < 24) {
				System.out.print(cor[i]);
				qttimprime += 1;
			} else {
				System.out.println(cor[i]);
				qttimprime = 0;
			}
		}
		System.out.print("\nA - Arquivo | ");
		System.out.print("C - Comeco de arquivo | ");
		System.out.print("F - Final de arquivo | ");
		System.out.print("L - Primeiro vazio | ");
		System.out.println("U - Unica particao");
	}
}
