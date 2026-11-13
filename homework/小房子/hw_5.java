import javax.swing.*;
import java.awt.*;

public class hw_5 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. 天空與草地：上半部填入天藍色，下半部填入綠色
        g.setColor(new Color(135, 206, 250)); // 天藍色
        g.fillRect(0, 0, 500, 250);
        
        g.setColor(new Color(60, 179, 113)); // 綠色草地
        g.fillRect(0, 250, 500, 150);

        // 2. 太陽：用 fillOval 畫黃色正圓，並用 drawLine 畫 8 條光芒
        int sunX = 420, sunY = 70, sunRadius = 50;
        g.setColor(Color.YELLOW);
        g.fillOval(sunX - sunRadius / 2, sunY - sunRadius / 2, sunRadius, sunRadius);
        
        // 畫 8 條光芒（利用 for 迴圈搭配 Math.cos、Math.sin 計算座標）
        g.setColor(new Color(255, 215, 0));
        int innerR = 32;
        int outerR = 45;
        for (int i = 0; i < 8; i++) {
            double angle = i * 2 * Math.PI / 8;
            int x1 = (int) (sunX + innerR * Math.cos(angle));
            int y1 = (int) (sunY + innerR * Math.sin(angle));
            int x2 = (int) (sunX + outerR * Math.cos(angle));
            int y2 = (int) (sunY + outerR * Math.sin(angle));
            g.drawLine(x1, y1, x2, y2);
        }

        // 3. 雲朵：用至少 3 個互相重疊的白色 fillOval 組成
        g.setColor(Color.WHITE);
        int cloudX = 80, cloudY = 60;
        g.fillOval(cloudX, cloudY, 60, 35);
        g.fillOval(cloudX + 35, cloudY - 15, 70, 45);
        g.fillOval(cloudX + 75, cloudY, 50, 35);

        // 4. 房屋主體：用 fill + draw 矩形
        int houseX = 170, houseY = 200, houseW = 160, houseH = 120;
        g.setColor(new Color(245, 222, 179)); // 小麥色房屋
        g.fillRect(houseX, houseY, houseW, houseH);
        g.setColor(Color.BLACK);
        g.drawRect(houseX, houseY, houseW, houseH);

        // 5. 屋頂：只能用 drawLine 畫出三角形（三條線段）
        int peakX = houseX + houseW / 2; // 屋頂頂點
        int peakY = houseY - 60;
        int leftX = houseX - 10;
        int leftY = houseY;
        int rightX = houseX + houseW + 10;
        int rightY = houseY;
        
        g.setColor(new Color(178, 34, 34)); // 磚紅色屋頂線條
        g.drawLine(peakX, peakY, leftX, leftY);     // 左斜邊
        g.drawLine(peakX, peakY, rightX, rightY);   // 右斜邊
        g.drawLine(leftX, leftY, rightX, rightY);   // 底邊

        // 6. 門：用 fillRoundRect，門把用小的 fillOval[cite: 1]
        int doorW = 40, doorH = 70;
        int doorX = houseX + houseW / 2 - doorW / 2;
        int doorY = houseY + houseH - doorH;
        g.setColor(new Color(139, 69, 19)); // 棕色門
        g.fillRoundRect(doorX, doorY, doorW, doorH, 10, 10);
        
        // 門把
        g.setColor(Color.YELLOW);
        g.fillOval(doorX + 28, doorY + doorH / 2, 6, 6);

        // 7. 窗戶：至少 2 扇，用 fillRect + drawRect，並用 drawLine 畫十字窗框[cite: 1]
        int winW = 30, winH = 30;
        
        // 左窗戶
        int win1X = houseX + 20, win1Y = houseY + 25;
        g.setColor(new Color(173, 216, 230)); // 淺藍色窗戶玻璃
        g.fillRect(win1X, win1Y, winW, winH);
        g.setColor(Color.BLACK);
        g.drawRect(win1X, win1Y, winW, winH);
        g.drawLine(win1X + winW / 2, win1Y, win1X + winW / 2, win1Y + winH); // 垂直窗框
        g.drawLine(win1X, win1Y + winH / 2, win1X + winW, win1Y + winH / 2); // 水平窗框

        // 右窗戶
        int win2X = houseX + houseW - 50, win2Y = houseY + 25;
        g.setColor(new Color(173, 216, 230));
        g.fillRect(win2X, win2Y, winW, winH);
        g.setColor(Color.BLACK);
        g.drawRect(win2X, win2Y, winW, winH);
        g.drawLine(win2X + winW / 2, win2Y, win2X + winW / 2, win2Y + winH);
        g.drawLine(win2X, win2Y + winH / 2, win2X + winW, win2Y + winH / 2);

        // 8. 用 g.drawString("學號 姓名", x, y) 在畫面角落簽名[cite: 1]
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString("A1143344 張鈞甯", 20, 380);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("我的小房子風景畫");
        hw_5 panel = new hw_5();
        
        // 設定畫布大小 500x400[cite: 1]
        panel.setPreferredSize(new Dimension(500, 400));
        
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 視窗置中
        frame.setVisible(true);
    }
}