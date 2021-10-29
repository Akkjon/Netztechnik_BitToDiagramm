public class NrziDiagram extends Diagram {

    public NrziDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {

        int lastY = ImageProperties.LEVEL_Y_ZERO;

        for(String s : sequence.split("")) {
            int y = lastY;
            if(s.equals("1")) {
                y = lastY==ImageProperties.LEVEL_Y_ZERO
                        ? ImageProperties.LEVEL_Y_POS : ImageProperties.LEVEL_Y_ZERO;
            }

            g.drawLine(
                    x, lastY, x, y
            );

            g.drawLine(
                    x, y, x+signalSpacing, y
            );

            x+=signalSpacing;
            lastY = y;
        }
    }
}
