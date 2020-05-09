package com.joy.tank;

import java.awt.*;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/10 0:04
 * 子弹类
 */
public class Bullet {
    private static final int Speed = 10;
    private static final int Width = 30;
    private static final int Height = 30;
    private int x, y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics graphics) {
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
    }
}
