import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Server extends Thread {

    private static ArrayList<BufferedWriter>clientes;
    private static ServerSocket server;
    private String nome;
    private Socket socket;
    private InputStream linhaEntrada;
    private InputStreamReader leitorLinhaEntrada;
    private BufferedReader BLeitor;  
    private BufferedWriter BEscritor;
    
    public Server(Socket socket){
        this.socket = socket;
        try {
            linhaEntrada  = socket.getInputStream();
            leitorLinhaEntrada = new InputStreamReader(linhaEntrada);
            BLeitor = new BufferedReader(leitorLinhaEntrada);
        } catch (IOException e) {
        	//e.printStackTrace();
        }
    }

    public void run(){
        try{
            String mensagem = "";
            OutputStream linhaSaida=this.socket.getOutputStream();
            Writer escritor = new OutputStreamWriter(linhaSaida);
            BEscritor=new BufferedWriter(escritor);
            clientes.add(BEscritor);
            nome=mensagem=BLeitor.readLine();
            while(!mensagem.equals(null)){
                mensagem=BLeitor.readLine();
                espalharMsg(BEscritor,mensagem);
                System.out.println(mensagem);
                if(mensagem.equals("/platypus")) {
                	espalharMsg(BEscritor,mensagem);
//                	socket.close();
//            		linhaEntrada.close();
//            		leitorLinhaEntrada.close();
//            		BLeitor.close();
            		
                }
            }
        }catch (Exception e) {
        	//e.printStackTrace();
        }
    }
    
    public void espalharMsg(BufferedWriter BSaida, String mensagem) throws IOException {
    	BufferedWriter BEscritor;
    	if(mensagem.equals("/platypus")) {
    		for(BufferedWriter Bescritor:clientes){
    			mensagem="Desligando o servidor...";
    			nome="Server: ";
                BEscritor = (BufferedWriter)Bescritor;
                Bescritor.write(nome+mensagem+"\r\n");
                Bescritor.flush();
                //System.exit(0);
                }
    	}else {       
       try {
    	   for(BufferedWriter Bescritor:clientes){
               BEscritor = (BufferedWriter)Bescritor;
               if(!(BSaida == BEscritor)&&!nome.equals(null)&&!mensagem.equals(null)){
               	if(nome.equals("")) {
       			nome = "Anônimo: ";
                   Bescritor.write(nome+mensagem+"\r\n");
                   Bescritor.flush();
               	}
               	else if(nome.equals("Anônimo: ")) {
                Bescritor.write(nome+mensagem+"\r\n");
                Bescritor.flush();
                }
                else {
                Bescritor.write(nome+": "+mensagem+"\r\n");
                Bescritor.flush();
               }
             }
           }
       }catch(Exception e) {
    	  //e.printStackTrace();
       }
      }
    }

    public static void main(String []args) {

        try{
            JLabel lblMessage=new JLabel("Porta do Servidor:");
            JTextField txtPorta=new JTextField();
            Object[]texts={lblMessage,txtPorta};
            JOptionPane.showMessageDialog(null,texts);
            if(txtPorta.getText().equals(""))
            	System.exit(0);
            server=new ServerSocket(Integer.parseInt(txtPorta.getText()));
            clientes=new ArrayList<BufferedWriter>();
            JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+txtPorta.getText());           
            while(true){
                System.out.println("Aguardando conexão...");
                Socket socket = server.accept();
               // String hostName = socket.getInetAddress().getHostName();
                String aviso = "Cliente " +socket+" conectado...";
                System.out.println(aviso);
                
                Thread t = new Server(socket);
                t.start();
                //espalharMsg(BEscritor,aviso);
            }

        }catch (Exception e) {
        	 JLabel lblErro=new JLabel("Erro: Porta já esta em uso ");
        	 Object[]erro={lblErro};
        	 JOptionPane.showMessageDialog(null,erro);
             System.exit(0);
        }
    }

} 
