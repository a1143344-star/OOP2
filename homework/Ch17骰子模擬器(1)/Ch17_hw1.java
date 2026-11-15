import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Ch17_hw1 extends JFrame {
    private JLabel str = new JLabel("已擲1次，總和6，平均6.00");
    private JLabel show = new JLabel("6");
    private JButton btn = new JButton("擲骰子");

    public Ch17_hw1() {
        setTitle("骰子模擬器");
        setSize(400, 320); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. 設定頂端面板與內邊距 (上、左、下、右)，讓文字與上方保持距離
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(25, 0, 0, 0)); // 上方留 25 像素的空間

        str = new JLabel("已擲 1 次，總和 6，平均 6.00");
		str.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
        str.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(str);
        add(topPanel, BorderLayout.NORTH);

        // 2. 設定中間點數的字體大小 (60pt) 與顏色邏輯
        show.setFont(new Font("Arial", Font.BOLD, 60)); // 設定 60pt 粗體
        
        // 假設初始點數為 6，設定對應顏色 (6 綠色、1 紅色、其餘黑色)
        int initialDice = 6; 
        show.setText(String.valueOf(initialDice));
        if (initialDice == 6) {
            show.setForeground(new Color(0, 150, 0)); // 綠色
        } else if (initialDice == 1) {
            show.setForeground(Color.RED); // 紅色
        } else {
            show.setForeground(Color.BLACK); // 其餘黑色
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(show);
        add(centerPanel, BorderLayout.CENTER);

        // 3. 底部按鈕面板
        JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EmptyBorder(0, 0, 45, 0));
		btn.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
        bottomPanel.add(btn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ch17_hw1(); // 建立視窗物件
            }
        });
    }
}