public class NrzDiagram extends Diagram {

    public NrzDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {

        int lastY = sequence.split("")[0].equals("0") ? ImageProperties.bottomLinePoint : ImageProperties.topLinePoint;

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            // Pegel
            int y = s.equals("0") ? ImageProperties.bottomLinePoint : ImageProperties.topLinePoint;
            g.drawLine(x, lastY, x, y);
            g.drawLine(x, y, x + signalSpacing, y);

            lastY = y;
            x += signalSpacing;
        }
    }
}
