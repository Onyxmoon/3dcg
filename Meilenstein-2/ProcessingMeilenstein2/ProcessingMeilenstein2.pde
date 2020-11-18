//General
color textColor;
ColorPalette randomCuratedColorPalette;

//Flower
Flower f;
FlowerMode flowerMode = FlowerMode.STATIC;
int leafs;
int leafApertureFactor = 180;

//Fonts
PFont harabaraMais30;
PFont aileronUL10;
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
  aileronUL10 = createFont("aileron.thin.otf", 10);
  aileronUL12 = createFont("aileron.thin.otf", 12);

  //Initialize envoriment variables
  leafs = int(random(8, 16));

  //Initalize colors
  randomCuratedColorPalette = randomCuratedColorPalette();

  //Initalize main flower
  f = new Flower(this);
}

void draw() {
  //Background: Draw solid color
  background(randomCuratedColorPalette.background);

  //Text: Draw title
  drawTextTitle();

  //Text: Draw mode
  drawTextMode();

  //Flower: Set attributes
  f.setLeafs(leafs);
  f.leafApertureFactor = leafApertureFactor;
  f.fillColor = randomCuratedColorPalette.foreground;
  f.midpointColor = color(255);

  //Flower: Set effects
  f.flowerMode = flowerMode;
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

void keyPressed() {
  if (key == ' ') {
    flowerMode = flowerMode.next();
  } else if (key == '+') {
    leafs++;
  } else if (key == '-') {
    leafs--;
  } else if (key == 'c') {
    ColorPalette newColor = randomCuratedColorPalette();
    while(newColor.equals(randomCuratedColorPalette))
    {
      newColor = randomCuratedColorPalette();
    }
    randomCuratedColorPalette = newColor;
  }
}

void drawTextTitle() {
  pushMatrix();
  scale(min(height, width) / 1000f * 2);
  textFont(harabaraMais30);
  textAlign(LEFT, TOP);
  fill(randomCuratedColorPalette.accent);
  text("Computergrafik.", 35, 35);
  textFont(aileronUL12);
  text("The leaf count is our main effort in life and shoulb be " + leafs, 35, 75);
  popMatrix();
}

void drawTextMode() {
  pushMatrix();
  translate(width - 20, height - 20);
  scale(min(height, width) / 1000f * 2);
  textFont(aileronUL10);
  textAlign(RIGHT, BOTTOM);
  String mode = "[" + flowerMode + "]";
  textSize(10);
  fill(randomCuratedColorPalette.accent);
  text(mode, 0, 0);
  stroke(255);
  strokeWeight(1);
  popMatrix();
}

ColorPalette randomCuratedColorPalette() {
  ColorPalette[] curatedColorPalette = { 
    new ColorPalette(color(130, 101, 255), color(40, 40, 110, 110), color(255)),
    new ColorPalette(color(62, 60, 65), color(240, 88, 103, 110), color(255)),
    new ColorPalette(color(240, 88, 103), color(255, 244, 80, 110), color(255)),
    new ColorPalette(color(255, 50, 47), color(225, 225, 225, 150), color(255)),
    new ColorPalette(color(189, 190, 192), color(60, 58, 59, 110), color(255))
  };
  
  java.util.Random r = new java.util.Random();

  return curatedColorPalette[r.nextInt(curatedColorPalette.length)];
}
