import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {


    private static final int LEVEL_Y_POS = ImageProperties.PADDING + ImageProperties.TOP_PADDING;
    private static final int LEVEL_Y_NEG = ImageProperties.HEIGHT - ImageProperties.PADDING - ImageProperties.TOP_PADDING;
    private static final int LEVEL_Y_ZERO = LEVEL_Y_POS + (LEVEL_Y_NEG - LEVEL_Y_POS) / 2;

    public static void main(String[] args) {
        // Eingabe lesen
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bitfolge eingeben: ");
        String sequence = scanner.nextLine();

        // Eingabe validieren
        if (sequence.replace("0", "").replace("1", "").trim().length() > 0) {
            //throw new IllegalArgumentException(String.format("input  '%s' does not only contain 0 or 1", sequence));
            StringBuilder newSequence = new StringBuilder();
            for (byte abyte : sequence.getBytes()) {
                StringBuilder str = new StringBuilder(Integer.toBinaryString(abyte));
                for(int i = 0; i<8-(str.length()); i++) {
                    str.insert(0, "0");
                }
                if(str.length()>8) {
                    str = new StringBuilder(str.substring(str.length() - 8));
                }
                newSequence.append(str);
            }
            sequence = newSequence.toString();
        }

        System.out.println("Kodierungsverfahren eingeben (NRZ, NRZI, RZ, AMI, Manchester):");
        String method = scanner.nextLine();

        scanner.close();

        // Diagramm erzeugen
        Diagram diagram = switch (method.toLowerCase().strip()) {
            case ("nrz")        -> new NrzDiagram(sequence);
            case ("nrzi")       -> new NrziDiagram(sequence);
            case ("rz")         -> new RzDiagram(sequence);
            case ("ami")        -> new AmiDiagram(sequence);
            case ("manchester") -> new ManchesterDiagram(sequence);
            default -> null;
        };

        if(diagram == null) {
            throw new IllegalArgumentException(String.format("Method '%s' is not supported", method));
        }

        BufferedImage img = diagram.get();

        // Diagramm als Datei speichern
        File out = new File(System.currentTimeMillis() + ".png");
        try {
            ImageIO.write(img, "png", out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
