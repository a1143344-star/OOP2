import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ch17_hw1 extends JFrame implements ActionListener {

    // 宣告為類別屬性（成員變數），以便在 actionPerformed 函式中讀取
    private JTextField userText;
    private JPasswordField passwordText;
    private JPanel panel;

    public Ch17_hw1() {
        setTitle("使用者登入");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 帳號標籤與輸入框
        JLabel userLabel = new JLabel("帳號:");
        userLabel.setBounds(40, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20); // 賦值給類別屬性
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // 密碼標籤與輸入框
        JLabel passwordLabel = new JLabel("密碼:");
        passwordLabel.setBounds(40, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20); // 賦值給類別屬性
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        // 登入按鈕
        JButton loginButton = new JButton("登入");
        loginButton.setBounds(100, 90, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String username = userText.getText();
        String password = new String(passwordText.getPassword());

        // 簡單驗證範例
        if ("admin".equals(username) && "1234".equals(password)) {
            JOptionPane.showMessageDialog(panel, "登入成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(panel, "帳號或密碼錯誤！", "警告", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Ch17_hw1();
    }
}