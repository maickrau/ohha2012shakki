import java.awt.event.*;

public class NaytaSiirrotListener implements ActionListener {
    GUI omaGUI;
    int omaX;
    int omaY;
    public NaytaSiirrotListener(GUI omaGUI, int omaX, int omaY)
    {
        this.omaGUI = omaGUI;
        this.omaX = omaX;
        this.omaY = omaY;
    }
    public void actionPerformed(ActionEvent e)
    {
        omaGUI.naytaSiirrot(omaX, omaY);
    }
}
