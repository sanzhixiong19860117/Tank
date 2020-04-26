# Tank
## 第一天学习

### new出一个窗口

```java
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 10:58
 */
public class T {
    public static void main(String[] args) {
        //这个是java的窗口类
        Frame f = new Frame();              //内存中创建
        f.setVisible(true);                 //是否显示
        f.setTitle("tank wark");            //设置title
        f.setResizable(false);              //是否改变大小
        f.setSize(800, 600); //设置高度和宽度
        //是否关闭当前界面
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
```

核心知识点：

使用Frame窗口类的基础使用，显示，title标签的修改，大小的修改，是否可以修改大小，以及关闭按钮的事件操作，详情请查看aip的操作。

### TankFrame从Frame继承

1.new一个TankeFrame类，然后继承Frame类

```java
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
```

2.在刚才的T类操作创建TankeFrame类

代码如下：

```java
import com.joy.tank.TankFrame;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/26 10:58
 */
public class T {
    public static void main(String[] args) {
        //这个是java的窗口类
        TankFrame tankFrame = new TankFrame();
    }
}

```



### 重写paint方法

重写paint方法

```java
//窗口重新绘制的时候操作
    @Override
    public void paint(Graphics graphics) {
        System.out.println("paint");
        graphics.fillRect(200, 200, 50, 50);//画一个坐标在200，200当中大小为50，50的长方形
    }
```

1.@Override是重写父类的方法

2.它重写Container.java里面的方法

```java
public void paint(Graphics g) {
        if (isShowing()) {
            synchronized (getObjectLock()) {
                if (printing) {
                    if (printingThreads.contains(Thread.currentThread())) {
                        return;
                    }
                }
            }

            // The container is showing on screen and
            // this paint() is not called from print().
            // Paint self and forward the paint to lightweight subcomponents.

            // super.paint(); -- Don't bother, since it's a NOP.

            GraphicsCallback.PaintCallback.getInstance().
                runComponents(getComponentsSync(), g, GraphicsCallback.LIGHTWEIGHTS);
        }
    }
```

3.paint的方法是每次打开windows窗口的时候就会出现修改，利用这个方法，我让方形进行运动，x和y都进行累加操作，进行运动。

最后的代码如下：

```java
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
```

### 按一键让其运动起来

1.监听键盘事件

```java
this.addKeyListener(new MyKeyListener());
```

2.定义一个内部类

```java
//键盘监听处理类
    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //按钮下
            System.out.println("keyPressed");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //被弹起来操作
            System.out.println("keyReleased");
        }
    }
```

重写了KayAdapter的两个方法，一个是按钮，一个是抬起。

正常操作按下，可以运动按下以后然后x重新赋值

```java
@Override
        public void keyPressed(KeyEvent e) {
            //按钮下
            System.out.println("keyPressed");
            x += 200;
        }
```

这样就可以让每次点击一下就可以，但是我们发现并不是每次点击就会运动，因为没有更新当前界面造成，然后我们调用repaint的方法进行界面的更新操作。

代码如下：

```java
@Override
        public void keyPressed(KeyEvent e) {
            //按钮下
            System.out.println("keyPressed");
            x += 200;
            repaint();
        }
```

马上运行可以看到现在可以正常的操作了。

问题：如果是没有按的其他的东西要运动怎么办？

答：使用while（true）进行刷新，这个就类似于cocos里面的帧率刷新操作。

我们在T这个类中怎么while（ture）操作

```java
public static void main(String[] args) {
        //这个是java的窗口类
        TankFrame tankFrame = new TankFrame();
        while (true) {
            try {
                Thread.sleep(100);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
```

1.while(true)进行死循环，然后在Thread.sleep这个是每个100毫秒让当前线程停止一下，然后在调用repaint()这个方法重新绘制地图，在TankFrame类中的paint方法中修改x和y进行运动

```java
@Override
    public void paint(Graphics graphics) {
        System.out.println("paint");
        graphics.fillRect(x, y, 50, 50);
        //运动就是x或者y的数值进行赋值
        x += 10;
        y += 10;
    }
```

这样我们就可以看到对应的运动效果。这个是最基本的java gui的基本运动效果操作。

### 键盘的数值

控制是获得对应的数据

带入如下

```java
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
```

key是从系统的事件，获得当前按的数据，在根据key对应的常量数据，为左的话x减去一个固定的数值，为右的时候则是x加上某一个固定的数值，上下都是一样的效果。