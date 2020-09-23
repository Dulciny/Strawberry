import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		Socket socket;
		PrintStream saida;
		Scanner in;
		Boolean chatOn;
		
		try {
			socket = new Socket("localhost", 9876);
			chatOn = true;
		} catch (Exception e) {
			System.out.println("Erro ao solicitar conexão");
			System.out.println("Erro: "+e.getMessage());
			chatOn = false;
			return;
		}
		
		try {
			do {
			saida = new PrintStream(socket.getOutputStream());
			in = new Scanner(System.in);
			saida.println(in.next());
			} while(chatOn = true);
			if(in.next().equals("exit")) {
				
			socket.close();
			System.out.println("fechando o servidor...");
			}	
		} catch (Exception e) {
			System.out.println("Erro ao solicitar conexão");
			System.out.println("Erro: "+e.getMessage());
			return;
		}
		
		try {
			if(in.next().equals("exit")) { 
				   socket.close();
				   System.out.println("fechando o servidor...");
				}			
		} catch (Exception e) {
			System.out.println("Erro ao encerrar conexão");
			System.out.println("Erro:"+e.getMessage());
			return;
		}

	}
//A
}
