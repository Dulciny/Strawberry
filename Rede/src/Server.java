import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		
		ServerSocket server;
		Socket cliente;
		Scanner in;
		String entrada;
		Boolean chatOn;
		
		try {
			server = new ServerSocket(9876);
		} catch (Exception e) {
			System.out.println("Erro ao criar socket na porta 9876");
			System.out.println("Erro:"+e.getMessage());
			return;
		}
		

		try {
		    System.out.println("Aguardando conexão...");
			cliente = server.accept();
			chatOn = true;
			System.out.println("Conectado a:"+cliente.getInetAddress().getHostAddress());
		} catch (Exception e) {
			System.out.println("Erro ao criar socket na porta 9876");
			System.out.println("Erro:"+e.getMessage());
			chatOn = false;
			return;
		}
		
		try {
			do {
			in = new Scanner(cliente.getInputStream());
			entrada = in.nextLine();
			System.out.println("Recebido: " + entrada);
			} while(chatOn = true);
		} catch (Exception e) {
			System.out.println("Erro de comunicação");
			System.out.println("Erro:"+e.getMessage());
			return;
		}
		
		try {
			if(entrada.equals("exit")) {
			   server.close();
			   cliente.close();
			   System.out.println("fechando o servidor...");
			}						
		} catch (Exception e) {
			System.out.println("Erro ao encerrar conexão");
			System.out.println("Erro:"+e.getMessage());
			return;
		}

	}
//b
}
