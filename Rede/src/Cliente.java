import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.text.SimpleDateFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Cliente extends JFrame implements ActionListener, KeyListener, FocusListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoEnviar, txtIP, txtPorta, txtNome;
	private Socket socket;
	private String nome, titulo;
	private JLabel lblConexao, lblponto, lblNome;
	private JTextArea caixaMensagem;
	private OutputStream linhaSaida;
	private Writer redator;
	private BufferedWriter BEscritor;
	private Timer timer = null;
	private int cor = 1, notificacao, sair = 0;
	public java.net.URL noti;

	public static void main(String[] args) {
		try {
			Cliente frame = new Cliente();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});
			frame.conectar();
			frame.escutar();
		} catch (Exception e) {
			// System.out.println(e+"b");
		}
	}

	public Cliente() throws IOException {
		JLabel lblMessage = new JLabel(" --------------- Conectar ---------------");
		JLabel lblIp = new JLabel("Ip:");
		txtIP = new JTextField();
		JLabel lblPorta = new JLabel("Porta:");
		txtPorta = new JTextField();
		JLabel lblNome = new JLabel("Nome de usuário:");
		txtNome = new JTextField();
		Object[] dados = { lblMessage, lblIp, txtIP, lblPorta, txtPorta, lblNome, txtNome };
		JOptionPane.showMessageDialog(null, dados);
		if (txtNome.getText().equals("") || txtNome.getText().equals(null) || txtNome.getText().length() == 1) {
			nome = "Anônimo: ";
		} else
			nome = txtNome.getText() + "  ";
		if (txtIP.getText().equals(null))
			txtIP.setText("localhost");
		if (txtPorta.getText().equals(""))
			System.exit(0);
		if (txtPorta.getText().length() > 0) {

			setTitle("ChatBox");
			setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\doc\\Sharex\\2020-09\\javaw_LYkh0xfG2J.png"));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 534, 395);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.activeCaptionBorder);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			contentPane.addFocusListener(this);

			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.inactiveCaptionBorder);
			panel.setBounds(10, 67, 498, 223);
			contentPane.add(panel);
			panel.setLayout(null);
			caixaMensagem = new JTextArea();
			panel.add(caixaMensagem);
			caixaMensagem.setEditable(false);
			caixaMensagem.setFont(new Font(" Akzidenz Grotesk", Font.PLAIN, 15));
			JScrollPane scroll = new JScrollPane(caixaMensagem);
			scroll.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.white));
			scroll.setBounds(0, 0, 498, 223);
			caixaMensagem.setLineWrap(true);
			panel.add(scroll);
			panel.addFocusListener(this);

			campoEnviar = new JTextField();
			campoEnviar.setFont(new Font(" Akzidenz Grotesk", Font.PLAIN, 15));
			campoEnviar.setBackground(SystemColor.inactiveCaptionBorder);
			campoEnviar.setBounds(10, 301, 385, 47);
			contentPane.add(campoEnviar);
			campoEnviar.setColumns(10);
			campoEnviar.addKeyListener(this);
			campoEnviar.addFocusListener(this);

			JPanel panel_1 = new JPanel();
			panel_1.setBackground(SystemColor.textHighlight);
			panel_1.setBounds(10, 289, 498, 5);
			contentPane.add(panel_1);
			panel_1.addFocusListener(this);

			JLabel lblChat = new JLabel("Chat");
			lblChat.setFont(new Font(" Akzidenz Grotesk", Font.BOLD, 30));
			lblChat.setBounds(10, 0, 69, 45);
			contentPane.add(lblChat);
			lblChat.addFocusListener(this);

			JLabel lblBox = new JLabel("Box");
			lblBox.setForeground(new Color(128, 0, 0));
			lblBox.setBackground(new Color(128, 0, 0));
			lblBox.setFont(new Font(" Akzidenz Grotesk", Font.BOLD, 30));
			lblBox.setBounds(78, 0, 240, 45);
			contentPane.add(lblBox);
			lblBox.addFocusListener(this);

			titulo = nome.substring(0, nome.length() - 2);
			lblNome = new JLabel(titulo);
			lblNome.setFont(new Font(" Akzidenz Grotesk", Font.PLAIN, 14));
			lblNome.setBounds(177, 43, 280, 19);
			contentPane.add(lblNome);
			lblNome.addFocusListener(this);

			lblConexao = new JLabel("");
			lblConexao.setFont(new Font(" Akzidenz Grotesk", Font.PLAIN, 18));
			lblConexao.setBounds(10, 43, 180, 19);
			contentPane.add(lblConexao);
			lblConexao.addFocusListener(this);

			lblponto = new JLabel(".");
			lblponto.setHorizontalAlignment(SwingConstants.RIGHT);
			lblponto.setForeground(SystemColor.textInactiveText);
			lblponto.setVerticalAlignment(SwingConstants.BOTTOM);
			lblponto.setFont(new Font(" Akzidenz Grotesk", Font.BOLD, 50));
			lblponto.setBounds(484, 18, 24, 21);
			contentPane.add(lblponto);
			lblponto.addFocusListener(this);

			JButton btnEnviar = new JButton("Enviar");
			btnEnviar.setForeground(UIManager.getColor("ColorChooser.foreground"));
			btnEnviar.setBackground(SystemColor.textHighlight);
			btnEnviar.setFont(new Font(" Akzidenz Grotesk", Font.BOLD, 15));
			btnEnviar.setBounds(394, 301, 114, 47);
			contentPane.add(btnEnviar);
			btnEnviar.addKeyListener(this);
			btnEnviar.addFocusListener(this);
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						enviarMensagem();
					} catch (IOException e1) {
						sair = 3;
						sair();
					}
				}
			});

			JButton btnSair = new JButton("Sair");
			btnSair.setForeground(UIManager.getColor("ColorChooser.foreground"));
			btnSair.setFont(new Font(" Akzidenz Grotesk", Font.BOLD, 12));
			btnSair.setBackground(Color.PINK);
			btnSair.setBounds(449, 43, 59, 23);
			contentPane.add(btnSair);
			btnSair.addFocusListener(this);
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						sair = 1;
						sair();
					} catch (Exception er) {
						System.exit(1);
					}
				}
			});
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				enviarMensagem();
			} catch (IOException e1) {
				// System.out.println(e1+"f");;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void conectar() throws IOException {
		lblConexao.setForeground(Color.BLACK);
		lblConexao.setText("Conectando...");
		try {
			socket = new Socket(txtIP.getText(), Integer.parseInt(txtPorta.getText()));
			linhaSaida = socket.getOutputStream();
			redator = new OutputStreamWriter(linhaSaida);
			BEscritor = new BufferedWriter(redator);
			BEscritor.write(txtNome.getText() + "\r\n");
			BEscritor.flush();
			lblConexao.setText("Conectado(a) como: ");
			lblConexao.setForeground(Color.GREEN);
			timer();
			campoEnviar.setText("/conectar");
			enviarMensagem();
		} catch (Exception er) {
			// System.out.println(er + "i");
			;
			caixaMensagem.setText("Erro: não foi possivel conectar-se ao servidor, tente novamente.");
			lblConexao.setForeground(Color.RED);
			lblConexao.setText("Desconectado");
			lblNome.setText("");
			sair = 2;
			sair();
		}
	}

	public void enviarMensagem() throws IOException {
		String txt = campoEnviar.getText();
		java.util.Date data = new java.util.Date();
		SimpleDateFormat formatarData = new SimpleDateFormat("HH:mm");
		String hora = formatarData.format(data);
		if (txt.length() > 0) {
			try {
				if (txt.equals("/platypus")) {
					BEscritor.write("/platypus");
					nome = "Server: ";
					caixaMensagem.append(nome + "Desligando o servidor...\r\n");
					campoEnviar.setText("");
					txt = "";
					sair = 3;
					sair();
				} else if (txt.equals("/conectar")) {
					BEscritor.write(txt + "\r\n");
					caixaMensagem.append("Chat: Bem-vindo(a), " + nome.substring(0, nome.length() - 2) + "\r\n");
					campoEnviar.setText("");
					txt = "";
				} else {
					BEscritor.write(txt + "\r\n");
					caixaMensagem.append(hora + " - ");
					caixaMensagem.append("Você: " + txt + "\r\n");
					campoEnviar.setText("");
					txt = "";
				}
			} catch (Exception e) {
				// System.out.println(e+"h");
			}
			try {
				BEscritor.flush();
				campoEnviar.setText("");
			} catch (Exception e) {
				sair = 4;
				sair();
			}
		}
	}

	public void escutar() throws IOException {
		InputStream linhaEntrada = socket.getInputStream();
		InputStreamReader leitorLinhaEntrada = new InputStreamReader(linhaEntrada);
		BufferedReader BLeitor = new BufferedReader(leitorLinhaEntrada);
		String msg = "";

		while (true)
			if (BLeitor.ready()) {
				msg = BLeitor.readLine();
				if (msg.equals("Chat: Finalizando sessão...")) {
					sair = 3;
					sair();
				} else {
					notificacao();
					caixaMensagem.append(msg + "\r\n");
					caixaMensagem.setCaretPosition(caixaMensagem.getDocument().getLength());
				}
			}
	}

	public void sair() {
		if (sair == 1) {
			try {
				BEscritor.write("Saiu da sala!" + "\r\n");
				caixaMensagem.append("Saiu da sala!" + "\r\n");
				campoEnviar.setText("");
				caixaMensagem.append("Chat: " + "Desconectando...");
				BEscritor.close();
				redator.close();
				linhaSaida.close();
				socket.close();
				lblConexao.setText("Desconectado");
				lblConexao.setForeground(Color.RED);
				lblponto.setForeground(Color.GRAY);
				cor = 3;
				long TEMPO = (700 * 3);
				timer = null;
				if (timer == null) {
					timer = new Timer();
					TimerTask tarefa = new TimerTask() {
						public void run() {
							try {
								System.exit(0);
							} catch (Exception e) {
								// System.out.println(e);
								System.exit(0);
							}
						}
					};
					timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
				}
			} catch (IOException e) {
				System.exit(0);
			}
		} else if (sair == 2) {
			try {
				BEscritor.write(nome + "Saiu da sala!" + "\r\n");
				caixaMensagem.append("Saiu da sala!" + "\r\n");
				campoEnviar.setText("");
				caixaMensagem.append("Chat: " + "Desconectando...");
				BEscritor.close();
				redator.close();
				linhaSaida.close();
				socket.close();
				lblConexao.setText("Desconectado");
				lblConexao.setForeground(Color.RED);
				lblponto.setForeground(Color.GRAY);
				cor = 3;
				long TEMPO = (700 * 7);
				timer = null;
				if (timer == null) {
					timer = new Timer();
					TimerTask tarefa = new TimerTask() {
						public void run() {
							try {
								System.exit(0);
							} catch (Exception e) {
								// System.out.println(e+"e");
							}
						}
					};
					timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
				}
			} catch (IOException e) {
				System.exit(0);
			}
		} else if (sair == 3) {
			try {
				caixaMensagem.append("Chat: Finalizando sessão...\r\n");
				campoEnviar.setText("");
				caixaMensagem.append("Chat: " + "Desconectado" + "\r\n");
				BEscritor.close();
				redator.close();
				linhaSaida.close();
				socket.close();
				lblConexao.setText("Desconectado");
				lblConexao.setForeground(Color.RED);
				lblponto.setForeground(Color.GRAY);
				cor = 3;
				long TEMPO = (700 * 7);
				timer = null;
				if (timer == null) {
					timer = new Timer();
					TimerTask tarefa = new TimerTask() {
						public void run() {
							try {
								System.exit(0);
							} catch (Exception e) {
								// System.out.println(e+"d");
							}
						}
					};
					timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
				}
			} catch (IOException e) {
				System.exit(0);
			}
		} else if (sair == 4) {
			System.exit(0);
		}
	}

	public void timer() {
		long TEMPO = (470 * 2);
		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {
						if (cor == 1) {
							lblponto.setForeground(Color.GRAY);
							cor = 2;
						} else if (cor == 2) {
							lblponto.setForeground(Color.GREEN);
							cor = 1;
						}
					} catch (Exception e) {
						// System.out.println(e+"c");
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
		}
	}

	public void focusGained(FocusEvent e) {
		notificacao = 0;
	}

	public void focusLost(FocusEvent e) {
		notificacao = 1;
	}

	void notificacao() {

		if (notificacao == 1) {
			try {
				noti = getClass().getResource("notificacao.wav");
//				File f = new File("./res/notificacao.wav");
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(noti.toURI().toURL());
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				toFront();
				// TrayIcon.displayMessage("ChatBox", "Você recebeu uma mensagem... ", null);
			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "e");
			}
		}
	}

	public void aviso(String message) {

	}
}