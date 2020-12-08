import Shape.Star;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

public class Meilenstein3 extends PApplet {

    private Star star = new Star(this);
    private int stars = 32;
    private int starSize = 50;
    private int starRadius = 250;
    private float starRotationDelta = 0;

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
    }

    @Override
    public void draw() {
        background(255);
        float starStepAngle = TWO_PI / stars;
        star.size = starSize;
        pushMatrix();
        translate(width / 2f, height / 2f);
        for (int i = 0; i < stars; i++){
            pushMatrix();
            star.position = new PVector(
                    cos(starStepAngle * i) * starRadius,
                    sin(starStepAngle * i) * starRadius
            );
            star.orientation = (starStepAngle * i) + starRotationDelta;
            star.render();
            popMatrix();
        }
        popMatrix();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);

        switch (event.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_UP: starRadius += 1; break;
            case java.awt.event.KeyEvent.VK_DOWN: starRadius = starRadius == 0 ? 0 : starRadius - 1; break;
            case java.awt.event.KeyEvent.VK_LEFT: starRotationDelta += radians(1); break;
            case java.awt.event.KeyEvent.VK_RIGHT: starRotationDelta -= radians(1); break;
            case java.awt.event.KeyEvent.VK_PAGE_UP: stars += 1; break;
            case java.awt.event.KeyEvent.VK_PAGE_DOWN: stars = stars == 0 ? 0 : stars - 1; break;
            case java.awt.event.KeyEvent.VK_PLUS: starSize += 1; break;
            case java.awt.event.KeyEvent.VK_MINUS: starSize = starSize == 0 ? 0 : starSize - 1; break;
        }
    }
}
