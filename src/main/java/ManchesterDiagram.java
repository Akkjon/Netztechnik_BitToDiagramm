public class ManchesterDiagram extends Diagram {

    public ManchesterDiagram(String sequence) {
        super(sequence);
    }

    @Override
    protected void drawSignal() {
        String last = sequence.split("")[0].equals("0") ? "1" : "0";

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            // Pegel
            //TODO ich weiß, das sieht grässlich aus.. ich hatte keine Lust mehr...
            if(s.equals("0")) {
                g.drawLine(
                        x - (signalSpacing / 2),
                        ImageProperties.bottomLinePoint,
                        x,
                        ImageProperties.bottomLinePoint
                );

                g.drawLine(
                        x + (signalSpacing / 2),
                        ImageProperties.topLinePoint,
                        x,
                        ImageProperties.topLinePoint
                );
            } else {
                g.drawLine(
                        x - (signalSpacing / 2),
                        ImageProperties.topLinePoint,
                        x,
                        ImageProperties.topLinePoint
                );

                g.drawLine(
                        x + (signalSpacing / 2),
                        ImageProperties.bottomLinePoint,
                        x,
                        ImageProperties.bottomLinePoint
                );
            }
            if(last.equals(s)) {
                g.drawLine(
                        x - (signalSpacing / 2),
                        ImageProperties.bottomLinePoint,
                        x - (signalSpacing / 2),
                        ImageProperties.topLinePoint
                );
            }

            g.drawLine(x, ImageProperties.bottomLinePoint, x, ImageProperties.topLinePoint);

            x+=signalSpacing;
            last = s;
        }
    }
}
