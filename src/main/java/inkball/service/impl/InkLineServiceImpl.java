package inkball.service.impl;

import inkball.model.InkLine;
import inkball.service.IInkLineService;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SanseYooyea
 */
public class InkLineServiceImpl implements IInkLineService {
    private final List<InkLine> lines;
    private final PApplet sketch;
    private boolean drawing = false;
    private InkLine newLine = null;

    public InkLineServiceImpl(PApplet sketch) {
        lines = new ArrayList<>();
        this.sketch = sketch;
    }

    @Override
    public void draw(int mouseX, int mouseY, int pmouseX, int pmouseY) {
        if (!drawing) {
            return;
        }

        if (mouseX < 0 || mouseY < 0) {
            return;
        }

        if (!(mouseX == pmouseX && mouseY == pmouseY)) {
            newLine.append(mouseX, mouseY, pmouseX, pmouseY);
        }

        newLine.update();
    }

    @Override
    public void startDraw(int mouseX, int mouseY) {
        drawing = true;
        newLine = new InkLine(sketch);
        lines.add(newLine);

        if (mouseX >= 0 && mouseY >= 0) {
            newLine.append(mouseX, mouseY, mouseX, mouseY);
        }
    }

    @Override
    public void stopDraw() {
        drawing = false;
        newLine = null;
    }

    @Override
    public void stopDraw(boolean ifHit) {
        if (!ifHit) {
            return;
        }

        drawing = false;
        newLine = null;
    }

    @Override
    public void stopDraw(InkLine hit) {
        if (newLine == hit) {
            for (InkLine i : lines) {
                if (i == hit) {
                    lines.remove(hit);
                    break;
                }
            }
            stopDraw();
        } else {
            for (InkLine i : lines) {
                if (i == hit) {
                    lines.remove(hit);
                    return;
                }
            }
        }
    }

    @Override
    public void update() {
        lines.forEach(InkLine::update);
    }

    @Override
    public void clear() {
        this.lines.clear();
    }

    @Override
    public List<InkLine> getLines() {
        return lines;
    }
}
