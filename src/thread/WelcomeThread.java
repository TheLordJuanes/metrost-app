package thread;

import javafx.application.Platform;
import ui.MetrostGUI;

public class WelcomeThread extends Thread {

    private MetrostGUI metrostGUI;
    private boolean decrease;
    private boolean increase;

    public WelcomeThread(MetrostGUI metrostGUI) {
        setDaemon(true);
        this.metrostGUI = metrostGUI;
        increase = false;
        decrease = true;
    }

    @Override
    public void run() {
        int countdecreaseOP = 0;
        int countincreaseOP = 0;
        while (metrostGUI.getLabelChange()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            while (decrease) {
                if (countdecreaseOP == 10) {
                    decrease = false;
                    increase = true;
                    countincreaseOP = 0;
                    Platform.runLater(new Thread() {

                        @Override
                        public void run() {
                            switch (metrostGUI.getLbWelcome().getText()) {
                                case "Welcome !":
                                    metrostGUI.getLbWelcome().setText("Bienvenid@ !");
                                    break;
                                case "Bienvenid@ !":
                                    metrostGUI.getLbWelcome().setText("Bienvenue !");
                                    break;
                                case "Bienvenue !":
                                    metrostGUI.getLbWelcome().setText("Willkommen !");
                                    break;
                                case "Willkommen !":
                                    metrostGUI.getLbWelcome().setText("ようこそ !");
                                    break;
                                case "ようこそ !":
                                    metrostGUI.getLbWelcome().setText("Benvenut@ !");
                                    break;
                                case "Benvenut@ !":
                                    metrostGUI.getLbWelcome().setText("Bem-vind@ !");
                                    break;
                                case "Bem-vind@ !":
                                    metrostGUI.getLbWelcome().setText("مرحبا!");
                                    break;
                                case "مرحبا!":
                                    metrostGUI.getLbWelcome().setText("Welkom !");
                                    break;
                                case "Welkom !":
                                    metrostGUI.getLbWelcome().setText("Witamy !");
                                    break;
                                case "Witamy !":
                                    metrostGUI.getLbWelcome().setText("ברוך הבא!");
                                    break;
                                case "ברוך הבא!":
                                    metrostGUI.getLbWelcome().setText("欢​迎​光​临 !");
                                    break;
                                case "欢​迎​光​临 !":
                                    metrostGUI.getLbWelcome().setText("Welcome !");
                                    break;
                            }
                        }
                    });
                } else {
                    metrostGUI.getLbWelcome().setOpacity(metrostGUI.getLbWelcome().getOpacity() - 0.1);
                    countdecreaseOP++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
            while (increase) {
                if (countincreaseOP == 10) {
                    increase = false;
                    countdecreaseOP = 0;
                    decrease = true;
                } else {
                    metrostGUI.getLbWelcome().setOpacity(metrostGUI.getLbWelcome().getOpacity() + 0.1);
                    countincreaseOP++;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }
    }
}