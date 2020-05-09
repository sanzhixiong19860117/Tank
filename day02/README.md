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

## 4.子弹的操作

思路：定义一个子弹类，然后跟tank类一样，在屏幕当中画出来

```java
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
        graphics.fillOval(x, y, Width, Height);
        graphics.setColor(Color.RED);
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
```

## 5.用双缓冲解决闪烁

```java
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
```

只需要在tankFrame类增加这个更新方法就好了。

## 6.按下键盘发射子弹

1.获得桌面对应的引用

```java
private Tank myTank = new Tank(200, 200, Dir.DOWN,this);//这个地方的
//this是指TankFrame这个对象
```

2.在构造函数当中加入对应的参数

```java
private TankFrame tf = null;//增加了桌面的对象引用
public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }
```

3.在按下ctrl下tank发射子弹的方法

```java
case KeyEvent.VK_CONTROL://按下ctrl下操作开火
                    myTank.fire();
```

4.在tank类中写一个方法开火方法

```java
//开火
    public void fire() {
        this.tf.tankBullet = new Bullet(this.x, this.y, this.dir);
    }
```



## 