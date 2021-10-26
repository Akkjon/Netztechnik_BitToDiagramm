import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Main {

    private static final int height = 300;
    private static final int width = 1000;
    private static final int padding = 50;
    private static final int topPadding = 20;
    private static final int fontSize = 20;

    public static void main(String[] args) {
        // Eingabe lesen
        System.out.println("Bitfolge eingeben: ");
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();
        scanner.close();

        // Eingabe validieren
        if(sequence.replace("0", "").replace("1", "").trim().length() > 0) {
            throw new IllegalArgumentException(String.format("input  '%s' does not only contain 0 or 1", sequence));
        }

        // Diagramm erzeugen
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        drawSequence(sequence, img.createGraphics());

        // Diagramm als Datei speichern
        File out = new File(System.currentTimeMillis() + ".png");
        try {
            ImageIO.write(img, "png", out);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private static final int topPoint = padding;
    private static final int topLinePoint = topPoint + topPadding;

    private static final int bottomPoint = height - padding;
    private static final int bottomLinePoint = bottomPoint - topPadding;

    private static void drawSequence(String sequence, Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));

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
        g.drawString(header, width/2 - (fm.stringWidth(header) / 2), fm.getHeight());
    }

    private static void drawCoordinateSystem(Graphics2D g) {
        //koordinatenachse
        g.setStroke(new BasicStroke(5));
        g.drawLine(padding, height - padding, width - padding, height - padding);
        g.drawLine(padding, height - padding, padding, padding);

        //pegelmarkierung y-achse
        g.drawString("0", padding - 20, bottomLinePoint + (fontSize / 2) - 2);
        g.drawLine(padding, bottomLinePoint, padding - 10, bottomLinePoint);

        g.drawString("1", padding - 20, padding + topPadding + (fontSize / 2) - 2);
        g.drawLine(padding, topLinePoint, padding - 10, topLinePoint);
    }

    private static void drawSignal(Graphics2D g, String sequence) {
        g.setStroke(new BasicStroke(2));

        int spacing = (width - 2 * padding) / sequence.length();

        int x = padding;
        int lastY = sequence.split("")[0].equals("0") ? bottomLinePoint : topLinePoint;

        // Über Bits der Eingabe iterieren
        for(String s : sequence.split("")) {

            // Taktzeitmarkierung auf x-Achse
            if(x > padding) {
                g.drawLine(x, bottomPoint + (topPadding / 2), x, bottomPoint - (topPadding / 2));
            }

            // Pegel
            int y = s.equals("0") ? bottomLinePoint : topLinePoint;
            g.drawLine(x, lastY, x, y);
            g.drawLine(x, y, x+spacing, y);

            lastY = y;
            x += spacing;
        }
        g.drawLine(x, lastY, x, height - padding - topPadding);
    }

}
