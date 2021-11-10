public class ManchesterDiagram extends Diagram {

    public ManchesterDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {
        String last = sequence.split("")[0].equals("0") ? "1" : "0";

        int[] positions = {ImageProperties.LEVEL_Y_POS, ImageProperties.LEVEL_Y_NEG};

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            int pos0 = s.equals("0") ? 1 : 0;

            // Pegel
            g.drawLine(
                    x - (signalSpacing / 2),
                    positions[pos0],
                    x,
                    positions[pos0]
            );

            g.drawLine(
                    x,
                    positions[Math.abs(pos0 - 1)],
                    x + (signalSpacing / 2),
                    positions[Math.abs(pos0 - 1)]
            );

            if(last.equals(s)) {
                g.drawLine(
                        x - (signalSpacing / 2),
                        ImageProperties.LEVEL_Y_NEG,
                        x - (signalSpacing / 2),
                        ImageProperties.LEVEL_Y_POS
                );
            }

            g.drawLine(x, ImageProperties.LEVEL_Y_NEG, x, ImageProperties.LEVEL_Y_POS);

            x+=signalSpacing;
            last = s;
        }
    }
}
