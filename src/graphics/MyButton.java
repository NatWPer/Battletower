package graphics;

public class MyButton {
    String text;
    float xRadius;
    float yRadius;

    float xPos;

    float yPos;

    public MyButton(String text, float xRadius, float yRadius, float xPos, float yPos) {
        this.text = text;
        this.xRadius = xRadius;
        this.yRadius = yRadius;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getxRadius() {
        return xRadius;
    }

    public void setxRadius(float xRadius) {
        this.xRadius = xRadius;
    }

    public float getyRadius() {
        return yRadius;
    }

    public void setyRadius(float yRadius) {
        this.yRadius = yRadius;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }
}
