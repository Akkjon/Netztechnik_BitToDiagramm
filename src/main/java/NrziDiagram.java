public class NrziDiagram extends Diagram {

    public NrziDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {

        int lastY = ImageProperties.bottomLinePoint;

        for(String s : sequence.split("")) {
            int y = lastY;
            if(s.equals("1")) {
                y = lastY==ImageProperties.bottomLinePoint
                        ? ImageProperties.topLinePoint : ImageProperties.bottomLinePoint;
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
