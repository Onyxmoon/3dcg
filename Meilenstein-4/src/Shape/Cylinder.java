package Shape;

import Shape.Primitive.Circle;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Cylinder {

    private final PApplet PARENT_APP;

    //Model parameter
    public int baseSegments = 12;
    public int sideSegments = 12;
    public float radius = 1;
    public float height = 1;
    public float strokeWeight = 1f;

    //Transformations
    public float rotationX = 0;
    public float rotationY = 0;
    public float rotationZ = 0;
    public float scale = 1;
    public PVector position = new PVector(0,0,0);


    public Cylinder(PApplet parentApp) {
        this.PARENT_APP = parentApp;
    }

    //Generates vertices for one ring of the lateral surface
    private PVector[] makeLateralRingShape(float startHeight) {
        float stepAngle = PApplet.TWO_PI / this.baseSegments;
        float ringHeight = PApplet.abs(height) / sideSegments;

        //Segments * 2 for the vertices and plus 2 to close the shape
        PVector[] vertices = new PVector[(baseSegments * 2) + 2];


        for (int i = 0; i <= this.baseSegments; i++) {
            float x = PApplet.cos(i * stepAngle) * this.radius;
            float y = PApplet.sin(i * stepAngle) * this.radius;

            PVector pv = new PVector(x, y, startHeight);
            vertices[i * 2] = pv;

            x = PApplet.cos(i * stepAngle) * this.radius;
            y = PApplet.sin(i * stepAngle) * this.radius;

            pv = new PVector(x, y, startHeight + ringHeight);
            vertices[i * 2 + 1] = pv;
        }

        return vertices;
    }

    //Generates vertices for the lateral surface
    private void renderShape() {
        //Render plate
        Circle c = new Circle(PARENT_APP);
        c.radius = this.radius;
        c.strokeWeight = this.strokeWeight;
        c.segments = this.baseSegments;
        c.render();

        float stepHeight = height / sideSegments;
        //Render lateral body
        for (int i = 0; i < sideSegments; i++) {

            PARENT_APP.beginShape(PConstants.TRIANGLE_STRIP);
            for (PVector pv : makeLateralRingShape(stepHeight * i)) {
                PARENT_APP.vertex(pv.x, pv.y, pv.z);
            }
            PARENT_APP.endShape();
        }

        c.position = new PVector(0, 0, height);
        c.render();
    }

    public void render() {
        PARENT_APP.strokeWeight(strokeWeight / scale);
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
