package com.joy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    //移动的速度
    private static final int speed = 10;//速度
    //初始化方向
    Dir dir = Dir.DOWN;

    public TankFrame() {
        setVisible(true);                 //是否显示
        setTitle("tank wark");            //设置title
        setResizable(false);              //是否改变大小
        setSize(800, 600); //设置高度和宽度

        //键盘监听
        this.addKeyListener(new MyKeyListener());

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
        graphics.fillRect(x, y, 50, 50);
        //运动就是x或者y的数值进行赋值
//        x += 10;
//        y += 10;
        //进行移动
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
    }

    //键盘监听处理类
    class MyKeyListener extends KeyAdapter {
        //思路
        /**
         * 先确定方向，然后在这个方向状态，在处理对应的移动
         */
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();//使用code或者对应的数据
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();//使用code或者对应的数据
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bL) dir = Dir.LEFT;
            if (bR) dir = Dir.RIGHT;
            if (bU) dir = Dir.UP;
            if (bD) dir = Dir.DOWN;
        }
    }
}
