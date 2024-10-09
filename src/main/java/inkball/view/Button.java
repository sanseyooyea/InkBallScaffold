package inkball.view;


import processing.core.PApplet;

/**
 * @author SanseYooyea
 */
public class Button {
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final String label;
    private final int textSize; // 文本大小
    private final PApplet p;
    private boolean isHovered;
    private Runnable action; // 点击按钮时执行的动作
    private boolean pressed;

    public Button(PApplet sketch, String label, int textSize, float x, float y, float width, float height) {
        this.p = sketch;
        this.label = label;
        this.textSize = textSize;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void draw() {
        // 检测是否鼠标悬停在按钮上
        isHovered = isMouseOver();

        // 绘制按钮
        p.rect(x, y, width, height);

        // 绘制按钮上的文本
        p.textAlign(p.CENTER, p.CENTER);
        p.text(label, x + width / 2, y + height / 2);
    }

    public boolean isMouseOver() {
        return p.mouseX > x && p.mouseX < x + width && p.mouseY > y && p.mouseY < y + height;
    }

    public void isPressed() {
        if (isHovered && p.mousePressed) {
            pressed = true;
        }

    }

    public void released() {
        if (isHovered && pressed && !p.mousePressed) {
            pressed = false;
            if (isHovered && action != null) {
                action.run();
            }
        }
    }

    public void setAction(Runnable action) {
        this.action = action;
    }


    // Getters and Setters 可以根据需要添加
}

