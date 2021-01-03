import Figure.CoordinateSystem;
import Shape.CircularCone;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.MouseEvent;

public class Meilenstein4 extends PApplet {


    public static void main(String[] args) {
        PApplet.main(Meilenstein4.class);
    }

    CoordinateSystem c = new CoordinateSystem(this);
    PVector originVertex;
    float xOriginRotation = -20;
    float yOriginRotation = 0;
    float zoom = 0;

    //Mouse data
    float xOffset = 0.0f;
    float yOffset = 0.0f;

    @Override
    public void settings() {
        super.settings();
        size(1000, 1000, PConstants.P3D);
    }

    @Override
    public void setup() {
        super.setup();
        surface.setResizable(true);

        //Setup origin
        originVertex = new PVector(width / 2f,height / 2f);

        //Setup coordinate system
        c.resolution = 24;
        c.axisThickness = 0.3f;
        c.axisLength = 10f;
        c.dimension = CoordinateSystem.Dimension.D3D;
    }

    @Override
    public void draw() {
        //Reduce clipping of near plane to minimum
        perspective(PI/3.0f,(float)width/height,1,100000);

        background(255);

        translate(originVertex.x, originVertex.y, zoom);
        rotateX(radians(xOriginRotation));
        rotateY(radians(yOriginRotation));
        rotateZ(radians(180));

        c.scale = min(width, height) / 25f;
        c.render();

        pushMatrix();
        CircularCone cone = new CircularCone(this);
        cone.position = new PVector(150,150,150);
        cone.scale = min(width, height) / 25f;

        fill(255);
        stroke(0);
        cone.render();
        popMatrix();

        System.out.println(frameRate);
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        super.mouseWheel(event);
        if (event.getCount() < 0) {
            this.zoom += 20;
        } else {
            this.zoom = this.zoom - 20 < 0 ? 0 : this.zoom - 20;
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);
        switch (event.getButton()) {
            case PConstants.LEFT: xOffset = mouseX- originVertex.x; yOffset = mouseY- originVertex.y; break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        super.mouseDragged(event);
        switch (event.getButton()) {
            case PConstants.LEFT: originVertex.x = mouseX-xOffset; originVertex.y = mouseY-yOffset; break;
            case PConstants.RIGHT: xOriginRotation += -(mouseY-pmouseY) * 0.1f; yOriginRotation += (mouseX-pmouseX) * 0.1f; break;
        }


    }
}
