package com.joy.tank;

import java.awt.*;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/9 23:15
 */
public class Tank {
    private int x;
    private int y;
    //移动的速度
    private static final int speed = 10;//速度
    //初始化方向
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private TankFrame tf = null;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.yellow);
        graphics.fillRect(x, y, 50, 50);
        graphics.setColor(c);
        this.move();
    }

    private void move() {
        if (!this.moving) return;
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

    //开火
    public void fire() {
        this.tf.bullets.add(new Bullet(this.x, this.y, this.dir));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
