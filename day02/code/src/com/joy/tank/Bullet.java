package com.joy.tank;

import java.awt.*;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/10 0:04
 * 子弹类
 */
public class Bullet {
    private static final int Speed = 20;
    private static final int Width = 30;
    private static final int Height = 30;
    private int x, y;
    private Dir dir;

    private boolean live = true;//是否存在
    private TankFrame tf = null;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics graphics) {
        if (!live) {
            this.tf.bullets.remove(this);
        }
        Color c = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillOval(x, y, Width, Height);
        this.move();
    }

    private void move() {
        //进行移动
        switch (dir) {
            case LEFT:
                x -= Speed;
                break;
            case RIGHT:
                x += Speed;
                break;
            case UP:
                y -= Speed;
                break;
            case DOWN:
                y += Speed;
                break;
        }

        //判断移动出边界死亡操作
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }
}
