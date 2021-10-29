public class RzDiagram extends Diagram{

    public RzDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            // Pegel
            int y = s.equals("0") ? ImageProperties.LEVEL_Y_ZERO : ImageProperties.LEVEL_Y_POS;
            g.drawLine(x, ImageProperties.LEVEL_Y_ZERO, x, y);

            g.drawLine(
                    x + (signalSpacing / 2),
                    y,
                    x + (signalSpacing / 2),
                    ImageProperties.LEVEL_Y_ZERO);

            g.drawLine(x, y, x + (signalSpacing / 2), y);

            g.drawLine(
                    x + (signalSpacing / 2),
                    ImageProperties.LEVEL_Y_ZERO,
                    x + signalSpacing,
                    ImageProperties.LEVEL_Y_ZERO);

            //lastY = y;
            x += signalSpacing;
        }
        //g.drawLine(x, lastY, x, ImageProperties.bottomLinePoint);
    }
}
