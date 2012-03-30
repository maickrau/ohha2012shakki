
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SiirryTahanListener implements ActionListener {
    GUI omaGUI;
    int vanhaX;
    int vanhaY;
    int uusX;
    int uusY;
    public SiirryTahanListener(GUI omaGUI, int vanhaX, int vanhaY, int uusX, int uusY)
    {
        this.omaGUI = omaGUI;
        this.vanhaX = vanhaX;
        this.vanhaY = vanhaY;
        this.uusX = uusX;
        this.uusY = uusY;
    }
    public void actionPerformed(ActionEvent e)
    {
        omaGUI.siirryTahan(vanhaX, vanhaY, uusX, uusY);
    }
    
}
