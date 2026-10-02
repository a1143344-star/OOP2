import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
public class Ch17_ex1 extends JFrame{
    static JFrame frm=new JFrame("骰子模擬器");
    static JPanel pne=new JPanel(new GridLayout(2, 1));
    static JButton btn=new JButton("擲骰子");
    static JLabel lab1=new JLabel("0");;
    static JLabel lab2=new JLabel("已擲0次，總和0，平均0.00");;

    static int count=0;
    static int sum=0;

    static Random ran=new Random();

    public static void main(String args[]){
        BorderLayout border=new BorderLayout(2,5);
        frm.setLayout(border);
        frm.setSize(400, 320);
        lab1.setFont(new Font("Arial", Font.BOLD, 60)); 

        pne.add(lab2);
        pne.add(lab1);

        lab1.setHorizontalAlignment(JLabel.CENTER);
        lab2.setHorizontalAlignment(JLabel.CENTER);
        frm.add(pne, BorderLayout.CENTER);
        frm.add(btn, BorderLayout.SOUTH);
        
        btn.addActionListener(e->{
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
            lab2.setText("已擲"+count+"次，總和"+sum+"，平均"+a);
        });
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}