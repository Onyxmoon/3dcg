class Leaf {
  
  public float apertureFactor = 300;
  public float leafLength = 100;
  
  public Leaf() {
  }

  public void render() {
    pushMatrix();
    translate(leafLength - 30, -leafParabel(leafLength));
    beginShape(POLYGON);
    for (float f = -leafLength; f <= leafLength; f += 1) {
      vertex(f, leafParabel(f));
    }
    for (float f = leafLength; f >= -leafLength; f -= 1) {
      vertex(f, leafParabel(f) * -1 + leafParabel(leafLength) * 2);
    }
    endShape();
    popMatrix();
  }

  float leafParabel(float x) {
    return pow(x, 2) / apertureFactor;
  }
}
