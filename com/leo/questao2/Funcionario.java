package com.leo.questao2;

import java.util.Date;

public class Funcionario {

	String nome;
	Date dataDeEntrada;
	Date dataNascimento;
	double salario;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataDeEntrada() {
		return dataDeEntrada;
	}
	public void setDataDeEntrada(Date dataDeEntrada) {
		this.dataDeEntrada = dataDeEntrada;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	/*
	 * Metodo para calcular a gratificacao do funcionario baseado na data de entrada, nascimento e salario
	 */
	void calcularGratificacao(){

		double gratificacao = 0;
		Date today = new Date();
		long diff = today.getTime() - dataDeEntrada.getTime();
		long dias = diff/ (1000*60*60*24);
		long anosTrabalhados = dias/365;

		long idade = today.getTime() - dataNascimento.getTime();
		long idadeDias = idade/ (1000*60*60*24);
		long idadeAnos = idadeDias/365;

		if(anosTrabalhados >= 10){
			gratificacao = salario*0.15;
		}else if(anosTrabalhados >=5 || anosTrabalhados <= 7){
			gratificacao = salario*0.102;
		}else if(anosTrabalhados <= 3){
			gratificacao = salario*0.055;
		}


		if (idadeAnos >= 50) {
			gratificacao += salario*0.12;
		}
		System.out.println("Funcionario: " + nome);
		System.out.println("Salario: "+ salario);
		System.out.println("Idade: " + idadeAnos);
		System.out.println("Anos trabalhados na empresa: " + anosTrabalhados);
		System.out.println("Gratificacao: " + gratificacao);
		System.out.println("Salario total: " +(salario+gratificacao));

	}

}
