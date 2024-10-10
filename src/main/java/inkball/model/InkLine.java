package inkball.model;

import processing.core.PApplet;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SanseYooyea
 */
public class InkLine implements Updatable {
    public final float lineThickness = 5;
    private final PApplet sketch;
    public List<Line> line;

    public InkLine(PApplet sketch) {
        this.sketch = sketch;
        line = new ArrayList<>();
    }

    public void append(int mouseX, int mouseY, int pmouseX, int pmouseY) {
        line.add(new Line(new Point2D.Float(mouseX, mouseY), new Point2D.Float(pmouseX, pmouseY), lineThickness));
    }

    @Override
    public void update() {
        sketch.stroke(0);
        sketch.strokeWeight(lineThickness);
        sketch.fill(0);
        for (Line lineSegment : line) {
            sketch.line((float) lineSegment.start.getX(), (float) lineSegment.start.getY(), (float) lineSegment.end.getX(), (float) lineSegment.end.getY());
        }
    }

    static class Line {
        public final float lineThickness;
        public Point2D start, end;
        public float angle;
        public double len;

        public Line(Point2D start, Point2D end, float lineThickness) {
            this.start = start;
            this.end = end;
            this.angle = PApplet.atan2((float) (start.getY() - end.getY()), (float) (start.getX() - end.getX()));
            this.len = start.distance(end);
            this.lineThickness = lineThickness;
        }
    }
}
