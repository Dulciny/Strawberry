import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthScrollBarUI;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
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
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Cliente extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoEnviar, txtIP, txtPorta, txtNome;
	private Socket socket;
	private String nome;
	private JLabel lblConexao, lblponto;
	private JTextArea caixaMensagem; 
    private OutputStream linhaSaida ;
    private Writer redator;
    private BufferedWriter BEscritor;
    private Timer timer = null;
    private int cor = 1;

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
    		//e.printStackTrace();
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
        Object[] dados = {lblMessage, lblIp, txtIP, lblPorta,  txtPorta, lblNome, txtNome };
        JOptionPane.showMessageDialog(null, dados);
        nome = txtNome.getText();
		nome +=": ";	
		if(nome.equals(": "))
		nome = "Anônimo: ";
		if(txtIP.getText().equals(null))
		   txtIP.setText("localhost");
		if(txtPorta.getText().equals(""))
		System.exit(0);
		if(txtPorta.getText().length()>0) {	
		
		setTitle("ChatBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 395);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 67, 498, 223);
		contentPane.add(panel);
		panel.setLayout(null);
		caixaMensagem = new JTextArea();
		panel.add(caixaMensagem);
		caixaMensagem.setEditable(false);
		caixaMensagem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(caixaMensagem);
	    scroll.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.white));
		scroll.setBounds(0, 0, 498, 223);
		caixaMensagem.setLineWrap(true);
		panel.add(scroll);
			
		campoEnviar = new JTextField();
		campoEnviar.setFont(new Font("SansSerif", Font.PLAIN, 15));
		campoEnviar.setBackground(SystemColor.inactiveCaptionBorder);
		campoEnviar.setBounds(10, 301, 385, 47);
		contentPane.add(campoEnviar);
		campoEnviar.setColumns(10);
		campoEnviar.addKeyListener(this);
				
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(10, 289, 498, 5);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Chat");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 0, 67, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblTube = new JLabel("Box");
		lblTube.setForeground(new Color(128, 0, 0));
		lblTube.setBackground(new Color(128, 0, 0));
		lblTube.setFont(new Font("SansSerif",Font.BOLD,30));
		lblTube.setBounds(78, 0, 240, 45);
		contentPane.add(lblTube);
		
		lblNome = new JLabel(nome.substring(0,nome.length()-2));
		lblNome.setFont(new Font("SansSerif",Font.PLAIN,14));
		lblNome.setBounds(159,43,280,19);
		contentPane.add(lblNome);
		
		lblConexao = new JLabel("");
		lblConexao.setFont(new Font("SansSerif",Font.PLAIN,18));
		lblConexao.setBounds(10, 43, 145, 19);
		contentPane.add(lblConexao);
		
        lblponto = new JLabel(".");
		lblponto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblponto.setForeground(SystemColor.textInactiveText);
		lblponto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblponto.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblponto.setBounds(484, 18, 24, 21);
		contentPane.add(lblponto);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setForeground(UIManager.getColor("ColorChooser.foreground"));
		btnEnviar.setBackground(SystemColor.textHighlight);
		btnEnviar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEnviar.setBounds(394, 301, 114, 47);
		contentPane.add(btnEnviar);
		btnEnviar.addKeyListener(this);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					enviarMensagem();
				} catch (IOException e1) {
					//e1.printStackTrace();
				}
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(UIManager.getColor("ColorChooser.foreground"));
		btnSair.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSair.setBackground(Color.PINK);
		btnSair.setBounds(449, 43, 59, 23);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				sair();
				}catch(Exception er) {
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

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                enviarMensagem();
            } catch (IOException e1) {
                //e1.printStackTrace();
            }
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void conectar() throws IOException{	
    	lblConexao.setForeground(Color.BLACK);
	    lblConexao.setText("Conectando...");
	    try {
    	socket = new Socket(txtIP.getText(),Integer.parseInt(txtPorta.getText()));
        linhaSaida = socket.getOutputStream();
        redator = new OutputStreamWriter(linhaSaida);
        BEscritor = new BufferedWriter(redator);
        BEscritor.write(txtNome.getText()+"\r\n");
        BEscritor.flush();	        
        lblConexao.setText("Conectado como: ");
        lblConexao.setForeground(Color.GREEN);
        timer();        
	    }catch(Exception er) {
	    	//er.printStackTrace();
	    	caixaMensagem.setText("Erro: não foi possivel conectar-se ao servidor, tente novamente.");
    		lblConexao.setForeground(Color.RED);
    		lblConexao.setText("Desconectado");
    		sair2();
	    }	    		
    }
	
	public void enviarMensagem() throws IOException{
    	String txt = campoEnviar.getText();
         	try {
             if(txt.equals("/platypus")) {
             BEscritor.write(txt+"\r\n");
             caixaMensagem.append("Desligando o servidor...");
             campoEnviar.setText("");
             txt="";
             sair2();
             }else {
             BEscritor.write(txt+"\r\n");
             caixaMensagem.append(nome+txt+"\r\n");
             campoEnviar.setText("");
             txt="";
             }
         	}catch(Exception e){}        	     			
    	 BEscritor.flush();			
         campoEnviar.setText("");	         
       }
	
	public void escutar() throws IOException{         
        InputStream in = socket.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader bfr = new BufferedReader(inr);
        String msg = "";  
        
        while(true)      	
            if(bfr.ready()){
            	msg = bfr.readLine();                           
                	caixaMensagem.append(msg+"\r\n");
                    caixaMensagem.setCaretPosition(caixaMensagem.getDocument().getLength());          	 
      }
    }
	
	public void sair() {
        try {		
        	BEscritor.write("Saiu da sala!"+"\r\n");
            caixaMensagem.append("Saiu da sala!"+"\r\n");
            campoEnviar.setText("");
		    caixaMensagem.append("Chat: "+"Desconectando...");
		    BEscritor.close();
			redator.close();
		    linhaSaida.close();
		    socket.close();
			lblConexao.setText("Desconectado");
			lblConexao.setForeground(Color.RED);
			lblponto.setForeground(Color.GRAY);
			cor = 3;
			long TEMPO = (700 * 3); 
			timer =null;
			 if (timer==null) {
	              timer=new Timer();
	              TimerTask tarefa=new TimerTask() {
	              public void run() {
	               try {                        	   
	            	   System.exit(0);
	                  } catch (Exception e) {
	                     //e.printStackTrace();
	                   }
	                  }
	                 };
	               timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
	           }				
	    } catch (IOException e) {
			System.exit(0);
	   }	     
	}
	
	public void sair2() {
        try {		
        	BEscritor.write(nome+"Saiu da sala!"+"\r\n");
            caixaMensagem.append("Saiu da sala!"+"\r\n");
            campoEnviar.setText("");
		    caixaMensagem.append("Chat: "+"Desconectando...");
		    BEscritor.close();
			redator.close();
		    linhaSaida.close();
		    socket.close();
			lblConexao.setText("Desconectado");
			lblConexao.setForeground(Color.RED);
			lblponto.setForeground(Color.GRAY);
			cor = 3;
			long TEMPO = (700 * 7); 
			timer =null;
			 if (timer==null) {
	              timer=new Timer();
	              TimerTask tarefa=new TimerTask() {
	              public void run() {
	               try {                        	   
	            	   System.exit(0);
	                  } catch (Exception e) {
	                     //e.printStackTrace();
	                   }
	                  }
	                 };
	               timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
	           }				
	    } catch (IOException e) {
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
                  if(cor==1) {
                    lblponto.setForeground(Color.GRAY);
                    cor=2;
                  }
                  else if(cor==2){
                    lblponto.setForeground(Color.GREEN);
                    cor=1;
                   }
                  } catch (Exception e) {
                     //e.printStackTrace();
                   }
                  }
                 };
                timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
           }	  
	}
	
}
