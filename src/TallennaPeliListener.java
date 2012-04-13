import java.awt.event.*;

public class TallennaPeliListener implements ActionListener {
    GUI omaGUI;
    
    public TallennaPeliListener(GUI omaGUI)
    {
        this.omaGUI = omaGUI;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (!omaGUI.lauta.tallennaLauta("tallennettuLauta.txt"))
        {
            omaGUI.viesti("Tallennus ei onnistunut");
        }
    }
}
