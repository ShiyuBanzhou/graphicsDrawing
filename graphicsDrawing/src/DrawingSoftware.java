import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingSoftware implements ActionListener, MouseListener,
        MouseMotionListener{
    //初始化默认画笔、颜色、坐标值
    private String text;                //用于存储文字输出的字符串
    private Color color = Color.black;  // 声明颜色属性，用来存储用户选择的颜色
    private Graphics g;                 // 声明Graphics画笔类的对象名属性
    private int x1, y1, x2, y2;         // 坐标
    private Shape shape;                // 声明图形对象名
    private JFrame frame;
    private JPanel panelDraw;
    private int flag = 0;
    private int width;
    private JSlider slider;
    private int eraserWidth;
    private JSlider eraserSlider;
    public DrawingSoftware(){
        prepareGUI();
    }
    private void prepareGUI(){
        slider = new JSlider(JSlider.HORIZONTAL, 1, 12, 6);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        JLabel label = new JLabel("画笔粗细：");
        eraserSlider = new JSlider(JSlider.HORIZONTAL, 1, 12, 6);
        eraserSlider.setMinorTickSpacing(1);
        eraserSlider.setMajorTickSpacing(5);
        eraserSlider.setPaintTicks(true);
        eraserSlider.setPaintLabels(true);
        JLabel eraserLabel = new JLabel("橡皮大小：");
        panelDraw = new JPanel();
        frame = new JFrame("画图软件");
        frame.setSize(1280, 960);
        frame.add(prepareMenu(), BorderLayout.NORTH);
        JPanel panelColor = new JPanel();   // 颜色面板（东侧）
        panelColor.setPreferredSize(new Dimension(130, 800));
        panelColor.setBackground(Color.gray);
        frame.add(panelColor, BorderLayout.EAST);
        JLabel colorLabel = new JLabel("选择颜色：       ");
        panelColor.add(colorLabel);
        // 定义Color数组，用来存储按钮上要显示的颜色信息
        Color[] colorArray = { Color.BLUE, Color.GREEN, Color.RED, Color.BLACK,
                Color.lightGray, Color.ORANGE, Color.PINK,
                new Color(200, 100, 200) };
        // 循环遍历colorArray数组，根据数组中的元素来实例化按钮对象
        for (int i = 0; i < colorArray.length; i++) {
            JButton button = new JButton();
            button.setBackground(colorArray[i]);
            button.setPreferredSize(new Dimension(50, 50));
            button.addActionListener(this);
            panelColor.add(button);
        }
        panelColor.add(label);
        panelColor.add(slider);
        panelColor.add(eraserLabel);
        panelColor.add(eraserSlider);
        frame.add(panelDraw, BorderLayout.CENTER);
        frame.setBackground(panelDraw.getBackground());
        frame.setVisible(true);
        g = panelDraw.getGraphics();// 从窗体上获取画笔对象
        panelDraw.addMouseListener(this);
        panelDraw.addMouseMotionListener(this);
    }
    private JMenuBar prepareMenu(){
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu actionMenu = new JMenu("操作");
        JMenu functionMenu = new JMenu("功能");
        JRadioButtonMenuItem textOutput = new JRadioButtonMenuItem("文本输出");
        JMenuItem empty = new JMenuItem("清空");
        JRadioButtonMenuItem freePaint = new JRadioButtonMenuItem("徒手绘画");
        JRadioButtonMenuItem drawRectangle = new JRadioButtonMenuItem("画矩形");
        JRadioButtonMenuItem drawLine = new JRadioButtonMenuItem("画线");
        JRadioButtonMenuItem drawCircle = new JRadioButtonMenuItem("画圆");
        JRadioButtonMenuItem sprayGun = new JRadioButtonMenuItem("喷枪");
        JRadioButtonMenuItem drawEraser = new JRadioButtonMenuItem("橡皮");
        ButtonGroup buttonGroup = new ButtonGroup();

        actionMenu.add(textOutput);
        actionMenu.addSeparator();
        actionMenu.add(empty);

        functionMenu.add(freePaint);
        functionMenu.addSeparator();
        functionMenu.add(drawRectangle);
        functionMenu.addSeparator();
        functionMenu.add(drawLine);
        functionMenu.addSeparator();
        functionMenu.add(drawCircle);
        functionMenu.addSeparator();
        functionMenu.add(sprayGun);
        functionMenu.addSeparator();
        functionMenu.add(drawEraser);
        menuBar.add(actionMenu);
        menuBar.add(functionMenu);

        //菜单内容事件监听
        //清空内容
        empty.addActionListener(e -> frame.repaint());
        //文字输出
        textOutput.addItemListener(e -> {
            if (flag != 1) {
                //输入对话框
                text = JOptionPane.showInputDialog(null,
                        "请输入你想要绘画的文字：","文字输出",JOptionPane.WARNING_MESSAGE);
            }
            flag = 1;
        });
        freePaint.addItemListener(e -> flag = 2);       //自由绘画
        drawRectangle.addItemListener(e -> flag = 3);   //画长方形
        drawLine.addItemListener(e -> flag = 4);        //画线
        drawCircle.addItemListener(e -> flag = 5);      //画圆
        sprayGun.addItemListener(e -> flag = 6);        //喷枪
        drawEraser.addItemListener(e -> flag = 7);      //橡皮
        buttonGroup.add(textOutput);
        buttonGroup.add(freePaint);
        buttonGroup.add(drawRectangle);
        buttonGroup.add(drawLine);
        buttonGroup.add(drawCircle);
        buttonGroup.add(sprayGun);
        buttonGroup.add(drawEraser);
        return menuBar;
    }
    public void actionPerformed(ActionEvent e) {
        JButton tempButton = (JButton) e.getSource();
        String text = tempButton.getText();
        if(text == null ||text.equals("") ){
            //如果点击的按钮为颜色按钮
            color = tempButton.getBackground();
            //用于测试
            System.out.println("选中颜色" + color);
            g.setColor(color);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        width = slider.getValue();
        //文字输出
        if (flag == 1){
            shape = new ShapeString(x1, y1, x2, y2, color, text, new BasicStroke(width));
            shape.draw((Graphics2D) g);
        }
        //绘制矩形
        if (flag == 3){
            shape = new ShapeRectangle(x1, y1, x2, y2, color, new BasicStroke(width));
            shape.draw((Graphics2D) g);
        }
        //绘制直线
        if(flag == 4){
            shape = new ShapeLine(x1, y1, x2, y2, color, new BasicStroke(width));
            shape.draw((Graphics2D) g);
        }
        //绘制圆形
        if(flag == 5){
            shape = new ShapeCircle(x1, y1, x2, y2, color, new BasicStroke(width));
            shape.draw((Graphics2D) g);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        width = slider.getValue();
        eraserWidth = eraserSlider.getValue();
        //徒手绘画
        if(flag == 2){
            shape = new ShapeLine(x1, y1, x2, y2, color, new BasicStroke(width));
            shape.draw((Graphics2D) g);
            x1 = x2;
            y1 = y2;
        }
        //喷枪
        if(flag == 6){
            shape = new ShapeGum(x1, y1, x2, y2, color, new BasicStroke(width));
            shape.draw((Graphics2D) g);
            x1 = x2;
            y1 = y2;
        }
        //橡皮
        if(flag == 7){
            shape = new ShapeEraser(x1, y1, x2, y2, color, new BasicStroke(eraserWidth));
            shape.draw((Graphics2D) g);
            x1 = x2;
            y1 = y2;
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}