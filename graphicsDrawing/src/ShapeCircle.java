/**
 * 定义ShapeCircle类，该类是用来绘制圆形的类
 */
import java.awt.*;

public class ShapeCircle extends Shape {

    public ShapeCircle(int x1, int y1, int x2, int y2, Color color,
                       BasicStroke s) {
        super(x1, y1, x2, y2, color, s);

    }

    public void draw(Graphics2D g) {
        g.setStroke(getStroke());                 // 画笔大小
        g.setColor(getColor());                     // 设置画笔颜色
        g.drawOval(getX1(), getY1(), getX2() - getX1(),
                getY2() - getY1());         // 绘制圆的方法

    }
}