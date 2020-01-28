package hhCore.config;

public class Gains {
  public double p, i, d, f;

  public Gains(double p, double i, double d, double f) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.f = f;
  }

  @Override // Auto-generated
  public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof Gains)) return false;
    Gains otherGains = (Gains) other;
    return Double.compare(otherGains.p, p) == 0
        && Double.compare(otherGains.i, i) == 0
        && Double.compare(otherGains.d, d) == 0
        && Double.compare(otherGains.f, f) == 0;
  }

  @Override
  public String toString() {
    return String.format("Gains{p=%f, i=%f, d=%f, f=%f}", p, i, d, f);
  }
}
