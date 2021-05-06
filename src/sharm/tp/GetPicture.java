package sharm.tp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GetPicture {
    public static void main(String[] args) throws IOException {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(basePath);

        // 读取图像
        //相对路径
        File imageEx = new File("src\\com\\atguigu\\tp\\default.jpg");
        //绝对路径 可以根据 out 文件夹来
//        File imageEx = new File(basePath + "com\\atguigu\\tp\\default.jpg");
        Image image = ImageIO.read(imageEx);
        // 显示图像
        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 570);
        // f.setResizable(false);
        frame.setLocation(300, 100);
        //关闭窗口--退出调试
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
