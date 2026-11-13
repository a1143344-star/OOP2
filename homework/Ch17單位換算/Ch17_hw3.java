import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ch17_hw3 {
    static JFrame frm = new JFrame("單位換算器");

    public static void main(String args[]) {
        BorderLayout border = new BorderLayout();
        frm.setLayout(border);
        frm.setSize(480, 280);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. NORTH：JComboBox 選擇換算類型
        String[] types = {"長度", "重量", "溫度"};
        JComboBox<String> typeBox = new JComboBox<>(types);
        
        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("選擇換算類型："));
        northPanel.add(typeBox);
        frm.add(northPanel, BorderLayout.NORTH);

        // 2. CENTER：兩列，每列一個 JTextField + 一個 JComboBox
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        // 第一列：來源數值與來源單位
        JPanel row1 = new JPanel();
        JTextField tfSource = new JTextField(10);
        JComboBox<String> cbSource = new JComboBox<>();
        row1.add(new JLabel("來源數值："));
        row1.add(tfSource);
        row1.add(cbSource);

        // 第二列：目標數值（顯示結果）與目標單位
        JPanel row2 = new JPanel();
        JTextField tfTarget = new JTextField(10);
        tfTarget.setEditable(false); // 結果欄位設為唯讀
        JComboBox<String> cbTarget = new JComboBox<>();
        row2.add(new JLabel("換算結果："));
        row2.add(tfTarget);
        row2.add(cbTarget);

        centerPanel.add(row1);
        centerPanel.add(row2);
        frm.add(centerPanel, BorderLayout.CENTER);

        // 初始化單位下拉選單的選項
        updateUnits(typeBox, cbSource, cbTarget);

        // 切換換算類型時，動態更新單位 JComboBox 的選項
        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnits(typeBox, cbSource, cbTarget);
            }
        });

        // 3. SOUTH：「換算」按鈕
        JButton btnCalc = new JButton("換算");
        JPanel southPanel = new JPanel();
        southPanel.add(btnCalc);
        frm.add(southPanel, BorderLayout.SOUTH);

        // 點擊換算按鈕的邏輯
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double val = Double.parseDouble(tfSource.getText());
                    String type = (String) typeBox.getSelectedItem();
                    String srcUnit = (String) cbSource.getSelectedItem();
                    String tgtUnit = (String) cbTarget.getSelectedItem();
                    
                    double result = convert(type, srcUnit, tgtUnit, val);
                    
                    // 計算結果取小數點後第四位，避免畫面雜亂
                    tfTarget.setText(String.format("%.4f", result));
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frm, "請輸入有效的數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frm.setVisible(true);
    }

    // 根據選擇的類型更新單位選單
    private static void updateUnits(JComboBox<String> typeBox, JComboBox<String> cbSource, JComboBox<String> cbTarget) {
        String selectedType = (String) typeBox.getSelectedItem();
        cbSource.removeAllItems();
        cbTarget.removeAllItems();

        String[] units;
        if ("長度".equals(selectedType)) {
            units = new String[]{"公尺", "公分", "英吋", "英尺"};
        } else if ("重量".equals(selectedType)) {
            units = new String[]{"公斤", "公克", "磅", "盎司"};
        } else { // 溫度
            units = new String[]{"攝氏", "華氏", "克氏"};
        }

        for (String u : units) {
            cbSource.addItem(u);
            cbTarget.addItem(u);
        }
        
        // 切換大分類時，讓目標單位的預設選項自動跳到第二個項目（例如公分、公克等）
        if (cbTarget.getItemCount() > 1) {
            cbTarget.setSelectedIndex(1); 
        }
    }

    // 換算核心邏輯
    private static double convert(String type, String src, String tgt, double val) {
        if (type.equals("長度")) {
            double meters = val;
            switch (src) {
                case "公尺": meters = val; break;
                case "公分": meters = val * 0.01; break;
                case "英吋": meters = val * 0.0254; break;
                case "英尺": meters = val * 0.3048; break;
            }
            switch (tgt) {
                case "公尺": return meters;
                case "公分": return meters / 0.01;
                case "英吋": return meters / 0.0254;
                case "英尺": return meters / 0.3048;
            }
        } else if (type.equals("重量")) {
            double kg = val;
            switch (src) {
                case "公斤": kg = val; break;
                case "公克": kg = val * 0.001; break;
                case "磅": kg = val * 0.45359237; break;
                case "盎司": kg = val * 0.0283495231; break;
            }
            switch (tgt) {
                case "公斤": return kg;
                case "公克": return kg / 0.001;
                case "磅": return kg / 0.45359237;
                case "盎司": return kg / 0.0283495231;
            }
        } else if (type.equals("溫度")) {
            double celsius = val;
            switch (src) {
                case "攝氏": celsius = val; break;
                case "華氏": celsius = (val - 32) * 5.0 / 9.0; break;
                case "克氏": celsius = val - 273.15; break;
            }
            switch (tgt) {
                case "攝氏": return celsius;
                case "華氏": return celsius * 9.0 / 5.0 + 32;
                case "克氏": return celsius + 273.15;
            }
        }
        return val;
    }
}