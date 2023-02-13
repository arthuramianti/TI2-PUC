package Exercicio01;
import java.util.*;

class SomarDoisNumeros {
	public static Scanner sc = new Scanner(System.in);
	
	private int primeiroNumero, segundoNumero;
	
	public void construct() {
		this.primeiroNumero = 0;
		this.segundoNumero = 0;
	}
	
	public int getPrimeiroNumero() {
		return primeiroNumero;
	}

	private void setPrimeiroNumero(int primeiroNumero) {
		this.primeiroNumero = primeiroNumero;
	}

	public int getSegundoNumero() {
		return segundoNumero;
	}

	private void setSegundoNumero(int segundoNumero) {
		this.segundoNumero = segundoNumero;
	}
	
	public static void main(String args[]) {
		SomarDoisNumeros somarDoisNumeros = new SomarDoisNumeros();
		int somaDosNumeros;
		
		System.out.println("Digite o primeiro número: ");
		somarDoisNumeros.setPrimeiroNumero(sc.nextInt());
		System.out.println("Digite o segundo número: ");
		somarDoisNumeros.setSegundoNumero(sc.nextInt());
		
		somaDosNumeros = somarDoisNumeros.getPrimeiroNumero() + somarDoisNumeros.getSegundoNumero();
		
		System.out.println("A soma dos dois números digitados é: " + somaDosNumeros);
	}

}
