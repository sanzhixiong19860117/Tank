## new出一个窗口
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