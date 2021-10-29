public class NrzDiagram extends Diagram {

    public NrzDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {

        int lastY = sequence.split("")[0].equals("0") ? ImageProperties.LEVEL_Y_ZERO : ImageProperties.LEVEL_Y_POS;

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            // Pegel
            int y = s.equals("0") ? ImageProperties.LEVEL_Y_ZERO : ImageProperties.LEVEL_Y_POS;
            g.drawLine(x, lastY, x, y);
            g.drawLine(x, y, x + signalSpacing, y);

            lastY = y;
            x += signalSpacing;
        }
    }
}
