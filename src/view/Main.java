package view;
import controller.RedesController;
import javax.swing.JOptionPane;

public class Main{
	public static void main(String[]args) {
		String SO = System.getProperty("os.name");
		RedesController Metodos = new RedesController();
		int esc;
		do {
			esc = Integer.parseInt(JOptionPane.showInputDialog(SO +": \n 1 - IPv4 \n 2 - PING \n 9 - Finalizar"));
			switch (esc) {
			case 1: 
				System.out.println(Metodos.ip(SO));
				break;
			case 2:
				System.out.println(Metodos.ping(SO));
				break;
			case 9: 
				JOptionPane.showMessageDialog(null,"Falou :3");
				break;
			default:
				JOptionPane.showMessageDialog(null,"Valor invalido!!!");
			}
		}while(esc!=9);
	}
}