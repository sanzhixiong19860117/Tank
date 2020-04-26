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

