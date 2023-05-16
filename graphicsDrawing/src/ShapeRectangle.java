/**
 * 定义ShapeRectangle类，该类是用来绘制圆形的类
 */
import java.awt.*;

public class ShapeRectangle extends Shape {

    public ShapeRectangle(int x1, int y1, int x2, int y2, Color color,
                       BasicStroke s) {
        super(x1, y1, x2, y2, color, s);

    }

    public void draw(Graphics2D g) {
        g.setStroke(getStroke());                   // 画笔大小
        g.setColor(getColor());                     // 设置画笔颜色
        g.drawRect(getX1(), getY1(),
                getX2(), getY2());                  // 绘制矩形的方法

    }
}