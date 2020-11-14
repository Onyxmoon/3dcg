//Flower
Flower f;
int leafs = 8;
int leafApertureFactor = 180;

//Fonts
PFont harabaraMais30;
PFont aileronUL12;

//Mouse drag
float mouseRotationAngle = 0;

void setup() {
  //Set renderer options
  frameRate(60);

  //Set window options
  size(1000, 1440);
  surface.setResizable(true);
  surface.setLocation(100, 100);

  //Initalize fonts
  harabaraMais30 = createFont("HarabaraMaisDemo.otf", 30);
  aileronUL12 = createFont("aileron.ultralight.otf", 12);

  //Initalize main flower
  f = new Flower();
}

void draw() {
  //Background: Draw solid color
  background(130, 101, 255);

  //Text: Draw title
  drawTextTitle();

  //Flower: Set attributes
  f.setLeafs(leafs);
  f.leafApertureFactor = leafApertureFactor;
  f.fillColor = color(40, 40, 110, 110);

  //Flower: Set effects
  f.breath = true;
  f.breathDuration = 400;
  f.breathWeaknessFactor = 1200;

  //Flower: Set transforms
  f.positionX = width / 2;
  f.positionY = height / 2;
  f.scaleFactor = min(height, width) / 1000f * 2f;
  f.rotation = mouseRotationAngle;

  //Flower: Draw Flower
  f.render();

  System.out.println(frameRate);
}

void mouseDragged() {
  mouseRotationAngle = atan2(mouseY-height/2, mouseX-width/2);
}

void mouseWheel(MouseEvent event) {
  if (event.getCount() < 0) {
    leafs++;
  } else {
    leafs = leafs - 1 < 0 ? 0 : leafs - 1;
  }
}

void drawTextTitle() {
  pushMatrix();
  scale(min(height, width) / 1000f * 2);
  textFont(harabaraMais30);
  textAlign(LEFT, TOP);
  text("Computergrafik.", 35, 35);
  textFont(aileronUL12);
  text("The leaf count is our main effort in life and shoulb be " + leafs, 35, 75);
  popMatrix();
}
