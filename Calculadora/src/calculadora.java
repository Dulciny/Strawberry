
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class calculadora extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("unused")
	private String montante = "", montante2 = "", poslbl, tirarChars7, tirarChars5, tirarChars6;
	private int etapa = 0, operacao = 0, zero = 1, pos = 0, op = 0, pontinho = 0, tracinho = 0, tamMon, tamMon2, cont = 0, x;
	private JLabel labelPrimario, labelSecundario;
	private char traco = '-';

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					calculadora frame = new calculadora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//////////////////////////////////////////////////////////////////// Layout //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public calculadora() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Doc\\Sharex\\2020-09\\javaw_rXg4ilp33D.png"));
		setTitle("Calculadora");
		setForeground(new Color(47, 79, 79));
		setBackground(new Color(0, 0, 0));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 534);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 465, 111);
		contentPane.add(panel);
		panel.setLayout(null);

		labelPrimario = new JLabel("");
		labelPrimario.setBackground(Color.WHITE);
		labelPrimario.setForeground(Color.DARK_GRAY);
		labelPrimario.setFont(new Font("Serif", Font.BOLD, 45));
		labelPrimario.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPrimario.setBounds(10, 41, 445, 59);
		panel.add(labelPrimario);

		labelSecundario = new JLabel("");
		labelSecundario.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSecundario.setForeground(Color.GRAY);
		labelSecundario.setFont(new Font("Serif", Font.BOLD, 28));
		labelSecundario.setBounds(10, 11, 445, 28);
		panel.add(labelSecundario);
		if (zero == 1);
		labelPrimario.setText("0");

////////////////////////////////////////////////////////////////////  Layout //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////// Dígitos //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JButton numero9 = new JButton("9");
		numero9.setFont(new Font("Serif", Font.PLAIN, 35));
		numero9.setBackground(Color.LIGHT_GRAY);
		numero9.setBounds(250, 199, 120, 71);                                                                        /* Dígito 9 */
		contentPane.add(numero9);
		numero9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 9;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 9;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 9;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 9;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero8 = new JButton("8");
		numero8.setFont(new Font("Serif", Font.PLAIN, 35));
		numero8.setBackground(Color.LIGHT_GRAY);
		numero8.setBounds(130, 199, 120, 71);                                                                  /* Dígito 8 */
		contentPane.add(numero8);
		numero8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 8;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 8;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 8;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 8;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero7 = new JButton("7");
		numero7.setFont(new Font("Serif", Font.PLAIN, 35));
		numero7.setBackground(Color.LIGHT_GRAY);
		numero7.setBounds(10, 199, 120, 71);                                                             /* Dígito 7 */
		contentPane.add(numero7);
		numero7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 7;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 7;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 7;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 7;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero6 = new JButton("6");
		numero6.setFont(new Font("Serif", Font.PLAIN, 35));
		numero6.setBackground(Color.LIGHT_GRAY);
		numero6.setBounds(250, 271, 120, 71);                                                       /* Dígito 6 */
		contentPane.add(numero6);
		numero6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 6;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 6;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 6;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 6;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero5 = new JButton("5");
		numero5.setFont(new Font("Serif", Font.PLAIN, 35));
		numero5.setBackground(Color.LIGHT_GRAY);
		numero5.setBounds(130, 271, 120, 71);                                                 /* Dígito 5 */
		contentPane.add(numero5);
		numero5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 5;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 5;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 5;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 5;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero4 = new JButton("4");
		numero4.setFont(new Font("Serif", Font.PLAIN, 35));
		numero4.setBackground(Color.LIGHT_GRAY);
		numero4.setBounds(10, 271, 120, 71);                                               /* Dígito 4 */
		contentPane.add(numero4);
		numero4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 4;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 4;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 4;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 4;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero3 = new JButton("3");
		numero3.setFont(new Font("Serif", Font.PLAIN, 35));
		numero3.setBackground(Color.LIGHT_GRAY);
		numero3.setBounds(250, 343, 120, 71);                                        /* Dígito 3 */
		contentPane.add(numero3);
		numero3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 3;
					etapaUm();
				   } 
				   else if (pos == 1 && cont == 0) {
					x = 3;
					etapaUm();					
				   } 
				   else if (etapa == 1) {
					x = 3;
					montante +=x;
					labelPrimario.setText(montante);
				   }
				    else {
				    x = 3;
					montante2 +=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero2 = new JButton("2");
		numero2.setFont(new Font("Serif", Font.PLAIN, 35));
		numero2.setBackground(Color.LIGHT_GRAY);
		numero2.setBounds(130, 343, 120, 71);                                     /* Dígito 2 */
		contentPane.add(numero2);
		numero2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 2;
					etapaUm();
				  } 
				   else if (pos == 1 && cont == 0) {
					x = 2;
					etapaUm();
				  } 
				   else if (etapa == 1) {
					x = 2;
					montante +=x;
					labelPrimario.setText(montante);
				  }
				   else {
					x=2;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero1 = new JButton("1");
		numero1.setFont(new Font("Serif", Font.PLAIN, 35));
		numero1.setBackground(Color.LIGHT_GRAY);
		numero1.setBounds(10, 343, 120, 71);                                  /* Dígito 1 */
		contentPane.add(numero1);
		numero1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 1;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 1;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 1;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 1;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton numero0 = new JButton("0");
		numero0.setFont(new Font("Serif", Font.PLAIN, 35));
		numero0.setBackground(Color.LIGHT_GRAY);
		numero0.setBounds(130, 415, 120, 71);                              /* Dígito 0 */
		contentPane.add(numero0);
		numero0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cont == 0 && etapa == 0) {
					x = 0;
					etapaUm();
				   } 
				    else if (pos == 1 && cont == 0) {
				    x = 0;
				    etapaUm();
				   }  
				    else if (etapa == 1) {
					x = 0;
					montante+=x;
					labelPrimario.setText(montante);
				   }
				    else {
					x = 0;
					montante2+=x;
					labelPrimario.setText(montante2);
				}
			}
		});

		JButton ponto = new JButton(".");
		ponto.setVerticalAlignment(SwingConstants.TOP);
		ponto.setFont(new Font("Serif", Font.BOLD, 40));
		ponto.setBackground(SystemColor.controlShadow);                /* Pontinho */
		ponto.setBounds(10, 415, 120, 71);
		contentPane.add(ponto);
		ponto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    if (pos == 1 && cont == 0) {
				    montante = "";
					montante2 = "";
					etapa = 1;
					montante += "0.";
					labelPrimario.setText(montante);
					labelSecundario.setText("");
					pos = 0;
			       }
					else if (etapa == 0 && montante.length() == 0 && cont == 0 && etapa == 0) {
					montante = "";
					montante2 = "";
					etapa = 1;
					montante += "0.";
					labelPrimario.setText(montante);
					labelSecundario.setText("");
					pontinho = 1;
					pos = 0;
				   }
					else if (pos == 1 && etapa == 0 && montante.length() == 0 && cont == 0 && etapa == 0) {
					montante = "";
					montante2 = "";
					etapa = 1;
					montante += "0.";
					labelPrimario.setText(montante);
					labelSecundario.setText("");
					pos = 0;
				   } 
					else if (etapa == 1 && pontinho == 0 && montante.length() == 0) {
					montante += "0.";
					labelPrimario.setText(montante);
					pontinho = 1;
				   }   
					else if (etapa == 2 && montante2.length() == 0) {
					montante2 += "0.";
					labelPrimario.setText(montante2);
					pontinho = 1;
				   } 
					else if (etapa == 2 && montante2.length() == 0) {
					montante2 += "0.";
					labelPrimario.setText(montante2);
					pontinho = 1;
				   } 
					else if (etapa == 1 && pontinho == 0) {
					montante += ".";
					labelPrimario.setText(montante);
					pontinho = 1;
				   } 
					else if (etapa == 2 && pontinho == 0) {
					montante2 += ".";
					labelPrimario.setText(montante2);
					pontinho = 1;

				}
			}
		});

		JButton button = new JButton("+/-");
		button.setForeground(SystemColor.desktop);
		button.setFont(new Font("MS PGothic", Font.PLAIN, 28));
		button.setBackground(SystemColor.controlShadow);            /* Tracinho */
		button.setBounds(250, 415, 120, 71);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (etapa == 0 && montante.length() == 0 && cont == 0 && etapa == 0) {
					montante = "";
					montante2 = "";
					etapa = 1;
					montante += traco;
					labelPrimario.setText(montante);
					labelSecundario.setText("");
					tracinho = 1;
					pos = 0;
				} 
				else if (pos == 1 && etapa == 0 && montante.length() == 0 && cont == 0 && etapa == 0) {
					montante = "";
					montante2 = "";
					etapa = 1;
					montante += traco;
					labelPrimario.setText(montante);
					labelSecundario.setText("");
					pos = 0;
				} 
				else if (etapa == 1 && pontinho == 0 && montante.length() == 0) {
					montante += traco;
					labelPrimario.setText(montante);
					tracinho = 1;
				} 
				else if (etapa == 2 && montante2.length() == 0) {
					montante2 += traco;
					labelPrimario.setText(montante2);
					tracinho = 1;
				} 
				else if (etapa == 2 && montante2.length() == 0) {
					montante2 += traco;
					labelPrimario.setText(montante2);
					tracinho = 1;
				}
				else if (etapa == 1 && tracinho == 0 && montante.length() > 0) {
					montante = traco + montante;
					labelPrimario.setText(montante);
					tracinho = 1;
				}
				if (etapa == 2 && tracinho == 0 && montante2.length() > 0) {
					montante2 = traco + montante2;
					labelPrimario.setText(montante2);
					tracinho = 1;
				}
			}
		});

/////////////////////////////////////////////////////////////////////////////////////////////////// Digitos //////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////// Operações //////////////////////////////////////////////////////////////////////////////////////////////////////

		JButton adicionar = new JButton("+");
		adicionar.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
		adicionar.setBackground(SystemColor.controlShadow);
		adicionar.setBounds(370, 343, 105, 71);             /* Adicionar */
		contentPane.add(adicionar);
		adicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(operacao > 0) {
					calculoPrevio();
					operacao = 4;
					opEtapa2();
				 }
				  else if (etapa == 1 && montante.length() > 0) {
					operacao = 4;
					labelSecundario.setText(montante + " +");
					opEtapa();
					
				 } 
				  else if (etapa == 2 && montante.length() > 0 && pos == 1) {
					operacao = 1;
					labelSecundario.setText(montante + " +");
					opEtapa();
				}

			}
		});

		JButton subtrair = new JButton("-");
		subtrair.setVerticalAlignment(SwingConstants.TOP);
		subtrair.setFont(new Font("Serif", Font.PLAIN, 40));
		subtrair.setBackground(SystemColor.controlShadow);
		subtrair.setBounds(370, 271, 105, 71);                      /* Subtrair */
		contentPane.add(subtrair);
		subtrair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(operacao > 0) {
					calculoPrevio();
					operacao = 3;
					opEtapa2();
				 }
				  else if (etapa == 1 && montante.length() > 0) {
					operacao = 3;
					labelSecundario.setText(montante + " -");
					opEtapa();
				 } 
				  else if (etapa == 2 && montante.length() > 0 && pos == 1) {
					operacao = 1;
					labelSecundario.setText(montante + " -");
					opEtapa();
				}

			}
		});

		JButton multiplicar = new JButton("\u00D7");
		multiplicar.setFont(new Font("Serif", Font.BOLD, 30));
		multiplicar.setBackground(SystemColor.controlShadow);
		multiplicar.setBounds(370, 199, 105, 71);                           /* Multiplicar */
		contentPane.add(multiplicar);
		multiplicar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(operacao > 0) {
					calculoPrevio();
					operacao = 2;
					opEtapa2();
				}			  
				else if (etapa == 1 && montante.length() > 0) {
					operacao = 2;
					labelSecundario.setText(montante + " x");
					opEtapa();
				} 
				else if (etapa == 2 && montante.length() > 0 && pos == 1) {
					operacao = 1;
					labelSecundario.setText(montante + " x");
					opEtapa();
				}
			}
		});

		JButton dividir = new JButton("\u00F7");
		dividir.setFont(new Font("Tahoma", Font.PLAIN, 30));
		dividir.setBackground(SystemColor.controlShadow);
		dividir.setBounds(370, 127, 105, 71);                                           /* Dividir */
		contentPane.add(dividir);
		dividir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(operacao > 0) {
					calculoPrevio();
					operacao = 1;
					opEtapa2();
				}				 
				else if (etapa == 1 && montante.length() > 0) {
					operacao = 1;
					labelSecundario.setText(montante + " ÷");
					opEtapa();
				} 
				else if (etapa == 2 && montante.length() > 0 && pos == 1) {
					operacao = 1;
					labelSecundario.setText(montante + " ÷");
					opEtapa();
				}
			}
		});

		JButton resultado = new JButton("=");
		resultado.setFont(new Font("Serif", Font.PLAIN, 40));
		resultado.setBackground(new Color(255, 228, 225));
		resultado.setBounds(370, 415, 105, 71);                                               /* Resultado */
		contentPane.add(resultado);
		resultado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					calcularResultado();
					
					cont = 0;
				} catch (Exception erro) {
					resetarCalculadora();
					labelSecundario.setText("Erro");
				}
			}
		});

		JButton Resetar = new JButton("C");
		Resetar.setFont(new Font("Serif", Font.PLAIN, 30));
		Resetar.setBackground(SystemColor.controlShadow);
		Resetar.setBounds(130, 127, 120, 71);                                                          /* Resetar */
		contentPane.add(Resetar);
		Resetar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetarCalculadora();
			}
		});

		JButton softResetar = new JButton("CE");
		softResetar.setFont(new Font("Serif", Font.PLAIN, 30));
		softResetar.setBackground(SystemColor.controlShadow);
		softResetar.setBounds(10, 127, 120, 71);
		contentPane.add(softResetar);                                                                          /* Soft Reset */
		softResetar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (etapa == 1 && pos == 0) {
					montante = "";
					labelPrimario.setText("0");
					pontinho = 0;
					tracinho = 0;
					cont = 0;
				} 
				else if (etapa == 2 && pos == 0) {
					montante2 = "";
					labelPrimario.setText("0");
					pontinho = 0;
					pontinho = 0;
					tracinho = 0;
					cont = 0;
				}
				else if (pos == 1) {
					resetarCalculadora();
			}
		   }		
		});

		JButton backspace = new JButton("\u2190");
		backspace.setFont(new Font("Tahoma", Font.PLAIN, 36));
		backspace.setBackground(SystemColor.controlShadow);
		backspace.setBounds(250, 127, 120, 71);                                                                         /* Backspace */
		contentPane.add(backspace);
		backspace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tamMon = montante.length();
				tamMon2 = montante2.length();
				if (pos == 1) {
					resetarCalculadora();
				} 
				else if (etapa == 1 && tamMon == 1) {
					resetarCalculadora();
				} 
				else if (etapa == 2 && tamMon2 == 1) {
					montante2 = "";
					tracinho = 0;
					labelPrimario.setText("0");
				} 
				else if (etapa == 2 && tamMon2 == 0) {
					etapa = 1;
					montante2 = "";
					labelPrimario.setText(montante);
					labelSecundario.setText(montante2);
				} 
				else if (etapa == 1 && tamMon > 0 && pos == 0) {
					montante = String.valueOf(montante).substring(0, String.valueOf(montante).length() - 1);
					labelPrimario.setText(montante);
				} 
				else if (etapa == 2 && tamMon2 > 0 && pos == 0) {
					montante2 = String.valueOf(montante2).substring(0, String.valueOf(montante2).length() - 1);
					labelPrimario.setText(montante2);
				}
			}
		});
}
	
/////////////////////////////////////////////////////////////////////////////////////////////////// Operação //////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////// Funções /////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String verificarDecimal(String x) {  /* Veririficar decimais */
		String montante = x;
		if(etapa != 0 && montante.length() != 0) {
			int tirarDecimais1 = (montante.length() -2);
			int tirarDecimais2 = (montante.length() -1);
			int tirarDecimais3 = (montante.length() );
			String tirarDecimais4 = String.valueOf(montante).substring( tirarDecimais1, tirarDecimais2 );
			String tirarDecimais5 = String.valueOf(montante).substring( tirarDecimais2, tirarDecimais3 );
			String tirarDecimais6 = (tirarDecimais4 + tirarDecimais5);
			if(tirarDecimais6.equals(".0")){
				montante = String.valueOf(montante).substring(0, tirarDecimais3 -2);
				}
			 }
		return montante;
}
	
	public String verificarChar(String x) throws Exception {  /* Verificar Chars */
		String montante = x;
		if(etapa != 0 && montante.length() != 0) {
			int tirarChars1 = (montante.length() -3);
			int tirarChars2 = (montante.length() -1);
			int tirarChars3 = (montante.length() );
			String tirarChars4 = String.valueOf(montante).substring( tirarChars1, tirarChars2 );
			String tirarChars5 = String.valueOf(montante).substring( tirarChars2, tirarChars3 );
			tirarChars7 = (tirarChars4 + tirarChars5);
			if(tirarChars7.equals("E+1")){
				montante = String.valueOf(montante).substring(0, tirarChars3 -3);
				montante += "0";
				}
			   else if(Integer.parseInt(tirarChars5) > 1 && Integer.parseInt(tirarChars5) < 7){
				 throw new Exception("erro"){};              
			   }
			 }		    				 
		return montante;
}
	
	public String somarNumeros(String x, String y) {   /* Somar */
	try {
		return verificarChar(String.valueOf((new BigDecimal(x)).stripTrailingZeros().add(new BigDecimal(y).stripTrailingZeros())));	
	}
	catch(Exception erro1) {
		return verificarDecimal(String.valueOf((Float.valueOf(x))+(Float.valueOf(y))));
	}
		
	}

	public String dividirNumeros(String x, String y) { /* Dividir */
		return verificarDecimal(String.valueOf((Double.valueOf(x) / (Double.valueOf(y)))));
	}

	public String subtrairNumeros(String x, String y) {  /* Subtrair */
		try {
			return verificarChar(String.valueOf((new BigDecimal(x)).stripTrailingZeros().subtract(new BigDecimal(y).stripTrailingZeros())));	
		}
		catch(Exception erro1) {
			return verificarDecimal(String.valueOf(( Float.valueOf(x))-(Float.valueOf(y))));
		}
	}
	
	public String multiplicarNumeros(String x, String y) {  /* Multiplicar */
		try {
			return verificarChar(String.valueOf((new BigDecimal(x)).stripTrailingZeros().multiply(new BigDecimal(y).stripTrailingZeros())));	
		}
		catch(Exception erro1) {
			return verificarDecimal(String.valueOf((Float.valueOf(x))*(Float.valueOf(y))));
		}
	}

	public void resetarCalculadora() {  /* Resetar */
		pos = 0;montante = "";montante2 = "";operacao = 0;etapa = 0;pontinho = 0;tracinho = 0;labelPrimario.setText("0");labelSecundario.setText("");cont = 0;
	}
		
    public void calculoPrevio() {  /* Calculo prévio */
	    if(operacao == 4 && montante2.length() > 0) {
		labelSecundario.setText(montante +  " + " + montante2 + " = " + somarNumeros(montante,montante2));
		montante = somarNumeros(montante,montante2);
		labelPrimario.setText(montante);
		op = 4;
		}
		else if(operacao == 3 && montante2.length() > 0) {
		labelSecundario.setText(montante +  " - " + montante2 + " = " + subtrairNumeros(montante,montante2));
		montante = subtrairNumeros(montante,montante2);
		labelPrimario.setText(montante);
		op = 2;
		}
		else if(operacao == 1 && montante2.length() > 0) {
		labelSecundario.setText(montante +  " \u00F7 " + montante2 + " = " + dividirNumeros(montante,montante2));
		montante = dividirNumeros(montante,montante2);
		labelPrimario.setText(montante);
		op = 1;
		}
		else if(operacao == 2 && montante2.length() > 0) {
		labelSecundario.setText(montante +  " x " + montante2 + " = " + multiplicarNumeros(montante,montante2));
		montante = multiplicarNumeros(montante,montante2);
		labelPrimario.setText(montante);
		op = 2;
		}
}
    
    public void calcularResultado() {  /* Resultado */  
		tamMon = montante.length();
		tamMon2 = montante2.length();
		if (etapa == 3 || etapa == 2 || pos == 1) {
			if (pos == 0 && tamMon2 != 0 && tamMon != 0) {
				if (operacao == 4 && etapa == 2 && tamMon2 != 0) {
					labelSecundario.setText(montante + " + " + montante2 + " =");
					montante = somarNumeros(montante, montante2);
					labelPrimario.setText(montante);
					res();
					op = 4;
					
				} 
				else if (operacao == 3 && etapa == 2 && tamMon2 != 0) {
					labelSecundario.setText(montante + " - " + montante2 + " =");
					montante = subtrairNumeros(montante, montante2);
					labelPrimario.setText(montante);
					res();
					op = 3;
					
				} 
				else if (operacao == 2 && etapa == 2 && tamMon2 != 0) {
					labelSecundario.setText(montante + " x " + montante2 + " =");
					montante = multiplicarNumeros(montante, montante2);
					labelPrimario.setText(montante);
					res();
					op = 2;
					
				} 
				else if (operacao == 1 && etapa == 2 && tamMon2 != 0) {
					if (Double.valueOf(montante2) == 0) {
						resetarCalculadora();
						labelSecundario.setText("Não pode dividir por zero");
					} 
					else {
						labelSecundario.setText(montante + " ÷ " + montante2 + " =");
						montante = dividirNumeros(montante, montante2);
						labelPrimario.setText(montante);
						res();
						op = 1;
						
					}
				}
			} 
			else if (pos == 1 && tamMon2 != 0 && tamMon != 0) {
				if (op == 4 && montante2.length() != 0) {
					montante = somarNumeros(montante, montante2);
					labelSecundario.setText(subtrairNumeros(montante, montante2) + " + " + montante2 + " =");
					labelPrimario.setText(montante);
				} else if (op == 3 && montante2.length() != 0) {
					montante = subtrairNumeros(montante, montante2);
					labelSecundario.setText(subtrairNumeros(montante, montante2) + " - " + montante2 + " =");
					labelPrimario.setText(montante);
				} else if (op == 1 && montante2.length() != 0) {
					montante = dividirNumeros(montante, montante2);
					labelSecundario.setText(multiplicarNumeros(montante, montante2) + " ÷ " + montante2 + " =");
					labelPrimario.setText(montante);
				}
				else if (op == 2 && montante2.length() != 0) {
					montante = multiplicarNumeros(montante, montante2);
					labelSecundario.setText(dividirNumeros(montante, montante2) + " x " + montante2 + " =");
					labelPrimario.setText(montante);
				}
			}
		}
	}
    
    public void etapaUm() {
    	montante = "";
		montante2 = "";
		etapa = 1;
		montante += x;
    	labelPrimario.setText(montante);
		labelSecundario.setText("");
		pos = 0;
    }
    
    public void opEtapa() {
    	etapa = 2;
		labelPrimario.setText("0");
		montante2 = "";
		op = 1;
		pontinho = 0;
		cont = 1;
		pos = 0;
		tracinho = 0;
    }
    
    public void opEtapa2() {
    	etapa = 2;
		labelPrimario.setText("0");
		montante2 = "";
		op = 0;
		pontinho = 0;
		cont = 1;
		pos = 0;
    }
    
    public void res() {
		etapa = 1;
		operacao = 0;
		pontinho = 0;
		tracinho = 0;
		pos = 1;
}
}
