package yetris;

import model.Model;
import view.MainView;

class App 
{

  public static void main(String[] args) {
    Model model = new Model();
    MainView view = new MainView(model);
    Controller controller = new Controller(model, view);
    
    view.setVisible(true);

    controller.start();
  }
  
}