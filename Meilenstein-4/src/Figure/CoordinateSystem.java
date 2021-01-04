package Figure;

import Shape.CircularCone;
import Shape.Cylinder;
import processing.core.PApplet;
import processing.core.PVector;

public class CoordinateSystem {

    private final PApplet PARENT_APP;

    public Dimension dimension = Dimension.D2D;
    public int colorX, colorY, colorZ, strokeColorX, strokeColorY, strokeColorZ;
    public float axisLength = 1;
    public float axisThickness = 1;
    public int resolution = 12;
    public float strokeWeight = 1f;

    //Transformations
    public float rotationX = 0;
    public float rotationY = 0;
    public float rotationZ = 0;
    public float scale = 1;
    public PVector position = new PVector(0,0,0);

    public CoordinateSystem(PApplet parentApp) {
        this.PARENT_APP = parentApp;
        colorX = PARENT_APP.color(255,0,0);
        colorY = PARENT_APP.color(0,255,0);
        colorZ = PARENT_APP.color(0,0,255);
        strokeColorX = PARENT_APP.color(180,25,25);
        strokeColorY = PARENT_APP.color(25,180,25);
        strokeColorZ = PARENT_APP.color(25,25,180);
    }

    /**
     * Renders one axis
     */
    private void renderShape() {
        float deltaTop = axisLength * 0.1f;

        Cylinder pillar = new Cylinder(PARENT_APP);
        pillar.radius = this.axisThickness;
        pillar.height = this.axisLength - deltaTop;
        pillar.baseSegments = this.resolution;
        pillar.sideSegments = this.resolution;
        pillar.strokeWeight = this.strokeWeight / this.scale;

        pillar.render();


        CircularCone top = new CircularCone(PARENT_APP);
        top.position = new PVector(0, 0, this.axisLength - deltaTop);
        top.radius = this.axisThickness + this.axisThickness * 0.5f;
        top.height = deltaTop;
        top.segments = this.resolution;


        top.strokeWeight = this.strokeWeight / this.scale;

        top.render();
    }

    public void render() {
        PARENT_APP.strokeWeight(1f / this.scale);
        PARENT_APP.pushMatrix();
        //Coordinate system transformations
        PARENT_APP.translate(this.position.x, this.position.y, this.position.z);
        PARENT_APP.scale(this.scale);
        PARENT_APP.rotateX(this.rotationX);
        PARENT_APP.rotateY(this.rotationY);
        PARENT_APP.rotateZ(this.rotationZ);
        //Origin box
        PARENT_APP.fill(255);
        PARENT_APP.stroke(200f);
        PARENT_APP.box(this.axisLength * 0.1f);
        //X-axis
        PARENT_APP.pushMatrix();
        PARENT_APP.fill(this.colorX);
        PARENT_APP.stroke(this.strokeColorX);
        PARENT_APP.rotateY(PApplet.radians(90));
        renderShape();
        PARENT_APP.popMatrix();
        //Y-axis
        PARENT_APP.pushMatrix();
        PARENT_APP.fill(this.colorY);
        PARENT_APP.stroke(this.strokeColorY);
        PARENT_APP.rotateX(PApplet.radians(270));
        renderShape();
        PARENT_APP.popMatrix();
        //Optional z-axis
        if (dimension == Dimension.D3D) {
            PARENT_APP.pushMatrix();
            PARENT_APP.fill(this.colorZ);
            PARENT_APP.stroke(this.strokeColorZ);
            renderShape();
            PARENT_APP.popMatrix();
        }
        PARENT_APP.popMatrix();
    }

    public enum Dimension {
        D2D, D3D
    }
}
