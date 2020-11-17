class ColorPalette {
  color background;
  color foreground;
  color accent;

  public ColorPalette(color background, color foreground, color accent) {
    this.background = background;
    this.foreground = foreground;
    this.accent = accent;
  }

  @Override public boolean equals(Object obj) { 
    if (obj == this) { 
      return true;
    } 
    if (obj == null || obj.getClass() != this.getClass()) { 
      return false;
    } 
    ColorPalette cp = (ColorPalette) obj; 
    return cp.background == this.background 
      && cp.foreground == this.foreground
      && cp.accent == this.accent;
  }

  @Override public int hashCode() { 
    int prime = 62;
    return this.background * prime + this.foreground * prime + this.accent * prime;
  }
}
