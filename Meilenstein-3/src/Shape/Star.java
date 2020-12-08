package Shape;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Star {

    private final PApplet parentApp;
    public float orientation = 0;
    public float size = 1;
    public PVector position = new PVector(0,0);



    public Star(PApplet parentApp) {
        this.parentApp = parentApp;
    }

    private PVector[] makeShape() {
        ArrayList<PVector> pVectors = new ArrayList<>();
        PVector[] circleOrigins = {
                new PVector(-1, -1),
                new PVector(+1, -1),
                new PVector(+1, +1),
                new PVector(-1, +1)
        };

        float stepAngle = parentApp.TWO_PI / circleOrigins.length;

        for (int i = 0; i < circleOrigins.length; i++) {
            //Circle is build in reverse direction
            for (float f = stepAngle * (i + 1); f > stepAngle * (i); f = f - PApplet.radians(1)) {
                float x = PApplet.cos(f);
                float y = PApplet.sin(f);
                PVector tempVector = new PVector(x, y);
                //Add translation
                tempVector.add(circleOrigins[i]);
                pVectors.add(tempVector);
            }
        }

        return pVectors.toArray(new PVector[0]);
    }

    private void renderShape() {
        parentApp.beginShape(PApplet.POLYGON);
        for (PVector pVector : makeShape()) {
            parentApp.vertex(pVector.x, pVector.y);
        }
        parentApp.endShape();

    }

    public void render() {
        parentApp.strokeWeight(1f / size);
        parentApp.noFill();
        parentApp.pushMatrix();
        parentApp.translate(position.x, position.y);
        parentApp.scale(size);
        parentApp.rotate(orientation);
        renderShape();
        parentApp.popMatrix();
    }
}
