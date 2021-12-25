import javax.swing.*;
import java.awt.*;
import java.awt.event.*; //事件
public class Test {
    public static void main(String args[]) {
        win1 bb = new win1();
    }
}


class win1 extends JFrame {
    public win1() {
        super("win");
        setSize(250,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        //容器
        Container pn = getContentPane();
        //設定成FlowLayout
        FlowLayout fy = new FlowLayout();
        pn.setLayout(fy);


        //建立button
        JButton bt1 = new JButton("enter");
        pn.add(bt1);

        JButton bt2 = new JButton("exit");
        pn.add(bt2);



        //bt1事件的動作
        bt1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"11111111"); //呼叫msgbox
            }
        });

        //bt2事件的動作
        bt2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"22222222"); //呼叫msgbox
            }
        });
        setContentPane(pn);
    }
}