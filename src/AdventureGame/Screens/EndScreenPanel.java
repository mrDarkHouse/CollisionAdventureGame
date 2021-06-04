package AdventureGame.Screens;

import AdventureGame.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class EndScreenPanel extends JPanel {

    public EndScreenPanel(boolean win) {
        setLayout(null);
        JPanel p = new JPanel(){{
            setLayout(new GridLayout(2, 1));
            JLabel text = new JLabel(win ? "YOU WIN!!!":"GAME OVER", SwingConstants.CENTER);
            text.setFont(new Font("Arial", Font.PLAIN, 72));
            add(text);
            JButton restart = new JButton("EXIT");
            restart.setFont(new Font("Arial", Font.PLAIN, 30));
            add(restart);
            restart.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    restart.setVisible(false);
                    Main.main.exitGame();
                }
            });
        }};
        p.setBounds(250, 50, Main.FRAME_WIDTH - 500, Main.FRAME_HEIGHT - 100);
        p.setBorder(new LineBorder(Color.BLACK, 3));
        p.setFocusable(true);
        p.setVisible(true);
        add(p);


    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = ((Graphics2D) g);
//        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
        super.paint(g);
//        g2.setColor(Color.BLACK);
//        g2.setFont(new Font("Arial", Font.PLAIN, 48));
//        g2.drawString("GAME OVER", 40, 40);
    }
}
