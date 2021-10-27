import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 1000;
    private static final int PADDING = 50;
    private static final int TOP_PADDING = 20;
    private static final int FONT_SIZE = 20;

    private static final int LEVEL_Y_POS = PADDING + TOP_PADDING;
    private static final int LEVEL_Y_NEG = HEIGHT - PADDING - TOP_PADDING;
    private static final int LEVEL_Y_ZERO = LEVEL_Y_POS + (LEVEL_Y_NEG - LEVEL_Y_POS) / 2;

    public static void main(String[] args) {
        // Eingabe lesen
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bitfolge eingeben: ");
        String sequence = scanner.nextLine();

        System.out.println("Kodierungsverfahren eingeben (NRZ, RZ, AMI, Manchester):");
        String method = scanner.nextLine();

        scanner.close();

        // Eingabe validieren
        if (sequence.replace("0", "").replace("1", "").trim().length() > 0) {
            throw new IllegalArgumentException(String.format("input  '%s' does not only contain 0 or 1", sequence));
        }

        // Diagramm erzeugen
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        drawSequence(sequence, img.createGraphics());

        // Diagramm als Datei speichern
        File out = new File(System.currentTimeMillis() + ".png");
        try {
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static final int topPoint = PADDING;
    private static final int topLinePoint = topPoint + TOP_PADDING;

    private static final int bottomPoint = HEIGHT - PADDING;
    private static final int bottomLinePoint = bottomPoint - TOP_PADDING;

    private static void drawSequence(String sequence, Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));

        // Titel des Diagramms
        drawTitle(g, sequence);

        // Koordinatensystem zeichnen
        drawCoordinateSystem(g);

        // Signal zeichnen
        drawSignal(g, sequence);
    }

    private static void drawTitle(Graphics2D g, String sequence) {
        String header = "Input: " + sequence;
        FontMetrics fm = g.getFontMetrics();
        g.drawString(header, WIDTH / 2 - (fm.stringWidth(header) / 2), fm.getHeight());
    }

    private static void drawCoordinateSystem(Graphics2D g) {
        //koordinatenachse
        g.setStroke(new BasicStroke(5));
        g.drawLine(PADDING, HEIGHT - PADDING, WIDTH - PADDING, HEIGHT - PADDING);
        g.drawLine(PADDING, HEIGHT - PADDING, PADDING, PADDING);

        //pegelmarkierung y-achse
        g.drawString("0", PADDING - 20, bottomLinePoint + (FONT_SIZE / 2) - 2);
        g.drawLine(PADDING, bottomLinePoint, PADDING - 10, bottomLinePoint);

        g.drawString("1", PADDING - 20, PADDING + TOP_PADDING + (FONT_SIZE / 2) - 2);
        g.drawLine(PADDING, topLinePoint, PADDING - 10, topLinePoint);
    }

    private static void drawSignal(Graphics2D g, String sequence) {
        g.setStroke(new BasicStroke(2));

        int spacing = (WIDTH - 2 * PADDING) / sequence.length();

        int x = PADDING;
        int lastY = sequence.split("")[0].equals("0") ? bottomLinePoint : topLinePoint;

        // Über Bits der Eingabe iterieren
        for (String s : sequence.split("")) {

            // Taktzeitmarkierung auf x-Achse
            if (x > PADDING) {
                g.drawLine(x, bottomPoint + (TOP_PADDING / 2), x, bottomPoint - (TOP_PADDING / 2));
            }

            // Pegel
            int y = s.equals("0") ? bottomLinePoint : topLinePoint;
            g.drawLine(x, lastY, x, y);
            g.drawLine(x, y, x + spacing, y);

            lastY = y;
            x += spacing;
        }
        g.drawLine(x, lastY, x, HEIGHT - PADDING - TOP_PADDING);
    }

}
