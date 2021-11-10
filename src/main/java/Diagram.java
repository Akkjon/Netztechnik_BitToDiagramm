import java.awt.*;
import java.awt.image.BufferedImage;

public class Diagram {

    private boolean isDrawn = false;
    private final BufferedImage bufferedImage;

    protected final Graphics2D g;
    protected final String sequence;
    protected final int signalSpacing;
    protected int x = ImageProperties.PADDING;


    public Diagram(String sequence) {
        this.sequence = sequence;
        this.bufferedImage = new BufferedImage(
                ImageProperties.WIDTH,
                ImageProperties.HEIGHT,
                BufferedImage.TYPE_4BYTE_ABGR
        );

        this.g = bufferedImage.createGraphics();
        this.signalSpacing = (ImageProperties.WIDTH - 2 * ImageProperties.PADDING) / sequence.length();
    }

    private void draw() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ImageProperties.WIDTH, ImageProperties.HEIGHT);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, ImageProperties.FONT_SIZE));

        // Titel des Diagramms
        drawTitle();

        // Koordinatensystem zeichnen
        drawCoordinateSystem();

        // Signal zeichnen
        drawSignal();

        isDrawn = true;
    }

    private void drawTitle() {
        String header = "Input: " + sequence;
        FontMetrics fm = g.getFontMetrics();
        g.drawString(header, ImageProperties.WIDTH / 2 - (fm.stringWidth(header) / 2), fm.getHeight());
    }

    private void drawCoordinateSystem() {
        //koordinatenachse
        g.setStroke(new BasicStroke(5));
        g.drawLine(
                ImageProperties.PADDING,
                ImageProperties.HEIGHT - ImageProperties.PADDING,
                ImageProperties.WIDTH - ImageProperties.PADDING,
                ImageProperties.HEIGHT - ImageProperties.PADDING);
        g.drawLine(
                ImageProperties.PADDING,
                ImageProperties.HEIGHT - ImageProperties.PADDING,
                ImageProperties.PADDING,
                ImageProperties.PADDING);

        //pegelmarkierung y-achse
        g.drawString(
                "0",
                ImageProperties.PADDING - 23,
                ImageProperties.LEVEL_Y_ZERO + (ImageProperties.FONT_SIZE / 2) - 2);
        g.drawLine(
                ImageProperties.PADDING,
                ImageProperties.LEVEL_Y_ZERO,
                ImageProperties.PADDING - 10,
                ImageProperties.LEVEL_Y_ZERO);

        g.drawString(
                "1",
                ImageProperties.PADDING - 20,
                ImageProperties.LEVEL_Y_POS + (ImageProperties.FONT_SIZE / 2) - 2);
        g.drawLine(
                ImageProperties.PADDING,
                ImageProperties.LEVEL_Y_POS,
                ImageProperties.PADDING - 10,
                ImageProperties.LEVEL_Y_POS);

        g.drawString(
                "-1",
                ImageProperties.PADDING - 27,
                ImageProperties.LEVEL_Y_NEG + (ImageProperties.FONT_SIZE / 2) - 2);
        g.drawLine(
                ImageProperties.PADDING,
                ImageProperties.LEVEL_Y_NEG,
                ImageProperties.PADDING - 10,
                ImageProperties.LEVEL_Y_NEG);

        g.setStroke(new BasicStroke(2));

        for(int i = 0; i<sequence.length(); i++) {
            // Taktzeitmarkierung auf x-Achse
            g.drawLine(
                    (i * signalSpacing) + ImageProperties.PADDING,
                    ImageProperties.HEIGHT - ImageProperties.PADDING + (ImageProperties.TOP_PADDING / 2),
                    (i * signalSpacing) + ImageProperties.PADDING,
                    ImageProperties.HEIGHT - ImageProperties.PADDING - (ImageProperties.TOP_PADDING / 2));
        }
    }

    protected void drawSignal() {
        //overwrite this method
    }

    public BufferedImage get() {
        if(!isDrawn) draw();
        return bufferedImage;
    }

}
