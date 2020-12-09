import Shape.Star;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Meilenstein3 extends PApplet {

    private final Star star = new Star(this);
    private int stars = 32;
    private int starSize = 50;
    private int circleRadius = 250;
    private float starRotationDelta = radians(80);
    private float mouseRotation = 0;

    public static void main(String[] args) {
        PApplet.main(Meilenstein3.class);
    }

    @Override
    public void settings() {
        super.settings();
        size(1000, 1000);
    }

    @Override
    public void setup() {
        super.setup();
        surface.setResizable(true);
    }

    @Override
    public void draw() {
        background(255);
        float starStepAngle = TWO_PI / stars;
        star.size = starSize;
        pushMatrix();
        translate(width / 2f, height / 2f);
        scale(min(width, height) / 1000f);
        rotate(mouseRotation);
        for (int i = 0; i < stars; i++){
            pushMatrix();
            star.position = new PVector(
                    cos(starStepAngle * i) * circleRadius,
                    sin(starStepAngle * i) * circleRadius
            );
            star.orientation = (starStepAngle * i) + starRotationDelta;
            star.render();
            popMatrix();
        }
        popMatrix();
        //System.out.println(frameRate);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);

        switch (event.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_UP: circleRadius += 1; break;
            case java.awt.event.KeyEvent.VK_DOWN: circleRadius = circleRadius == 0 ? 0 : circleRadius - 1; break;
            case java.awt.event.KeyEvent.VK_LEFT: starRotationDelta -= radians(1); break;
            case java.awt.event.KeyEvent.VK_RIGHT: starRotationDelta += radians(1); break;
            case java.awt.event.KeyEvent.VK_PAGE_UP: stars += 1; break;
            case java.awt.event.KeyEvent.VK_PAGE_DOWN: stars = stars == 0 ? 0 : stars - 1; break;
            case java.awt.event.KeyEvent.VK_PLUS: starSize += 1; break;
            case java.awt.event.KeyEvent.VK_MINUS: starSize = starSize == 1 ? 1: starSize - 1; break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        super.mouseDragged(event);
        System.out.println(event.getButton());
        switch (event.getButton()) {
            case 37: mouseRotation = atan2(event.getY()-height/2f, event.getX()-width/2f); break;
            case 39: starRotationDelta = atan2(event.getY()-height/2f, event.getX()-width/2f); break;
        }

    }

    @Override
    public void mouseWheel(MouseEvent event) {
        super.mouseWheel(event);
        if (event.getCount() == 1) {
            starSize = starSize == 1 ? 1: starSize - 1;
        } else {
            starSize += 1;
        }
    }
}
