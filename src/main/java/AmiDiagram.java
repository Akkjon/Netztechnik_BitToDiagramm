public class AmiDiagram extends Diagram {
    public AmiDiagram(String sequence) {
        super(sequence);
    }

    private boolean toggleOne = false;
    private int getNextOne() {
        int i = toggleOne ? ImageProperties.LEVEL_Y_NEG : ImageProperties.LEVEL_Y_POS;
        toggleOne = !toggleOne;
        return i;
    }

    @Override
    protected void drawSignal() {
        int lastY = sequence.split("")[0].equals("0") ? ImageProperties.LEVEL_Y_ZERO : ImageProperties.LEVEL_Y_POS;

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            // Pegel
            int y = s.equals("0") ? ImageProperties.LEVEL_Y_ZERO : getNextOne();
            g.drawLine(x, lastY, x, y);
            g.drawLine(x, y, x + signalSpacing, y);

            lastY = y;
            x += signalSpacing;
        }
    }
}
