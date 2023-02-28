package aplicacao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.DaoClient;
import model.Client;

public class mainapp {

	public static void main(String[] args) throws SQLException {
		DaoClient conexaoCliente = new DaoClient();
		Scanner sc = new Scanner(System.in);
		int opcaoDoMenu;
		boolean condicaoDoLoop = true;
		
		System.out.print("Bem vindo ao CRUD de Clientes!\n");
		while(condicaoDoLoop) {
			System.out.print("\nDigite:\n"
					+ "'1' para inserir clientes:\n"
					+ "'2' para atualizar:\n"
					+ "'3' para selecionar um cliente:\n"
					+ "'4' para deletar:\n"
					+ "'5' para sair do programa:\n"
					+ "'6' para recuperar todos os clientes: ");
			opcaoDoMenu = sc.nextInt();
			
			switch(opcaoDoMenu) {
			
			case 1:
				String nomeCliente, emailCliente;
				int idadeCliente;
				System.out.println("========INSERÇÃO DE CLIENTE==========");
				sc.nextLine();
				System.out.print("Digite o nome do cliente: ");
				nomeCliente = sc.nextLine();
				System.out.print("Digite o email do cliente: ");
				emailCliente = sc.next();
				System.out.print("Digite a idade do cliente: ");
				idadeCliente = sc.nextInt();
				Client client = new Client(nomeCliente, idadeCliente, emailCliente);
				boolean res = conexaoCliente.insert(client);
			
				System.out.print((res) ? "Inserção feita com sucesso!\n" : "Algum erro aconteceu tente de novo em alguns instantes");
				
				break;
			case 2:
				sc.nextLine();
				int idClienteParaAtualizar;
				System.out.println("========ATUALIZAÇÃO DE CLIENTES==========");
				
				List<Client> clients = conexaoCliente.get();
				for (Client c: clients) {
					System.out.println(c.toString());
				}
				System.out.print("Digite o ID do cliente que você deseja modificar: ");
				idClienteParaAtualizar = sc.nextInt();
				sc.nextLine();
				System.out.print("Digite o novo nome do cliente: ");
				nomeCliente = sc.nextLine();
				System.out.print("Digite a nova idade do cliente: ");
				idadeCliente = sc.nextInt();
				sc.nextLine();
				System.out.print("Digite o novo email do cliente: ");
				emailCliente = sc.next();
				client = new Client(nomeCliente, idadeCliente, emailCliente);
				res = conexaoCliente.update(client, idClienteParaAtualizar);
			
				System.out.print((res) ? "Atualização feita com sucesso!" : "Algum erro aconteceu, por favor tente novamente");
				
				break;
			case 3:
				int idCliente;
				System.out.println("========SELEÇÃO CLIENTE==========");
				System.out.print("Digite o ID do cliente que você deseja visualizar: ");
				idCliente = sc.nextInt();
				Client cliente = conexaoCliente.get(idCliente);
				System.out.println(cliente);
				break;
			case 4:
				System.out.println("Digite o ID do cliente que voce deseja deletar: ");
				idCliente = sc.nextInt();
				res = conexaoCliente.delete(idCliente);
				
				System.out.println((res) ? "Cliente foi deletado com sucesso" : "Algum erro aconteceu, por favor tente novamente");
				
				break;
			case 5:
				System.out.println("Parando o programa...");
				condicaoDoLoop = false;
				break;
			case 6:
				System.out.println("========LISTAGEM DE TODOS OS CLIENTES==========");
				clients = conexaoCliente.get();
				for (Client c: clients) {
					System.out.println(c.toString());
				}
				break;
			default :
				System.out.println("Esta não é uma opção válida, digite outra");
				break;
			}
		}
		
		System.out.println("Grato por usar o sistema, volte quando quiser =D.");
		
	}

}
