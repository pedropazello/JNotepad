package br.com.ppazello.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.ppazello.model.Janela;

public class JanelaListener implements ActionListener {
	
	private Janela janela;
	private JMenuItem item;
	private JFileChooser chooser;
	
	public Janela Lister(){
		return janela;
		
	}
	
	public JanelaListener(JMenuItem item ,  Janela janela){
		this.item = item;
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JTextArea texto = janela.getTexto();
		JTextField status = janela.getStatus();
		
		if(e.getSource() == item){
			String label = item.getText();
			if(label.equals("Novo")){
				int opcao = JOptionPane.showConfirmDialog(null,"tem certeza que deseja criar um novo sem salvar?","mensagem" , JOptionPane.YES_NO_OPTION);
				if(opcao == 0){
					texto.setText("");
				}else{
					status.setText(item.getText());
					chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("arquivos txt", "txt");
					chooser.addChoosableFileFilter(filter);  
					chooser.setAcceptAllFileFilterUsed(false);  
					int opcao1 = chooser.showSaveDialog(janela);
					
					if(opcao1 == JFileChooser.APPROVE_OPTION){
						salvarArquivo(chooser.getSelectedFile());
					}
					
				}
				
				
			}
			if(item.getText().equals("Abrir")){
				status.setText("abrir");
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("arquivos txt", "txt");
				chooser.addChoosableFileFilter(filter);  
				chooser.setAcceptAllFileFilterUsed(false); 
				int opcao = chooser.showOpenDialog(janela);
				
				if(opcao == JFileChooser.APPROVE_OPTION){
					String txt = lerArquivo(chooser.getSelectedFile());
					texto.setText(txt);
					texto.setCaretPosition(0);
				}
			}
			
			if(label.equals("Salvar")){
					status.setText(item.getText());
					chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("arquivos txt", "txt");
					chooser.addChoosableFileFilter(filter);  
					chooser.setAcceptAllFileFilterUsed(false); 
					int opcao = chooser.showSaveDialog(janela);
					
					 JFileChooser chooser = new JFileChooser();
					    int returnVal = chooser.showOpenDialog(item);
					    if(returnVal == JFileChooser.APPROVE_OPTION) {
					       System.out.println("You chose to open this file: " +
					            chooser.getSelectedFile().getName());
					    }
					 
					
					if(opcao == JFileChooser.APPROVE_OPTION){
						salvarArquivo(chooser.getSelectedFile());
					}
				};
		}
	}
	

	
	private String lerArquivo(File arq){
		String conteudo = " ";
		
			
		try{
			FileInputStream fis = new FileInputStream(arq);
			byte[] arquivo = new byte[fis.available()];
			fis.read(arquivo);
			fis.close();
			conteudo = new String(arquivo , Charset.forName("UTF-8"));
		}catch(IOException ee){
			System.out.println(ee.getMessage());
		}
		
		return conteudo;
	}
	
	private void salvarArquivo(File arq){
		try{
			FileOutputStream fos = new FileOutputStream(arq);
			String txt = janela.getTexto().getText();
			fos.write(txt.getBytes());
			fos.close();
		}catch(IOException ee){
			System.out.println(ee.getMessage());
		}
	}
	
}
