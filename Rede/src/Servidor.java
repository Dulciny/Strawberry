import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends Thread {

	private static ArrayList<BufferedWriter> clientes;
	private static ServerSocket server;
	private String nome, ip;
	private Socket socket;
	private InputStream linhaEntrada;
	private InputStreamReader leitorLinhaEntrada;
	private BufferedReader BLeitor;
	private BufferedWriter BEscritor;
	private JFrame f;
	private JPanel p;
	private static JTextArea log;
	private JScrollPane s;
	private static JTextField txtPorta;

	public Servidor(Socket socket) {
		/* Thread dos clientes */
		this.socket = socket;
		try {
			linhaEntrada = socket.getInputStream();
			leitorLinhaEntrada = new InputStreamReader(linhaEntrada);
			BLeitor = new BufferedReader(leitorLinhaEntrada);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public void run() {
		/* recebe a mensagem e manda pro espalharMensagem */
		try {
			String mensagem = "";
			OutputStream linhaSaida = this.socket.getOutputStream();
			Writer escritor = new OutputStreamWriter(linhaSaida);
			BEscritor = new BufferedWriter(escritor);
			clientes.add(BEscritor);
			nome = mensagem = BLeitor.readLine();
			while (!mensagem.equals(null)) {
				mensagem = BLeitor.readLine();
				espalharMsg(BEscritor, mensagem);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void espalharMsg(BufferedWriter BSaida, String mensagem) throws IOException {
		/* envia a mensagem para todos os clientes */
		BufferedWriter BEscritor;
		java.util.Date data = new java.util.Date();
		SimpleDateFormat formatarData = new SimpleDateFormat("HH:mm");
		String hora = formatarData.format(data);
		if (mensagem.equals("/platypus")) {
			for (BufferedWriter Bescritor : clientes) {
				mensagem = "Finalizando sess�o...";
				nome = "Chat: ";
				BEscritor = (BufferedWriter) Bescritor;
				Bescritor.write(nome + mensagem + "\r\n");
				Bescritor.flush();
			}
			System.exit(0);
		} else if (mensagem.equals("/conectar")) {
			for (BufferedWriter Bescritor : clientes) {
				BEscritor = (BufferedWriter) Bescritor;
				if (nome.equals("")) {
					nome = "An�nimo";
					mensagem = " conectou...";
					BEscritor = (BufferedWriter) Bescritor;
					Bescritor.write("Chat: " + nome + mensagem + "\r\n");
					Bescritor.flush();
				} else if (nome.equals("An�nimo: ")) {
					mensagem = " conectou...";
					BEscritor = (BufferedWriter) Bescritor;
					Bescritor.write("Chat: " + nome + mensagem + "\r\n");
					Bescritor.flush();
				} else {
					mensagem = " conectou...";
					BEscritor = (BufferedWriter) Bescritor;
					Bescritor.write("Chat: " + nome + mensagem + "\r\n");
					Bescritor.flush();
				}
			}
		} else {
			try {
				for (BufferedWriter Bescritor : clientes) {
					BEscritor = (BufferedWriter) Bescritor;
					if (!(BSaida == BEscritor) && !nome.equals(null) && !mensagem.equals(null)) {
						if (nome.equals("")) {
							nome = "An�nimo: ";
							Bescritor.write(hora + " - ");
							Bescritor.write(nome + mensagem + "\r\n");
							Bescritor.flush();
						} else if (nome.equals("An�nimo: ")) {
							Bescritor.write(hora + " - ");
							Bescritor.write(nome + mensagem + "\r\n");
							Bescritor.flush();
						} else {
							Bescritor.write(hora + " - ");
							Bescritor.write(nome + ": " + mensagem + "\r\n");
							Bescritor.flush();
						}
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		log.setCaretPosition(log.getDocument().getLength());
		log.append(nome + ": " + mensagem + "\r\n");
	}

	public static void main(String[] args) {
		/* inicia o servidor, perguntando a porta */
		try {
			JLabel lblMessage = new JLabel("Porta a ser utilizada:");
			txtPorta = new JTextField();
			Object[] texts = { lblMessage, txtPorta };
			JOptionPane.showMessageDialog(null, texts);
			if (txtPorta.getText().equals(""))
				System.exit(0);
			server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
			clientes = new ArrayList<BufferedWriter>();
			JOptionPane.showMessageDialog(null, "Servidor ativo na porta: " + txtPorta.getText());
			new Servidor();
			while (true) {
				log.append("Aguardando conex�o...\r\n");
				Socket socket = server.accept();
				String aviso = "Cliente " + InetAddress.getLocalHost() + " conectado...";
				log.append(aviso + "\r\n");
				Thread t = new Servidor(socket);
				t.start();
			}
		} catch (Exception e) {
			// System.out.println(e);
			JLabel lblErro = new JLabel("Erro: Porta j� esta em uso ");
			Object[] erro = { lblErro };
			JOptionPane.showMessageDialog(null, erro);
			System.exit(0);
		}
	}

	public Servidor() {
		/* constructor da interface do log */
		gui();
	}

	public void gui() {
		/* interface do log */
		try {
			try {
				URL whatismyip = new URL("http://checkip.amazonaws.com");
				BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
				ip = in.readLine();
			} catch (Exception e) {
				InetAddress host = InetAddress.getLocalHost();
				ip = host.getHostAddress();
			}
			f = new JFrame("Don't peek! - " + "Porta: " + txtPorta.getText() + " - Ip: " + ip);
			f.setSize(451, 491);
			f.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\doc\\Sharex\\2020-09\\javaw_LYkh0xfG2J.png"));
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			p = new JPanel();
			p.setBackground(Color.gray);
			p.setBounds(2, 2, 0, 0);
			f.add(p);
			p.setLayout(null);
			log = new JTextArea();
			p.add(log);
			log.setEditable(false);
			log.setFont(new Font("SansSerif", Font.PLAIN, 15));
			s = new JScrollPane(log);
			s.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.white));
			s.setBounds(4, 4, 427, 444);
			log.setLineWrap(true);
			p.add(s);
			f.setVisible(true);
			log.setText("Servidor iniciado...\r\n");
		} catch (Exception e) {
			// System.out.println(e);
		}
	}
}