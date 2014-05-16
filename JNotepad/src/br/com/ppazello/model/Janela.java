package br.com.ppazello.model;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.ppazello.controller.JanelaListener;

public class Janela extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private JMenuBar menu;
	private JMenu arquivo;
	private JMenuItem novo , abrir , salvar , fechar ;
	private JPanel painel;
	private JTextArea texto;
	private JScrollPane scroll;
	private JTextField status;
	
	public JTextArea getTexto(){
		return texto;
	}
	
	public JTextField getStatus(){
		return status;
	}
	
	public Janela(){
		super("JNotepad");
		
		montarComponentes();
	}
	
	private void montarComponentes(){
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		montarMenu();
		painel = new JPanel();
		texto = new JTextArea("" , 45 , 53);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		
		scroll = new JScrollPane(texto);
		scroll.setWheelScrollingEnabled(true);
		painel.add(scroll);
		
		status = new JTextField("Barra de status");
		status.setBackground(SystemColor.control);
		status.setEditable(false);
		add(menu , BorderLayout.NORTH);
		add(painel , BorderLayout.CENTER);
		add(status , BorderLayout.SOUTH);

	}
	
	private void montarMenu(){
		menu = new JMenuBar();
		arquivo = new JMenu("Arquivo");
		novo 	= new JMenuItem("Novo");
		abrir 	= new JMenuItem("Abrir");
		salvar 	= new JMenuItem("Salvar");
		fechar 	= new JMenuItem("Fechar");
		
		arquivo.add(novo);
		arquivo.add(abrir);
		arquivo.add(salvar);
		arquivo.addSeparator();
		arquivo.add(fechar);
		
		novo.addActionListener 	(new JanelaListener(novo , this));
		abrir.addActionListener	(new JanelaListener(abrir , this));
		salvar.addActionListener(new JanelaListener(salvar , this));
		fechar.addActionListener(new JanelaListener(fechar , this));		
		menu.add(arquivo);
	}
	
	public void exibir(){
		setSize(600,800);
		setLocation(600,100);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
