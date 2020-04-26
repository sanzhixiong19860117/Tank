package com.joy.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 11:16
 */
public class TankFrame extends Frame {
    int x = 200;
    int y = 200;

    public TankFrame() {
        setVisible(true);                 //是否显示
        setTitle("tank wark");            //设置title
        setResizable(false);              //是否改变大小
        setSize(800, 600); //设置高度和宽度
        //是否关闭当前界面
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //窗口重新绘制的时候操作
    @Override
    public void paint(Graphics graphics) {
        System.out.println("paint");
        graphics.fillRect(x, y, 50, 50);
        //运动就是x或者y的数值进行赋值
        x += 10;
        y += 10;
    }
}
