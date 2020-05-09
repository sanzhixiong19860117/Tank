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

    //tank类
    private Tank myTank = new Tank(200, 200, Dir.DOWN,this);

    //子弹类
    public Bullet tankBullet = new Bullet(300, 300, Dir.DOWN);

    //宽度高度的常量
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    public TankFrame() {
        setVisible(true);                 //是否显示
        setTitle("tank wark");            //设置title
        setResizable(false);              //是否改变大小
        setSize(GAME_WIDTH, GAME_HEIGHT); //设置高度和宽度

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

    //双缓冲操作
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (null == offScreenImage) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }//定义一个屏幕外的图像
        Graphics gBkImg = offScreenImage.getGraphics();//获取屏幕外的图像的画笔
        gBkImg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);//清空屏幕外的图像
        this.paint(gBkImg);   //将图像画到屏幕外的图像上
        g.drawImage(offScreenImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, this);//将屏幕外的图像画到屏幕上
    }

    //窗口重新绘制的时候操作
    @Override
    public void paint(Graphics graphics) {
        myTank.paint(graphics);
        tankBullet.paint(graphics);
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
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) {
                myTank.setMoving(false);
            } else {
                System.out.println("有移动");
                myTank.setMoving(true);
            }
            if (bL) myTank.setDir(Dir.LEFT);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bU) myTank.setDir(Dir.UP);
            if (bD) myTank.setDir(Dir.DOWN);
        }
    }
}
