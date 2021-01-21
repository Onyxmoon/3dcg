package Shape.Primitive;

import processing.core.PApplet;
import processing.core.PVector;

public class Circle {

    private final PApplet PARENT_APP;

    //Model parameter
    public float radius = 1;
    public int segments = 12;
    public int bodyColor;
    public float strokeWeight = 1f;
    public int strokeColor;

    //Transformations
    public float rotationX = 0;
    public float rotationY = 0;
    public float rotationZ = 0;
    public float scale = 1f;
    public PVector position = new PVector(0,0, 0);


    public Circle(PApplet parentApp) {
        this.PARENT_APP = parentApp;
        bodyColor = PARENT_APP.color(255);
        strokeColor = PARENT_APP.color(0);
    }


    private PVector[] makeShape() {
        float stepAngle = PApplet.TWO_PI / this.segments;

        //Segments + 2 for the startpoint end the last point which is the first circle point
        PVector[] vertices = new PVector[this.segments + 2];

        //Startpoint is the middle of my model coordinate system
        vertices[0] = new PVector( 0, 0);

        //Generating vertices
        for (int i = 0; i <= this.segments; i++) {
            float x = PApplet.cos(i * stepAngle) * this.radius;
            float y = PApplet.sin(i * stepAngle) * this.radius;
            vertices[i+1] = new PVector(x, y);
        }

        return vertices;
    }

    private void renderShape() {
        PARENT_APP.beginShape(PApplet.TRIANGLE_FAN);
        for (PVector pv : this.makeShape()) {
            PARENT_APP.vertex(pv.x, pv.y);
        }
        PARENT_APP.endShape();
    }

    public void render() {
        PARENT_APP.strokeWeight(strokeWeight / scale);
        PARENT_APP.stroke(this.strokeColor);
        PARENT_APP.fill(this.bodyColor);
        PARENT_APP.pushMatrix();
        PARENT_APP.translate(position.x, position.y, position.z);
        PARENT_APP.scale(scale);
        PARENT_APP.rotateX(this.rotationX);
        PARENT_APP.rotateY(this.rotationY);
        PARENT_APP.rotateZ(this.rotationZ);
        renderShape();
        PARENT_APP.popMatrix();
    }
}
