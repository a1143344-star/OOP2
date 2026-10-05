import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
public class Ch17_ex1 extends JFrame implements ActionListener{
    static Ch17_ex1 frm=new Ch17_ex1();
    static JPanel pne=new JPanel(new GridLayout(3, 1));
	static JPanel btnPanel = new JPanel();
    static JButton btn=new JButton("擲骰子");
    static JLabel lab1=new JLabel("0");
    static JLabel lab2=new JLabel("已擲0次，總和0，平均0.00");

    static int count=0;
    static int sum=0;

    static Random ran=new Random();

    public static void main(String args[]){
        btn.addActionListener(frm);
        BorderLayout border=new BorderLayout(2,5);
        frm.setLayout(border);
        frm.setSize(400, 320);
        lab1.setFont(new Font("Arial", Font.BOLD, 60)); 
        frm.setTitle("骰子模擬器");

        pne.add(lab2); 
        pne.add(lab1); 
		btnPanel.add(btn);
		pne.add(btnPanel);

        lab1.setHorizontalAlignment(JLabel.CENTER);
        lab2.setHorizontalAlignment(JLabel.CENTER);
        frm.add(pne, BorderLayout.CENTER);

        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        int roll=ran.nextInt(6)+1;
            count++;
            sum+=roll;
            lab1.setText(""+roll);
            if(roll==6){
                lab1.setForeground(Color.GREEN);
            }else if(roll==1){
                lab1.setForeground(Color.RED);
            }else{
                lab1.setForeground(Color.BLACK);
            }
            double a=(double)sum/count;
            lab2.setText(String.format("已擲%d次，總和%d，平均%.2f", count, sum, a));
    }
}