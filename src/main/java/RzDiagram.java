public class RzDiagram extends Diagram{

    public RzDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            // Pegel
            int y = s.equals("0") ? ImageProperties.bottomLinePoint : ImageProperties.topLinePoint;
            g.drawLine(x, ImageProperties.bottomLinePoint, x, y);

            g.drawLine(
                    x + (signalSpacing / 2),
                    y,
                    x + (signalSpacing / 2),
                    ImageProperties.bottomLinePoint);

            g.drawLine(x, y, x + (signalSpacing / 2), y);

            g.drawLine(
                    x + (signalSpacing / 2),
                    ImageProperties.bottomLinePoint,
                    x + signalSpacing,
                    ImageProperties.bottomLinePoint);

            //lastY = y;
            x += signalSpacing;
        }
        //g.drawLine(x, lastY, x, ImageProperties.bottomLinePoint);
    }
}
