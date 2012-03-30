import java.awt.event.*;

public class NaytaTavanLautaListener implements ActionListener {
    GUI omaGUI;
    public NaytaTavanLautaListener(GUI omaGUI)
    {
        this.omaGUI = omaGUI;
    }
    public void actionPerformed(ActionEvent e)
    {
        omaGUI.naytaTavanLauta();
    }
    
}
