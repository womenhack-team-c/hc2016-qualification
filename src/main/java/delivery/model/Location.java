package delivery.model;

import java.util.Objects;

public class Location {
    public final int r;
    public final int c;

    public Location(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return r == location.r &&
                c == location.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }

    @Override
    public String toString() {
        return "Location{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }
}
