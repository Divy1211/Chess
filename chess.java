package GUI.Chess;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
public class chess {
	JButton b[] = new JButton[64];
	static String m,re[] = new String[64];
	static boolean undercheck,ksc,qsc;
	static int wkm,wkrm,wqrm,bkm,bkrm,bqrm,kingsq,check,check2,r,chance,ca,enb,enw,ens[] = new int[2];
	public static String invertCase(String a) {
		int c = 0;
		String n = "";
		while(c < a.length()) {
			if(Character.isUpperCase(a.charAt(c))) {
				n = n+Character.toLowerCase(a.charAt(c));
			}
			else if(Character.isLowerCase(a.charAt(c)))
				n = n+Character.toUpperCase(a.charAt(c));
			else if(a.charAt(c) == ' ')
				n = n+" ";
			else
				n = n+a.substring(c,c+1);
			c++;
		}
		return n;
	}
	public void checkforcheck(String a) {
		int co = 0;
		check2 = 0;
		while(co < 64) {
			boolean bl = false;
			if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
				bl = Character.isLowerCase(b[co].getText().charAt(0));
			else if(!b[co].getText().equals(""))
				bl = Character.isUpperCase(b[co].getText().charAt(0));
			if(b[co].getText().equalsIgnoreCase("n") && bl) {
				a = b[co].getText();
				break;
			}
			co++;
		}
		b[co].setText("-");
		int kings = co;
		co = 0;
		while(co < 64) {
			check++;
			boolean bl = false;
			if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
				bl = Character.isLowerCase(b[co].getText().charAt(0));
			else if(!b[co].getText().equals(""))
				bl = Character.isUpperCase(b[co].getText().charAt(0));
			if(bl) {
				if(b[co].getText().equalsIgnoreCase("k"))
						knight(co,b[co].getText());
				else if(b[co].getText().equalsIgnoreCase("j"))
						bishop(co,b[co].getText());
				else if(b[co].getText().equalsIgnoreCase("l"))
						rook(co,b[co].getText());
				else if(b[co].getText().equalsIgnoreCase("m")) 
						queen(co,b[co].getText());
				else if(b[co].getText().equals("i")) {
					if(co%8 != 0 && b[co+7].getText().equals("-")) {
						b[co+7].setText(re[co+7]);
						check2++;
						if(!b[co+7].getText().equals("") && !b[co+7].getText().equals("-"))
							b[co+7].setEnabled(false);
					}
					if(co%8 != 7 && b[co+9].getText().equals("-")) {
						b[co+9].setText(re[co+9]);
						check2++;
						if(!b[co+9].getText().equals("") && !b[co+9].getText().equals("-"))
							b[co+9].setEnabled(false);
					}
				}
				else if(b[co].getText().equals("I")) {
					if(co%8 != 7 && b[co-7].getText().equals("-")) {
						b[co-7].setText(re[co-7]);
						check2++;
						if(!b[co-7].getText().equals("") && !b[co-7].getText().equals("-"))
							b[co-7].setEnabled(false);
					}
					if(co%8 != 0 && b[co-9].getText().equals("-")) {
						b[co-9].setText(re[co-9]);
						check2++;
						if(!b[co-9].getText().equals("") && !b[co-9].getText().equals("-"))
							b[co-9].setEnabled(false);
					}
				}
			}
			co++;
		}
		co = kings;
	    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.PLAIN, 20)));
	    UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,20)));
		if(check2 > 0) {
			if(undercheck) {
				b[co].setText(a);
				JOptionPane.showMessageDialog(null, "King Still Under Check!","CHECK!",JOptionPane.WARNING_MESSAGE);
			}
			else {
				b[co].setText(a);
				if(ca == 0) {
					b[co].setBackground(new Color(204,27,17));
					JOptionPane.showMessageDialog(null, "King Under Check!","CHECK!",JOptionPane.WARNING_MESSAGE);
					kingsq = co;
				}
				undercheck = true;
			}
		}
		else {
			b[co].setText(a);
			Color c;
			if(kingsq+2 < 64 && kingsq+2 >= 0)
				c = b[kingsq+2].getBackground();
			else
				c = b[kingsq-2].getBackground();
			b[kingsq].setBackground(c);
			undercheck = false;
		}
	}
	public void check(String a) {
		int co = 0;
		boolean bl = false;
		while(co < 64) {
			check++;
			bl = false;
			if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
				bl = Character.isLowerCase(b[co].getText().charAt(0));
			else if(!b[co].getText().equals(""))
				bl = Character.isUpperCase(b[co].getText().charAt(0));
			if(!re[co].equals(""))
				bl = true;
			if(bl) {
				if(b[co].getText().equalsIgnoreCase("k") || re[co].equalsIgnoreCase("k")) {
					if(b[co].equals("-"))
						knight(co,re[co]);
					else
						knight(co,b[co].getText());
				}
				else if(b[co].getText().equalsIgnoreCase("j") || re[co].equalsIgnoreCase("j")) {
					if(b[co].equals("-"))
						bishop(co,re[co]);
					else
						bishop(co,b[co].getText());
				}
				else if(b[co].getText().equalsIgnoreCase("l") || re[co].equalsIgnoreCase("l")) {
					if(b[co].equals("-"))
						rook(co,re[co]);
					else
						rook(co,b[co].getText());
				}
				else if(b[co].getText().equalsIgnoreCase("m") || re[co].equalsIgnoreCase("m")) {
					if(b[co].equals("-"))
						queen(co,re[co]);
					else
						queen(co,b[co].getText());
				}
				else if(b[co].getText().equals("i") || re[co].equals("i")) {
					if(co%8 != 0 && b[co+7].getText().equals("-")) {
						b[co+7].setText(re[co+7]);
						if(!b[co+7].getText().equals("") && !b[co+7].getText().equals("-"))
							b[co+7].setEnabled(false);
					}
					if(co%8 != 7 && b[co+9].getText().equals("-")) {
						b[co+9].setText(re[co+9]);
						if(!b[co+9].getText().equals("") && !b[co+9].getText().equals("-"))
							b[co+9].setEnabled(false);
					}
				}
				else if(b[co].getText().equals("I") || re[co].equals("I")) {
					if(co%8 != 7 && b[co-7].getText().equals("-")) {
						b[co-7].setText(re[co-7]);
						if(!b[co-7].getText().equals("") && !b[co-7].getText().equals("-"))
							b[co-7].setEnabled(false);
					}
					if(co%8 != 0 && b[co-9].getText().equals("-")) {
						b[co-9].setText(re[co-9]);
						if(!b[co-9].getText().equals("") && !b[co-9].getText().equals("-"))
							b[co-9].setEnabled(false);
					}
				}
				else if(b[co].getText().equalsIgnoreCase("n"))
					king(co,b[co].getText());
			}
			co++;
		}
	}
	public void king(int c, String a) {
		int co = 0,i = 0;
		boolean bl;
		if(check == 0) {
			ksc = false;
			qsc = false;
			m = a;
			while(co < 64) {
				bl = false;
				if(b[co].getText().equals("-")) {
					b[co].setText(re[co]);
					i++;
				}
				if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				if(bl)
					b[co].setEnabled(false);
				co++;
			}
			if(i > 0) {
				m = "";
				return;
			}
		}
		co = 0;
		int plm[] = {c-9,c-8,c-7,c-1,c+1,c+7,c+8,c+9};
		String as = "";
		int cs = 0;
		if(check == 0) {
			as = a;
			cs = c;
		}
		while(co < 64) {
			i = 0;
			while(i < 8) {
				bl = true;
				if(!b[co].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				if(check > 0) {
					if(plm[i] == co && ((Math.abs((c%8)-(plm[i]%8))) < 2) && b[co].getText().equals("-")) {
						b[co].setText(re[co]);
						if(!b[co].getText().equals(""))
							b[co].setEnabled(false);
					}
				}
				else if(plm[i] == co && ((Math.abs((c%8)-(plm[i]%8))) < 2) && bl) {
					re[co] = b[co].getText();
					b[co].setText("-");
					b[co].setEnabled(true);
					r = c;
				}
				i++;
			}
			co++;
		}
		int g = 0;
		if(check == 0) {
			g++;
			if(Character.isUpperCase(as.charAt(0))) {
				if(bkm == 0 && bkrm == 0 && b[cs+1].getText().equals("-") && b[cs+2].getText().equals("")) {
					re[cs+2] = b[cs+2].getText();
					b[cs+2].setText("-");
					b[cs+2].setEnabled(true);
					r = cs;
					ksc = true;
				}
				if(bkm == 0 && bqrm == 0 && b[cs-1].getText().equals("-") && b[cs-2].getText().equals("") && b[cs-3].getText().equals("")) {
					re[cs-2] = b[cs-2].getText();
					b[cs-2].setText("-");
					b[cs-2].setEnabled(true);
					r = cs;
					qsc = true;
				}
			}
			else {
				if(wkm == 0 && wkrm == 0 && b[cs+1].getText().equals("-") && b[cs+2].getText().equals("")) {
					re[cs+2] = b[cs+2].getText();
					b[cs+2].setText("-");
					b[cs+2].setEnabled(true);
					r = cs;
					ksc = true;
				}
				if(wkm == 0 && wqrm == 0 && b[cs-1].getText().equals("-") && b[cs-2].getText().equals("") && b[cs-3].getText().equals("")) {
					re[cs-2] = b[cs-2].getText();
					b[cs-2].setText("-");
					b[cs-2].setEnabled(true);
					r = cs;
					qsc = true;
				}
			}
		}
		if(check > 0)
			check = 0;
		else
			check(a);
		if(g > 0) {
			if(b[cs+1].getText().equals(""))
				b[cs+2].setText("");
			if(b[cs-1].getText().equals(""))
				b[cs-2].setText("");
		}
	}
	public void pawn(int c,String a) {
		int co = 0,i = 0;
		boolean bl = false;
		if(check == 0) {
			m = a;
			while(co < 64) {
				bl = false;
				if(b[co].getText().equals("-")) {
					b[co].setText(re[co]);
					i++;
				}
				if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				if(bl)
					b[co].setEnabled(false);
				co++;
			}
			if(i > 0) {
				m = "";
				return;
			}
		}
		co = 0;
		i = 0;
		int plm = 8;
		if(Character.isUpperCase(a.charAt(0))) {
			if(c <= 55 && 48 <= c) {
				while(co < 2 && c-plm <= 63 && c-plm >= 0) {
					bl = false;
					if(b[c-plm].getText().equals("")) {
						re[c-plm] = b[c-plm].getText();
						b[c-plm].setEnabled(true);
						b[c-plm].setText("-");
					}
					if(!b[c-9].getText().equals("") && Character.isLowerCase(a.charAt(0)))
						bl = Character.isUpperCase(b[c-9].getText().charAt(0));
					else if(!b[c-9].getText().equals(""))
						bl = Character.isLowerCase(b[c-9].getText().charAt(0));
					if(c%8 != 0 && !b[c-9].getText().equals("") && bl) {
						re[c-9] = b[c-9].getText();
						b[c-9].setEnabled(true);
						b[c-9].setText("-");
					}
					if(!b[c-7].getText().equals("") && Character.isLowerCase(a.charAt(0)))
						bl = Character.isUpperCase(b[c-7].getText().charAt(0));
					else if(!b[c-7].getText().equals(""))
						bl = Character.isLowerCase(b[c-7].getText().charAt(0));
					if(c%8 != 0 &&!b[c-7].getText().equals("") && bl) {
						re[c-7] = b[c-7].getText();
						b[c-7].setEnabled(true);
						b[c-7].setText("-");
					}
					plm+=8;
					co++;
				}
			}
			else {
				bl = false;
				if(b[c-plm].getText().equals("")) {
					re[c-plm] = b[c-plm].getText();
					b[c-plm].setEnabled(true);
					b[c-plm].setText("-");
				}
				if(c%8 != 0 && !b[c-9].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c-9].getText().charAt(0));
				else if(c%8 != 0 && !b[c-9].getText().equals(""))
					bl = Character.isLowerCase(b[c-9].getText().charAt(0));
				if(c%8 != 0 && !b[c-9].getText().equals("") && bl) {
					re[c-9] = b[c-9].getText();
					b[c-9].setEnabled(true);
					b[c-9].setText("-");
				}
				if(enb > 0) {
					if(c == ens[0]) {
						re[c-7] = b[c-7].getText();
						b[c-7].setEnabled(true);
						b[c-7].setText("-");
					}
					else if(c == ens[1]) {
						re[c-9] = b[c-9].getText();
						b[c-9].setEnabled(true);
						b[c-9].setText("-");
					}
				}
				if(!b[c-7].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c-7].getText().charAt(0));
				else if(!b[c-7].getText().equals(""))
					bl = Character.isLowerCase(b[c-7].getText().charAt(0));
				if(c%8 != 0 &&!b[c-7].getText().equals("") && bl) {
					re[c-7] = b[c-7].getText();
					b[c-7].setEnabled(true);
					b[c-7].setText("-");
				}
			}
		}
		else {
			if(c <= 15 && 8 <= c) {
				while(co < 2 && c+plm <= 63 && c+plm >= 0) {
					bl = false;
					if(b[c+plm].getText().equals("")) {
						re[c+plm] = b[c+plm].getText();
						b[c+plm].setEnabled(true);
						b[c+plm].setText("-");
					}
					if(c%8 != 7 && !b[c+9].getText().equals("") && Character.isLowerCase(a.charAt(0)))
						bl = Character.isUpperCase(b[c+9].getText().charAt(0));
					else if(c%8 != 7 && !b[c+9].getText().equals(""))
						bl = Character.isLowerCase(b[c+9].getText().charAt(0));
					if(c%8 != 7 && !b[c+9].getText().equals("") && bl) {
						re[c+9] = b[c+9].getText();
						b[c+9].setEnabled(true);
						b[c+9].setText("-");
					}
					if(!b[c+7].getText().equals("") && Character.isLowerCase(a.charAt(0)))
						bl = Character.isUpperCase(b[c+7].getText().charAt(0));
					else if(!b[c+7].getText().equals(""))
						bl = Character.isLowerCase(b[c+7].getText().charAt(0));
					if(c%8 != 0 &&!b[c+7].getText().equals("") && bl) {
						re[c+7] = b[c+7].getText();
						b[c+7].setEnabled(true);
						b[c+7].setText("-");
					}
					plm+=8;
					co++;
				}
			}
			else {
				bl = false;
				if(b[c+plm].getText().equals("")) {
					re[c+plm] = b[c+plm].getText();
					b[c+plm].setEnabled(true);
					b[c+plm].setText("-");
				}
				if(c%8 != 7 && !b[c+9].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c+9].getText().charAt(0));
				else if(c%8 != 7 && !b[c+9].getText().equals(""))
					bl = Character.isLowerCase(b[c+9].getText().charAt(0));
				if(c%8 != 7 && !b[c+9].getText().equals("") && bl) {
					re[c+9] = b[c+9].getText();
					b[c+9].setEnabled(true);
					b[c+9].setText("-");
				}
				if(enw > 0) {
					if(c == ens[0]) {
						re[c+9] = b[c+9].getText();
						b[c+9].setEnabled(true);
						b[c+9].setText("-");
					}
					else if(c == ens[1]) {
						re[c+7] = b[c+7].getText();
						b[c+7].setEnabled(true);
						b[c+7].setText("-");
					}
				}
				if(!b[c+7].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c+7].getText().charAt(0));
				else if(!b[c+7].getText().equals(""))
					bl = Character.isLowerCase(b[c+7].getText().charAt(0));
				if(c%8 != 0 &&!b[c+7].getText().equals("") && bl) {
					re[c+7] = b[c+7].getText();
					b[c+7].setEnabled(true);
					b[c+7].setText("-");
				}
			}
		}
		r = c;
	}
	public void queen(int c,String a) {
		int co = 0,i = 0;
		boolean bl = false;
		if(check == 0) {
			m = a;
			while(co < 64) {
				bl = false;
				if(b[co].getText().equals("-")) {
					b[co].setText(re[co]);
					i++;
				}
				if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				if(bl)
					b[co].setEnabled(false);
				co++;
			}
			if(i > 0) {
				m = "";
				return;
			}
		}
		int plm[] = {-8,-1,1,8};
		int plm2[] = {-8,-1,1,8};
		while(i < 4) {
			while(c+plm[i] < 64 && c+plm[i] >= 0) {
				if(c%8 == 7) {
					if(i == 2)
						break;
				}
				if(c <= 7 && c >= 0) {
					if(i == 0)
						break;
				}
				if(c <= 63 && c >= 56) {
					if(i == 4)
						break;
				}
				if(c%8 == 0) {
					if(i == 1)
						break;
				}
				bl = false;
				if(!b[c+plm[i]].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c+plm[i]].getText().charAt(0));
				else if(!b[c+plm[i]].getText().equals(""))
					bl = Character.isLowerCase(b[c+plm[i]].getText().charAt(0));
				if(check > 0) {
					if(!b[c+plm[i]].getText().equals("") && !bl && !b[c+plm[i]].getText().equals("-"))
						break;
					else if(bl && !b[c+plm[i]].getText().equalsIgnoreCase("n"))
						break;
					else if(b[c+plm[i]].getText().equals("-")) {
						b[c+plm[i]].setText(re[c+plm[i]]);
						check2++;
						if(!b[c+plm[i]].getText().equals(""))
							b[c+plm[i]].setEnabled(false);
					}
				}
				else {
					if(bl) {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
						break;
					}
					else if(!b[c+plm[i]].getText().equals("") && !bl)
						break;
					else {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
					}
				}
				if(i == 0) {
					if(((c+plm[i]) >= 0 && 7 >= (c+plm[i])))
						break;
				}
				else if(i == 1) {
					if((c+plm[i])%8 == 0)
						break;
				}
				else if(i == 2) {
					if((c+plm[i])%8 == 7)
						break;
				}
				else if(i == 3) {
					if(((c+plm[i]) >= 56 && 63 >= (c+plm[i])))
						break;
				}
				plm[i]+=plm2[i];
			}
			i++;
		}
		i = 0;
		plm[0] = -9;
		plm[1] = -7;
		plm[2] = 7;
		plm[3] = 9;
		plm2[0] = -9;
		plm2[1] = -7;
		plm2[2] = 7;
		plm2[3] = 9;
		while(i < 4) {
			while(c+plm[i] < 64 && c+plm[i] >= 0) {
				if(c%8 == 7) {
					if(i%2 != 0)
						break;
				}
				if(c <= 7 && c >= 0) {
					if(i < 2)
						break;
				}
				if(c <= 63 && c >= 56) {
					if(i > 1)
						break;
				}
				if(c%8 == 0) {
					if(i%2 == 0)
						break;
				}
				bl = false;
				if(!b[c+plm[i]].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c+plm[i]].getText().charAt(0));
				else if(!b[c+plm[i]].getText().equals(""))
					bl = Character.isLowerCase(b[c+plm[i]].getText().charAt(0));
				if(check > 0) {
					if(!b[c+plm[i]].getText().equals("") && !bl && !b[c+plm[i]].getText().equals("-"))
						break;
					else if(bl && !b[c+plm[i]].getText().equalsIgnoreCase("n"))
						break;
					else if(b[c+plm[i]].getText().equals("-")) {
						b[c+plm[i]].setText(re[c+plm[i]]);
						check2++;
						if(!b[c+plm[i]].getText().equals(""))
							b[c+plm[i]].setEnabled(false);
					}
				}
				else
					if(bl) {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
						break;
					}
					else if(!b[c+plm[i]].getText().equals("") && !bl)
						break;
					else {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
					}
				if(i == 0) {
					if((c+plm[i])%8 == 0 || ((c+plm[i]) >= 0 && 7 >= (c+plm[i])))
						break;
				}
				else if(i == 1) {
					if((c+plm[i])%8 == 7 || ((c+plm[i]) >= 0 && 7 >= (c+plm[i])))
						break;
				}
				else if(i == 2) {
					if((c+plm[i])%8 == 0 || ((c+plm[i]) >= 56 && 63 >= (c+plm[i])))
						break;
				}
				else if(i == 3) {
					if((c+plm[i])%8 == 7 || ((c+plm[i]) >= 56 && 63 >= (c+plm[i])))
						break;
				}
				plm[i]+=plm2[i];
			}
			i++;
		}
		if(check == 0)
			r = c;
		else
			check = 0;
	}
	public void rook(int c,String a) {
		int co = 0,i = 0;
		boolean bl = false;
		if(check == 0) {
			m = a;
			while(co < 64) {
				bl = false;
				if(b[co].getText().equals("-")) {
					b[co].setText(re[co]);
					i++;
				}
				if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				if(bl)
					b[co].setEnabled(false);
				co++;
			}
			if(i > 0) {
				m = "";
				return;
			}
		}
		int plm[] = {-8,-1,1,8};
		int plm2[] = {-8,-1,1,8};
		while(i < 4) {
			while(c+plm[i] < 64 && c+plm[i] >= 0) {
				if(c%8 == 7) {
					if(i == 2)
						break;
				}
				if(c <= 7 && c >= 0) {
					if(i == 0)
						break;
				}
				if(c <= 63 && c >= 56) {
					if(i == 4)
						break;
				}
				if(c%8 == 0) {
					if(i == 1)
						break;
				}
				bl = false;
				if(!b[c+plm[i]].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c+plm[i]].getText().charAt(0));
				else if(!b[c+plm[i]].getText().equals(""))
					bl = Character.isLowerCase(b[c+plm[i]].getText().charAt(0));
				if(check > 0) {
					if(!b[c+plm[i]].getText().equals("") && !bl && !b[c+plm[i]].getText().equals("-"))
						break;
					else if(bl && !b[c+plm[i]].getText().equalsIgnoreCase("n"))
						break;
					else if(b[c+plm[i]].getText().equals("-")) {
						b[c+plm[i]].setText(re[c+plm[i]]);
						check2++;
						if(!b[c+plm[i]].getText().equals(""))
							b[c+plm[i]].setEnabled(false);
					}
				}
				else
					if(bl) {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
						break;
					}
					else if(!b[c+plm[i]].getText().equals("") && !bl)
						break;
					else {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
					}
				if(i == 0) {
					if(((c+plm[i]) >= 0 && 7 >= (c+plm[i])))
						break;
				}
				else if(i == 1) {
					if((c+plm[i])%8 == 0)
						break;
				}
				else if(i == 2) {
					if((c+plm[i])%8 == 7)
						break;
				}
				else if(i == 3) {
					if(((c+plm[i]) >= 56 && 63 >= (c+plm[i])))
						break;
				}
				plm[i]+=plm2[i];
			}
			i++;
		}
		if(check == 0)
			r = c;
		else
			check = 0;
	}
	public void bishop(int c,String a) {
		int co = 0,i = 0;
		boolean bl = false;
		if(check == 0) {
			m = a;
			while(co < 64) {
				bl = false;
				if(b[co].getText().equals("-")) {
					b[co].setText(re[co]);
					i++;
				}
				if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				if(bl)
					b[co].setEnabled(false);
				co++;
			}
			if(i > 0) {
				m = "";
				return;
			}
		}
		int plm[] = {-9,-7,7,9};
		int plm2[] = {-9,-7,7,9};
		while(i < 4) {
			while(c+plm[i] < 64 && c+plm[i] >= 0) {
				if(c%8 == 7) {
					if(i%2 != 0)
						break;
				}
				if(c <= 7 && c >= 0) {
					if(i < 2)
						break;
				}
				if(c <= 63 && c >= 56) {
					if(i > 1)
						break;
				}
				if(c%8 == 0) {
					if(i%2 == 0)
						break;
				}
				bl = false;
				if(!b[c+plm[i]].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[c+plm[i]].getText().charAt(0));
				else if(!b[c+plm[i]].getText().equals(""))
					bl = Character.isLowerCase(b[c+plm[i]].getText().charAt(0));
				if(check > 0) {
					if(!b[c+plm[i]].getText().equals("") && !bl && !b[c+plm[i]].getText().equals("-"))
						break;
					else if(bl && !b[c+plm[i]].getText().equalsIgnoreCase("n"))
						break;
					else if(b[c+plm[i]].getText().equals("-")) {
						b[c+plm[i]].setText(re[c+plm[i]]);
						check2++;
						if(!b[c+plm[i]].getText().equals(""))
							b[c+plm[i]].setEnabled(false);
					}
				}
				else {
					if(bl) {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
						break;
					}
					else if(!b[c+plm[i]].getText().equals("") && !bl)
						break;
					else {
						re[c+plm[i]] = b[c+plm[i]].getText();
						b[c+plm[i]].setEnabled(true);
						b[c+plm[i]].setText("-");
					}
				}
				if(i == 0) {
					if((c+plm[i])%8 == 0 || ((c+plm[i]) >= 0 && 7 >= (c+plm[i])))
						break;
				}
				else if(i == 1) {
					if((c+plm[i])%8 == 7 || ((c+plm[i]) >= 0 && 7 >= (c+plm[i])))
						break;
				}
				else if(i == 2) {
					if((c+plm[i])%8 == 0 || ((c+plm[i]) >= 56 && 63 >= (c+plm[i])))
						break;
				}
				else if(i == 3) {
					if((c+plm[i])%8 == 7 || ((c+plm[i]) >= 56 && 63 >= (c+plm[i])))
						break;
				}
				plm[i]+=plm2[i];
			}
			i++;
		}
		if(check == 0)
			r = c;
		else
			check = 0;
	}
	public void knight(int c, String a) {
		int co = 0,i = 0;
		boolean bl;
		if(check == 0) {
			m = a;
			while(co < 64) {
				bl = false;
				if(b[co].getText().equals("-")) {
					b[co].setText(re[co]);
					i++;
				}
				if(!b[co].getText().equals("") && Character.isUpperCase(a.charAt(0)))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				if(bl)
					b[co].setEnabled(false);
				co++;
			}
			if(i > 0) {
				m = "";
				return;
			}
		}
		co = 0;
		int plm[] = {c-17,c-15,c-10,c-6,c+6,c+10,c+15,c+17};
		while(co < 64) {
			i = 0;
			while(i < 8) {
				bl = true;
				if(!b[co].getText().equals("") && Character.isLowerCase(a.charAt(0)))
					bl = Character.isUpperCase(b[co].getText().charAt(0));
				else if(!b[co].getText().equals(""))
					bl = Character.isLowerCase(b[co].getText().charAt(0));
				if(check > 0) {
					if(plm[i] == co && ((Math.abs((c%8)-(plm[i]%8))) < 3) && b[co].getText().equals("-")) {
						b[co].setText(re[co]);
						check2++;
						if(!b[co].getText().equals(""))
							b[co].setEnabled(false);
					}
				}
				else if(plm[i] == co && ((Math.abs((c%8)-(plm[i]%8))) < 3) && bl) {
					re[co] = b[co].getText();
					b[co].setText("-");
					b[co].setEnabled(true);
					r = c;
				}
				i++;
			}
			co++;
		}
		check = 0;
	}
	public static void main(String[] ar) {
		new chess();
	}
	public chess() {
		check = 0;
		wkm = 0;
		bkm = 0;
		bkrm = 0;
		bqrm = 0;
		wkrm = 0;
		wqrm = 0;
		ca = 0;
		JFrame f = new JFrame("chess");
		f.setSize(825, 860);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		JPanel p = new JPanel();
		p.setLayout(null);
		int c = 0;
		int x = 10, y = 710,bo2;
		Actionlistener al = new Actionlistener();
		boolean bo = true;
		while(c < 64) {
			re[c] = "";
			if(c == 0 || c == 7)
				b[c] = new JButton("l");
			else if(c == 1 || c == 6)
				b[c] = new JButton("k");
			else if(c == 2 || c == 5)
				b[c] = new JButton("j");
			else if(c == 3)
				b[c] = new JButton("m");
			else if(c == 4)
				b[c] = new JButton("n");
			else if(c >= 8 && c <= 	15)
				b[c] = new JButton("i");
			else if(c == 56 || c == 63) {
				b[c] = new JButton("L");
				b[c].setEnabled(false);
			}
			else if(c == 57 || c == 62) {
				b[c] = new JButton("K");
				b[c].setEnabled(false);
			}
			else if(c == 58 || c == 61) {
				b[c] = new JButton("J");
				b[c].setEnabled(false);
			}
			else if(c == 59) {
				b[c] = new JButton("M");
				b[c].setEnabled(false);
			}
			else if(c == 60) {
				b[c] = new JButton("N");
				b[c].setEnabled(false);
			}
			else if(c >= 48 && c <= 55) {
				b[c] = new JButton("I");
				b[c].setEnabled(false);
			}
			else
				b[c] = new JButton("");
			bo2 = 1;
			if(bo)
				bo2 = 0;
			b[c].setFont(new Font("Chess Alpha 2", Font.PLAIN, 80));
			if(c%2 == bo2)
				b[c].setBackground(new Color(22,116,50));
			else
				b[c].setBackground(Color.WHITE);
			b[c].setBounds(x, y,100,100);
			x+=100;
			if(x >= 800) {
				bo2 = 0;
				bo = !bo;
				x = 10;
				y-=100;
			}
			b[c].addActionListener(al);
			b[c].setBorderPainted(false);
			p.add(b[c++]);
		}
		f.add(p);
		f.setVisible(true);
	}
	private class Actionlistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int c = 0;
			while(c < 64) {
				if(e.getSource() == b[c]) {
					if(!b[c].getText().equals("")) {
						if(b[c].getText().equalsIgnoreCase("k")) {
							check = 0;
							knight(c,b[c].getText());
						}
						else if(b[c].getText().equalsIgnoreCase("j")) {
							check = 0;
							bishop(c,b[c].getText());
						}
						else if(b[c].getText().equalsIgnoreCase("l")) {
							check = 0;
							rook(c,b[c].getText());
						}
						else if(b[c].getText().equalsIgnoreCase("m")) {
							check = 0;
							queen(c,b[c].getText());
						}
						else if(b[c].getText().equalsIgnoreCase("i")) {
							check = 0;
							pawn(c,b[c].getText());
						}
						else if(b[c].getText().equalsIgnoreCase("n")) {
							check = 0;
							king(c,b[c].getText());
						}
					}
					if(e.getSource() == b[c] && b[c].getText().equals("-")) {
						int d = 0;
						enw = 0;
						enb = 0;
						ens[0] = 0;
						ens[1] = 0;
						chance++;
						while(d < 64) {
							if(b[d].getText().equals("-")) {
								b[d].setText(re[d]);
							}
							d++;
						}
						d = 0;
						while(d < 64)
							re[d++] = "";
						d = 0;
						if(m.equals("i") && r == c-16) {
							if((b[c-1].getText().equals("I") && c%8 != 0)) {
								enb++;
								ens[0] = c-1;
							}
							else if((b[c+1].getText().equals("I") && c%8 !=7)) {
								enb++;
								ens[1] = c+1;
							}
						}
						if(m.equals("I") && r == c+16) {
							if((b[c-1].getText().equals("i") && c%8 != 0)) {
								enw++;
								ens[0] = c-1;
							}
							else if((b[c+1].getText().equals("i") && c%8 !=7)) {
								enw++;
								ens[1] = c+1;
							}
						}
						if(c == r+9 && b[r].getText().equals("i") && b[c].getText().equals(""))
							b[c-8].setText("");
						else if(c == r+7 && b[r].getText().equals("i") && b[c].getText().equals(""))
							b[c-8].setText("");
						else if(c == r-7 && b[r].getText().equals("I") && b[c].getText().equals(""))
							b[c+8].setText("");
						else if(c == r-9 && b[r].getText().equals("I") && b[c].getText().equals(""))
							b[c+8].setText("");
						if(m.equals("i") && c <= 63 && c >= 56) {
							int p = -1;
							while(p < 0) {
							    String[] o = new String[] {"m", "l", "k", "j"};
							    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.PLAIN, 40)));
							    UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Chess Alpha 2",Font.PLAIN,40)));
							    p = JOptionPane.showOptionDialog(null, "Promote Pawn ?", "Pawn Promotion",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, o, o[0]);
							    if(p == 0)
							    	m = "m";
							    else if(p == 1)
							    	m = "l";
							    else if(p == 2)
							    	m = "k";
							    else if(p == 3)
							    	m = "j";
							}
						}
						if(m.equals("I") && c <= 7 && c >= 0) {
							int p = -1;
							while(p < 0) {
								String[] o = new String[] {"M", "L", "K", "J"};
							    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.PLAIN, 40)));
							    UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Chess Alpha 2",Font.PLAIN,40))); 
							    p = JOptionPane.showOptionDialog(null, "Promote Pawn?", "Pawn Promotion",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, o, o[0]);
							    if(p == 0)
							    	m = "M";
							    else if(p == 1)
							    	m = "L";
							    else if(p == 2)
							    	m = "K";
							    else if(p == 3)
							    	m = "J";
							}
						}
						if(m.equalsIgnoreCase("n")) {
							if(ksc) {
								b[c-1].setText(b[c+1].getText());
								b[c+1].setText("");
							}
							else if(qsc) {
								b[c+1].setText(b[c-2].getText());
								b[c-2].setText("");
							}
						}
						b[c].setText(m);
						b[r].setText("");
						if(undercheck) {
							checkforcheck(invertCase(m));
							if(undercheck) {
								b[r].setText(m);
								b[c].setText("");
								chance--;
								break;
							}
						}
						ca = 1;
						checkforcheck(invertCase(m));
						if(undercheck) {
						    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.PLAIN, 20)));
						    UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,20)));
							JOptionPane.showMessageDialog(null, "That Puts Your Own King Under Check!","CHECK!",JOptionPane.WARNING_MESSAGE);
							b[r].setText(m);
							b[c].setText("");
							chance--;
							undercheck = false;
							break;
						}
						ca = 0;
						if(m.equals("n"))
							wkm++;
						else if(m.equals("N"))
							bkm++;
						else if(m.equalsIgnoreCase("l") && r == 0)
							wqrm++;
						else if(m.equalsIgnoreCase("l") && r == 7)
							wkrm++;
						else if(m.equalsIgnoreCase("l") && r == 56)
							bqrm++;
						else if(m.equalsIgnoreCase("l") && r == 63)
							bkrm++;
						d = 0;
						while(d < 64) {
							if(chance%2 == 0) {
								if(!b[d].getText().equals("") && Character.isUpperCase(b[d].getText().charAt(0))) {
									b[d].setEnabled(false);
								}
								else
									b[d].setEnabled(true);
							}
							else {
								if(!b[d].getText().equals("") && Character.isLowerCase(b[d].getText().charAt(0))) {
									b[d].setEnabled(false);
								}
								else
									b[d].setEnabled(true);
							}
							d++;
						}
						checkforcheck(m);
					}
				}
				c++;
			}
		}
	}
}