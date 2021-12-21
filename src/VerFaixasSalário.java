import java.util.Scanner;

public class VerFaixasSal�rio {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);
		String invalido = "Valor inv�lido. O sal�rio deve ser maior ou igual a zero.";
		int tamanhoVetorSalario = 25; // tamanho do vetor sal�rios
		double[] salario = new double[tamanhoVetorSalario];
		int posicaoVetor = 0;
		int contador = 1;
		double delta; // usar para calcular o tamanho de cada faixa.
		int qtdFaixas = 10; // informa��o do exerc�cio
		int[] faixas = new int[qtdFaixas]; // armazenar quantidade por faixas.

		// ==============Pegar sal�rios com usu�rio
		while (posicaoVetor < tamanhoVetorSalario) {

			System.out.printf("Informe o %d� sal�rio: ", contador);
			salario[posicaoVetor] = leia.nextDouble();
			contador++;
			if (salario[posicaoVetor] < 0) {
				System.out.println(invalido); // Mostra mensagem que valor � inv�lido.
				contador = contador - 1; // retorna para que seja informado o valor novamente na mesma posi��o.
				posicaoVetor = posicaoVetor - 1; // retorna para que seja informado o valor novamente na mesma posi��o.
			}
			posicaoVetor++; // la�o While
		} // fim while

		// =============Calcula menor sal�rio
		posicaoVetor = 0;
		double menorSalario = salario[0];
		for (int i = 0; i < salario.length; i++) {
			if (menorSalario > salario[posicaoVetor]) { // vai comparar se o valor do vetor analisado � menor que o
														// valor armazenado na variv�vel.
				menorSalario = salario[posicaoVetor]; // se menor, este ser� o novo valor da vari�vel.

			} // fim if

			posicaoVetor++; // la�o for

		} // fim do for

		// =============Calcula maior sal�rio
		posicaoVetor = 0;
		double maiorSalario = salario[0];
		for (int i = 0; i < salario.length; i++) {
			if (maiorSalario < salario[posicaoVetor]) { // vai comparar se o valor do vetor analisado � maior que o
														// valor armazenado na variv�vel.
				maiorSalario = salario[posicaoVetor]; // se maior, este ser� o novo valor da vari�vel.

			} // fim if

			posicaoVetor++; // la�o for

		} // fim do for

		// =============Calcula varia��o sal�rios para definir as faixas
		delta = (maiorSalario - menorSalario) / qtdFaixas; // f�rmula para calcular delta (informa��o do exerc�cio).

		// =============Define as faixas
		double fI[] = new double[tamanhoVetorSalario]; // vetor para guardar o valor inicial de cada faixa.
		double fF[] = new double[tamanhoVetorSalario]; // vetor para guardar o valor final de cada faixa.

		posicaoVetor = 0;
		double menorRegistroDeSalario = menorSalario;
		while (posicaoVetor < tamanhoVetorSalario) { // posic�o do vetor for menor que o tamanho do vetor.

			fI[posicaoVetor] = menorRegistroDeSalario;
			if (posicaoVetor == 0) { // quando for a primeira faixa, o valor final ser� o menor sal�rio + delta.
				fF[posicaoVetor] = menorSalario + delta;
			} // fim if
			else if (posicaoVetor == 9) { // quando for a �ltima faixa, o valor final ser� o maior sal�rio.
				fF[posicaoVetor] = maiorSalario;
			} // fim else if
			else { // regra geral (aplicar quando n�o for a primeira nem a �ltima faixa.
				fF[posicaoVetor] = menorSalario + (delta * (posicaoVetor + 1));
			} // fim else

			posicaoVetor++; // la�o while
			menorRegistroDeSalario = menorSalario + delta * (posicaoVetor); // autalizar o menor sal�rio de cada faixa.

		} // fim while

		// =============Contar quantos sal�rios em cada faixa
		System.out.println();
		System.out.println("===IMPRIMINDO SAL�RIO===");
		for (int i = 0; i < salario.length; i++) {

			System.out.printf("Sal�rio vetor %d \tR$ %.2f\n", i, salario[i]);

		} // fim for

		System.out.println();
		System.out.println("===AN�LISE SAL�RIOS===\n");

		// =============Atribui��o na quantidade de faixa
		for (int i = 0; i < qtdFaixas; i++) {
			for (int j = 0; j < salario.length; j++) {
				if (qtdFaixas - 1 > i) { // Esse -1 � pq o vetor come�a em 0. Precisei colocar para cair na condi��o da
											// �ltima faixa que inclui o valor do maior sal�rio.
					if (salario[j] >= fI[i] && salario[j] < fF[i]) {
						faixas[i]++;
					}
				} else { // apenas quando for a �ltima faixa. Aqui a faixa final precisa analisar se o
							// valor do sal�rio � menor ou IGUAL ao valor final da faixa.
					if (salario[j] >= fI[i] && salario[j] <= fF[i]) {
						faixas[i]++;
					}
				}
			}
		}

		// =============Exibe na tela o menor e o maior sal�rio, e delta.
		System.out.printf("Menor sal�rio R$ %10.2f\n", menorSalario);
		System.out.printf("Maior sal�rio R$ %10.2f\n", maiorSalario);
		System.out.println("O valor de delta � R$ " + delta);
		System.out.println(); // apenas para saltar uma linha.

		// =============Imprime na tela as faixas de sal�rio e a quantidade em cada
		// faixa.
		int contar = 1;
		for (int i = 0; i < qtdFaixas; i++) {

			System.out.printf("%d� faixa \tDe R$ %10.2f at� R$ %10.2f cont�m %d sal�rios\n", contar, fI[i], fF[i],
					faixas[i]);
			contar++;

		} // fim for

		leia.close();

	} // fim main

} // fim class