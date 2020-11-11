class Leaf {
  
  public float apertureFactor = 300;
  public float length = 100;
  
  public Leaf() {
  }

  public void render() {
    pushMatrix();
    translate(length - 30, -leafParabel(100));
    beginShape(POLYGON);
    for (float f = -length; f <= length; f += 1) {
      vertex(f, leafParabel(f));
    }
    for (float f = length; f >= -length; f -= 1) {
      vertex(f, leafParabel(f) * -1 + leafParabel(100) * 2);
    }
    endShape();
    popMatrix();
  }

  float leafParabel(float x) {
    return pow(x, 2) / apertureFactor;
  }
}
