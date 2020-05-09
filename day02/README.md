# 第二天学习

## 1.tank的方向控制

首先创建一个结构体，给出tank走动的四个方向

```java
package com.joy.tank;
/**
 * @author joy
 * @version 1.0
 * @date 2020/5/9 22:38
 */
public enum Dir {
    LEFT,
    RIGHT,
    UP,
    DOWN
}
```

第二：根据按键给定方向

```java
//根据方向新增加的方法
private void setMainTankDir() {
            if (bL) dir = Dir.LEFT;
            if (bR) dir = Dir.RIGHT;
            if (bU) dir = Dir.UP;
            if (bD) dir = Dir.DOWN;
        }
```

第三：根据方向进行移动

```java
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
```

## 2.创建多个tank

1.首先对tank类进行封装，创建一个tank类

```java
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

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics graphics){
        graphics.fillRect(x, y, 50, 50);
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
}
```

2.在桌面上进行按钮的操作

```java
private void setMainTankDir() {
    //myTank就是我自己new出来的tank实体类
            if (bL) myTank.setDir(Dir.LEFT);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bU) myTank.setDir(Dir.UP);
            if (bD) myTank.setDir(Dir.DOWN);
        }
```

## 3.处理tank静止状态

思路：首先有一个变量它是否是移动状态，当按下某一个方向键的时候才能移动操作，如果没有按下就不用移动。

1.定义变量

```java
private boolean moving = false;
```

2.按下某一个键以后进行移动，就是move为ture

```java
private void setMainTankDir() {
    		//如果没有按下任何键就不动否则就要移动
            if (!bL && !bR && !bU && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
            }
            if (bL) myTank.setDir(Dir.LEFT);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bU) myTank.setDir(Dir.UP);
            if (bD) myTank.setDir(Dir.DOWN);
        }
```

3.在移动的地方判断这个移动状态是否为true如果为false，直接就退出去，在tank类里面增加一个移动的判断

```java
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
```



## 