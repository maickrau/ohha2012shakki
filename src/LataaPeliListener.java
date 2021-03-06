import java.awt.event.*;

/**
 * listeneri napille joka lataa pelin
 */
public class LataaPeliListener implements ActionListener {
    GUI omaGUI;
    
    public LataaPeliListener(GUI omaGUI)
    {
        this.omaGUI = omaGUI;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (!omaGUI.lauta.lataaLauta("tallennettuLauta.txt"))
        {
            omaGUI.viesti("Lataus ei onnistunut");
        }
        else
        {
            omaGUI.naytaTavanLauta();
        }
    }
}
