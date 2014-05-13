package yetris;

import javax.swing.*; 

import yetris.model.Model;
import yetris.view.MainView;

public class App 
{

  public static void main(String[] args) {

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } 
    catch (UnsupportedLookAndFeelException e) {
    }
    catch (ClassNotFoundException e) {
    }
    catch (InstantiationException e) {
    }
    catch (IllegalAccessException e) {
    }

    Model model = new Model();
    
    MainView view = new MainView(model);
    Controller controller = new Controller(model, view);
    
    view.setVisible(true);

    controller.start();
  }
  
}