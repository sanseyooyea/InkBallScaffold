package inkball.service;

import inkball.model.InkLine;

import java.util.List;

/**
 * @author SanseYooyea
 */
public interface IInkLineService {
    void draw(int mouseX, int mouseY, int pmouseX, int pmouseY);

    void startDraw(int mouseX, int mouseY);

    void stopDraw();

    void stopDraw(boolean ifHit);

    void stopDraw(InkLine hit);

    void update();

    void clear();

    List<InkLine> getLines();
}
