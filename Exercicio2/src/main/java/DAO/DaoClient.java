package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Client;

public class DaoClient extends Dao{
	
	public DaoClient() {
		super();
		conectar();
	}
	
	public void finalizaConexao() {
		close();
	}
	
	public boolean insert(Client cliente) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO client (nome_cliente, idade_cliente, email_cliente) "
						+ "VALUES ('"+cliente.getNomeCliente()+  "', '" + cliente.getIdade() + "', '"  
					    + cliente.getEmail() + "');";
			st.executeUpdate(sql);
			st.close();
			status = true;
		}catch(SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Client get(int id) throws SQLException {
		Client cliente = null;
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM client WHERE id_client=" + id + ";";
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()){            
					cliente = new Client(rs.getString("nome_cliente"),rs.getInt("id_client"), rs.getInt("idade_cliente"), rs.getString("email_cliente"));
				}
				st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		
		
		return cliente;
	}
	
	public boolean update(Client client, int idCliente) {
		boolean retorno = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE client SET nome_cliente= '" + client.getNomeCliente() + "', idade_cliente= "
					+ client.getIdade() + ", email_cliente= '" + client.getEmail() + "' WHERE id_client = "
					+ idCliente + ";";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			retorno = true;
		}catch(SQLException u) {
			throw new RuntimeException(u);
		}
		
		return retorno;
	}
	
	public boolean delete(int id) {
		boolean retorno = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM client WHERE id_client=" + id + ";";
			st.executeUpdate(sql);
			st.close();
			retorno = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		
		return retorno;
	}
	
	public int getClientId(String nomeCliente) {
		int idClient = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT id_client FROM client WHERE nome_cliente LIKE '%"+nomeCliente+"%';";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				idClient = rs.getInt("id_client");
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idClient;
	}
	
	public List<Client> get() {
		return getClients("");
	}
	
	public List<Client> getClients(String orderBy){
		List<Client> clients = new ArrayList<Client>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM client" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {	            	
	        	Client u = new Client(rs.getString("nome_cliente"),rs.getInt("id_client"), rs.getInt("idade_cliente"), rs.getString("email_cliente"));
	            clients.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return clients;
	}
}
