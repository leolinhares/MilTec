package com.leo.questao2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Run {
	
	private static Funcionario dados() throws ParseException{
		Scanner sc = new Scanner(System.in);
	    Funcionario funcionario = new Funcionario();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	 
	    
		System.out.println("Digite o nome do funcionário:");
		funcionario.setNome(sc.nextLine());
		System.out.println("Digite a data de entrada na empresa (dd/mm/aaaa):");
		String data1 = sc.nextLine();
		System.out.println("Digite a data de nascimento (dd/mm/aaaa):");
		String data2 = sc.nextLine();
		System.out.println("Digite o salario do funcionário:");
		funcionario.setSalario(sc.nextDouble());
		
		try{
			Date entrada = sdf.parse(data1);
			Date nascimento = sdf.parse(data2);
			funcionario.setDataDeEntrada(entrada);
			funcionario.setDataNascimento(nascimento);

		}catch(ParseException e){
	         System.out.println("Parse Exception");
		}
		sc.close();
		return funcionario;
	}
	
	public static void main(String[] args) throws ParseException {
		Funcionario f1 = dados();
		f1.calcularGratificacao();
	}

}
