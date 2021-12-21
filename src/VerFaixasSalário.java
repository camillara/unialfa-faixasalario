import java.util.Scanner;

public class VerFaixasSalário {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);
		String invalido = "Valor inválido. O salário deve ser maior ou igual a zero.";
		int tamanhoVetorSalario = 25; // tamanho do vetor salários
		double[] salario = new double[tamanhoVetorSalario];
		int posicaoVetor = 0;
		int contador = 1;
		double delta; // usar para calcular o tamanho de cada faixa.
		int qtdFaixas = 10; // informação do exercício
		int[] faixas = new int[qtdFaixas]; // armazenar quantidade por faixas.

		// ==============Pegar salários com usuário
		while (posicaoVetor < tamanhoVetorSalario) {

			System.out.printf("Informe o %dº salário: ", contador);
			salario[posicaoVetor] = leia.nextDouble();
			contador++;
			if (salario[posicaoVetor] < 0) {
				System.out.println(invalido); // Mostra mensagem que valor é inválido.
				contador = contador - 1; // retorna para que seja informado o valor novamente na mesma posição.
				posicaoVetor = posicaoVetor - 1; // retorna para que seja informado o valor novamente na mesma posição.
			}
			posicaoVetor++; // laço While
		} // fim while

		// =============Calcula menor salário
		posicaoVetor = 0;
		double menorSalario = salario[0];
		for (int i = 0; i < salario.length; i++) {
			if (menorSalario > salario[posicaoVetor]) { // vai comparar se o valor do vetor analisado é menor que o
														// valor armazenado na varivável.
				menorSalario = salario[posicaoVetor]; // se menor, este será o novo valor da variável.

			} // fim if

			posicaoVetor++; // laço for

		} // fim do for

		// =============Calcula maior salário
		posicaoVetor = 0;
		double maiorSalario = salario[0];
		for (int i = 0; i < salario.length; i++) {
			if (maiorSalario < salario[posicaoVetor]) { // vai comparar se o valor do vetor analisado é maior que o
														// valor armazenado na varivável.
				maiorSalario = salario[posicaoVetor]; // se maior, este será o novo valor da variável.

			} // fim if

			posicaoVetor++; // laço for

		} // fim do for

		// =============Calcula variação salários para definir as faixas
		delta = (maiorSalario - menorSalario) / qtdFaixas; // fórmula para calcular delta (informação do exercício).

		// =============Define as faixas
		double fI[] = new double[tamanhoVetorSalario]; // vetor para guardar o valor inicial de cada faixa.
		double fF[] = new double[tamanhoVetorSalario]; // vetor para guardar o valor final de cada faixa.

		posicaoVetor = 0;
		double menorRegistroDeSalario = menorSalario;
		while (posicaoVetor < tamanhoVetorSalario) { // posicão do vetor for menor que o tamanho do vetor.

			fI[posicaoVetor] = menorRegistroDeSalario;
			if (posicaoVetor == 0) { // quando for a primeira faixa, o valor final será o menor salário + delta.
				fF[posicaoVetor] = menorSalario + delta;
			} // fim if
			else if (posicaoVetor == 9) { // quando for a última faixa, o valor final será o maior salário.
				fF[posicaoVetor] = maiorSalario;
			} // fim else if
			else { // regra geral (aplicar quando não for a primeira nem a última faixa.
				fF[posicaoVetor] = menorSalario + (delta * (posicaoVetor + 1));
			} // fim else

			posicaoVetor++; // laço while
			menorRegistroDeSalario = menorSalario + delta * (posicaoVetor); // autalizar o menor salário de cada faixa.

		} // fim while

		// =============Contar quantos salários em cada faixa
		System.out.println();
		System.out.println("===IMPRIMINDO SALÁRIO===");
		for (int i = 0; i < salario.length; i++) {

			System.out.printf("Salário vetor %d \tR$ %.2f\n", i, salario[i]);

		} // fim for

		System.out.println();
		System.out.println("===ANÁLISE SALÁRIOS===\n");

		// =============Atribuição na quantidade de faixa
		for (int i = 0; i < qtdFaixas; i++) {
			for (int j = 0; j < salario.length; j++) {
				if (qtdFaixas - 1 > i) { // Esse -1 é pq o vetor começa em 0. Precisei colocar para cair na condição da
											// última faixa que inclui o valor do maior salário.
					if (salario[j] >= fI[i] && salario[j] < fF[i]) {
						faixas[i]++;
					}
				} else { // apenas quando for a última faixa. Aqui a faixa final precisa analisar se o
							// valor do salário é menor ou IGUAL ao valor final da faixa.
					if (salario[j] >= fI[i] && salario[j] <= fF[i]) {
						faixas[i]++;
					}
				}
			}
		}

		// =============Exibe na tela o menor e o maior salário, e delta.
		System.out.printf("Menor salário R$ %10.2f\n", menorSalario);
		System.out.printf("Maior salário R$ %10.2f\n", maiorSalario);
		System.out.println("O valor de delta é R$ " + delta);
		System.out.println(); // apenas para saltar uma linha.

		// =============Imprime na tela as faixas de salário e a quantidade em cada
		// faixa.
		int contar = 1;
		for (int i = 0; i < qtdFaixas; i++) {

			System.out.printf("%dª faixa \tDe R$ %10.2f até R$ %10.2f contém %d salários\n", contar, fI[i], fF[i],
					faixas[i]);
			contar++;

		} // fim for

		leia.close();

	} // fim main

} // fim class