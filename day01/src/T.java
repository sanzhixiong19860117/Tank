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
