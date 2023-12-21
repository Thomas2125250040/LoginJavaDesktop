package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelSlide extends JPanel{

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }
    
    public void setAnimate(int animate){
        this.animate = animate;
    }
    
    private int animate = 1;
    private int currentShowing = 0;
    private Component com1;
    private Component com2;
    private Timer timer;
    private List<Component> list = new ArrayList<>();
    private boolean isRight;
    
    public PanelSlide(){
    setBackground(Color.WHITE);
    timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            animate();
        }
    });
    }
    
    public void init(Component ... comp){
        if (comp.length > 0){
            for (Component c : comp){
                list.add(c);
                c.setSize(getSize());
                c.setVisible(false);
                this.add(c);
            }
            Component show = list.get(currentShowing);
            show.setLocation(0,0);
            show.setVisible(true);
        }
        
    }
    
    public void show(int index){
        if (!timer.isRunning()){
            if (list.size() >= 2 && index != currentShowing && index < list.size()){
                com1 = list.get(currentShowing);
                com2 = list.get(index);
                isRight = index < currentShowing;
                currentShowing = index;
                com2.setVisible(true);
                if (isRight){
                    com2.setLocation(-getWidth(), 0);
                }else {
                    com2.setLocation(getWidth(), 0);
                }
                timer.start();
            }
        }
    }
    
    public void animate(){
        if (isRight){
            if (com2.getX() < 0){
                com1.setLocation(com1.getLocation().x + animate, 0);
                com2.setLocation(com2.getLocation().x + animate, 0);
            } else {
                com2.setLocation(0,0);
                com1.setVisible(false);
                timer.stop();
            }
        } else {
             if (com2.getX() > 0){
                com1.setLocation(com1.getLocation().x - animate, 0);
                com2.setLocation(com2.getLocation().x - animate, 0);
            } else {
                com2.setLocation(0,0);
                com1.setVisible(false);
                timer.stop();
            }
        }
    }
}
