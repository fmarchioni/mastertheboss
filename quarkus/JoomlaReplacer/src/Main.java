import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception{
        new Main().replace();

    }

    private void replace() throws  Exception {
        String data = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);

        data = data.replaceAll("<pre class=\"brush:xml\">","<pre class=\"language-markup\"><code>");
        data = data.replaceAll("<pre class=\"brush:java\">","<pre class=\"language-java\"><code>");
        data = data.replaceAll("</pre>","</pre></code>");
        System.out.println(data);

        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // print the last copied thing
        Transferable t = clipBoard.getContents(null);
        if (t.isDataFlavorSupported(DataFlavor.stringFlavor))
            System.out.println(t.getTransferData(DataFlavor.stringFlavor));
        StringSelection clip = new StringSelection(data);
        clipBoard.setContents(clip, clip);
        // prints NOW
        System.out.println(clipBoard.getContents(null).getTransferData(DataFlavor.stringFlavor));
        System.in.read();
    }
}
