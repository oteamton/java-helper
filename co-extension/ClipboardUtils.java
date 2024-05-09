import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class ClipboardUtils {
    public static void copyToClipboard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection selection = new StringSelection(text);

        clipboard.setContents(selection, null);
        System.out.println("Copied " + text);
    }
}
