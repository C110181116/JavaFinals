import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vacanciesFilter{
    public static void main(String[] args) {
        JFrame askWeb = new JFrame("選擇查詢的網頁");
        FlowLayout fl = new FlowLayout();
        askWeb.setLayout(fl);
        askWeb.setSize(300,150);
        askWeb.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        askWeb.setVisible(true);

        JButton yourator =new JButton("yourator");
        askWeb.add(yourator);

        JButton Cakeresume =new JButton("Cakeresume");
        askWeb.add(Cakeresume);

        JButton Tasker =new JButton("Tasker");
        askWeb.add(Tasker);

        //yourator按鈕事件
        yourator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Cakeresume按鈕事件
        Cakeresume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Tasker按鈕事件
        Tasker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
