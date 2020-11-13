int leafs = 8;
int leafApertureFactor = 180;

void setup() {
  size(1000, 1440);
  surface.setResizable(true);
  surface.setLocation(100, 100);
}

void draw() {
  //Draw Background
  background(130, 101, 255);

  //Draw text
  pushMatrix();
  scale(min(height, width) / 1000f * 2);
  textFont(createFont("HarabaraMaisDemo.otf", 30));
  textSize(30);
  textAlign(LEFT, TOP);
  text("Computergrafik.", 35, 35);
  textFont(createFont("aileron.ultralight.otf", 12));
  textSize(12);
  text("The leaf count is our main effort in life and shoulb be " + leafs + ". We bend us to " + leafApertureFactor + ".", 35, 75);
  popMatrix();

  //Draw Flower
  pushMatrix();
  noStroke();
  fill(40, 40, 110, 110);
  translate(width / 2, height / 2);
  scale(min(height, width) / 1000f * 2f);
  Flower f = new Flower();
  f.setLeafs(leafs);
  f.leafApertureFactor = leafApertureFactor;
  f.render();
  popMatrix();

  //Mouse press
  if (mousePressed == true) {
    if (mouseButton == LEFT) {
      leafApertureFactor = leafApertureFactor + 1;
    } else {
      leafApertureFactor = leafApertureFactor - 1 <= 40 ? 40 : leafApertureFactor - 1;
    }
  }
}

void mouseWheel(MouseEvent event) {
  if (event.getCount() < 0) {
    leafs++;
  } else {
    leafs = leafs - 1 < 0 ? 0 : leafs - 1;
  }
}
