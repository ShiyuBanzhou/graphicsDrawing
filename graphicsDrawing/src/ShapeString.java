/**
 * 定义ShapeString类，该类是用来文字输出的类
 */
import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics2D;

public class ShapeString extends Shape{
    private String str;
    public ShapeString(int x1, int y1, int x2, int y2, Color color, String str, BasicStroke s) {
        super(x1, y1, x2, y2,color,s);
        this.str = str;
    }
    public void draw(Graphics2D g){
        g.setStroke(getStroke());                   // 画笔大小
        g.setColor(getColor());                     //设置画笔颜色
        g.drawString(this.str, getX1(), getY1());
    }

}
	