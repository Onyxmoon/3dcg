package Shape;

import Shape.Primitive.Circle;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class CircularCone {

    private final PApplet PARENT_APP;

    //Model parameter
    public int segments = 12;
    public float radius = 1;
    public float height = 1;
    public float strokeWeight = 1f;

    //Transformations
    public float rotationX = 0;
    public float rotationY = 0;
    public float rotationZ = 0;
    public float scale = 1;
    public PVector position = new PVector(0,0);


    public CircularCone(PApplet parentApp) {
        this.PARENT_APP = parentApp;
    }

    //Generates vertices for the lateral surface
    private PVector[] makeShape() {
        float stepAngle = PApplet.TWO_PI / this.segments;
        //Segments + 2 for the startpoint end the last point which is the first real surface point
        PVector[] vertices = new PVector[this.segments + 2];

        vertices[0] = new PVector(0,0, this.height);

        for (int i = 0; i <= this.segments; i++) {
            float x = PApplet.cos(i * stepAngle) * this.radius;
            float y = PApplet.sin(i * stepAngle) * this.radius;

            PVector pv = new PVector(x, y, 0);
            vertices[i+1] = pv;
        }

        return vertices;
    }

    private void renderShape() {
        //Render plate
        Circle c = new Circle(PARENT_APP);
        c.radius = this.radius;
        c.segments = this.segments;
        c.strokeWeight = this.strokeWeight;
        c.render();

        //Render lateral surface
        PARENT_APP.beginShape(PConstants.TRIANGLE_FAN);
        for (PVector pv : this.makeShape()) {
            PARENT_APP.vertex(pv.x, pv.y, pv.z);
        }
        PARENT_APP.endShape();
    }

    public void render() {
        PARENT_APP.strokeWeight(this.strokeWeight / this.scale);
        PARENT_APP.pushMatrix();
        PARENT_APP.translate(this.position.x, this.position.y, this.position.z);
        PARENT_APP.scale(scale);
        PARENT_APP.rotateX(this.rotationX);
        PARENT_APP.rotateY(this.rotationY);
        PARENT_APP.rotateZ(this.rotationZ);
        renderShape();
        PARENT_APP.popMatrix();
    }
}
