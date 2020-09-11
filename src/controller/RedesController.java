package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	public RedesController(){
		super();
	}
	
	public String ip(String so){
		StringBuffer buffer = new StringBuffer("");
		String Adaptador = "";
		String retBuff;
		if(so.contains("Linux")){	
			try {
				Process process = Runtime.getRuntime().exec("ifconfig");//comando linux
				InputStream fluxo = process.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer2 = new BufferedReader(leitor);
				String linha = buffer2.readLine();
				while(linha!=null){
					if(linha.contains("flags")) {
						String [] arg = linha.split(":");
						Adaptador = arg[0];
					}
					if(linha.contains("inet ")){
		   				 String ip = linha.substring(linha.indexOf ("inet") +5 ,linha.indexOf("netmask")-1);
		   				 System.out.println(Adaptador + " - IPv4: " + ip);
					}
					linha = buffer2.readLine();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				Process process = Runtime.getRuntime().exec("ipconfig");
				InputStream fluxo = process.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer2 = new BufferedReader(leitor);
				String linha = buffer2.readLine();
				while(linha!=null){
					if (linha.contains("Adaptador")) {
						String [] arg  = linha.split(":");
						Adaptador = arg[0];
					}
					if (linha.contains("IPv4")) {
						String [] arg  = linha.split(" ");
						buffer.append(Adaptador + " - IPv4: " + arg[arg.length - 1] +"\n");
					}
					linha = buffer2.readLine();
				 }	
		}
			catch(IOException e){
				e.printStackTrace();
		}
		}
			retBuff = buffer.toString();
			return retBuff;
	}	
	
		public String ping(String so) {
			String media = "";
			String x = "";
			if(so.contains("Linux")) {
				try {
					double aux = 0;
					Process process = Runtime.getRuntime().exec("ping -c10 www.google.com.br");
					InputStream fluxo = process.getInputStream();
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer1 = new BufferedReader(leitor);
					String linha = buffer1.readLine();
					while(linha!=null) {
						if (linha.contains("ttl")) {
							String []arg= linha.split(" ");
							String []arg1= arg[6].split("=");
							aux += Double.parseDouble(arg1[1]);
						}
						linha = buffer1.readLine();
					}
					aux = aux / 10;
					media = "A media do ping: " + aux + " ms";
				}catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					Process process = Runtime.getRuntime().exec("PING www.google.com.br -n 10");
					InputStream fluxo = process.getInputStream();
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer1 = new BufferedReader(leitor);
					String linha = buffer1.readLine();
					while(linha!=null) {
						x = linha;
						linha = buffer1.readLine();
					}
					String[]arg = x.split(" ");
					media = "A media do ping: " + arg[arg.length - 1];
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			return media;
		}
}
	