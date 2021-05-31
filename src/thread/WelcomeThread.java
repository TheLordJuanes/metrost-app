/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 3rd 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package thread;

import javafx.application.Platform;
import ui.MetrostGUI;

public class WelcomeThread extends Thread {

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private MetrostGUI metrostGUI;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public WelcomeThread(MetrostGUI metrostGUI) {
        setDaemon(true);
        this.metrostGUI = metrostGUI;
    }

    @Override
    public void run() {
        int countDecreaseOP = 0;
        int countIncreaseOP = 0;
        while (metrostGUI.getLabelChange()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            boolean decrease = true;
            while (decrease) {
                if (countDecreaseOP == 10) {
                    decrease = false;
                    countIncreaseOP = 0;
                    Platform.runLater(new Thread() {

                        @Override
                        public void run() {
                            switch (metrostGUI.getLbWelcome().getText()) {
                                case "Welcome !":
                                    metrostGUI.getLbWelcome().setText("Bienvenid@ !"); // Spanish
                                    metrostGUI.getLbSentence1().setText("Diseña tu");
                                    metrostGUI.getLbSentence2().setText("red de");
                                    metrostGUI.getLbSentence3().setText("transporte.");
                                    break;
                                case "Bienvenid@ !":
                                    metrostGUI.getLbWelcome().setText("Bienvenue !"); // French
                                    metrostGUI.getLbSentence1().setText("Concevez votre");
                                    metrostGUI.getLbSentence2().setText("réseau de");
                                    metrostGUI.getLbSentence3().setText("transport.");
                                    break;
                                case "Bienvenue !":
                                    metrostGUI.getLbWelcome().setText("Willkommen !"); // German
                                    metrostGUI.getLbSentence1().setText("Entwerfen");
                                    metrostGUI.getLbSentence2().setText("Sie Ihr");
                                    metrostGUI.getLbSentence3().setText("Transportnetz.");
                                    break;
                                case "Willkommen !":
                                    metrostGUI.getLbWelcome().setText("ようこそ !"); // Japanese
                                    metrostGUI.getLbSentence1().setText("輸送ネット");
                                    metrostGUI.getLbSentence2().setText("ワークを設");
                                    metrostGUI.getLbSentence3().setText("計します。");
                                    break;
                                case "ようこそ !":
                                    metrostGUI.getLbWelcome().setText("Benvenut@ !"); // Italian
                                    metrostGUI.getLbSentence1().setText("Progetta la");
                                    metrostGUI.getLbSentence2().setText("tua rete di");
                                    metrostGUI.getLbSentence3().setText("trasporto.");
                                    break;
                                case "Benvenut@ !":
                                    metrostGUI.getLbWelcome().setText("Bem-vind@ !"); // Portuguese
                                    metrostGUI.getLbSentence1().setText("Projete sua");
                                    metrostGUI.getLbSentence2().setText("rede de");
                                    metrostGUI.getLbSentence3().setText("transporte.");
                                    break;
                                case "Bem-vind@ !":
                                    metrostGUI.getLbWelcome().setText("مرحبا!"); // Arabic
                                    metrostGUI.getLbSentence1().setText("الخاصة بك");
                                    metrostGUI.getLbSentence2().setText("شبكة النقل");
                                    metrostGUI.getLbSentence3().setText("صمم");
                                    break;
                                case "مرحبا!":
                                    metrostGUI.getLbWelcome().setText("Welkom !"); // Dutch
                                    metrostGUI.getLbSentence1().setText("Ontwerp uw");
                                    metrostGUI.getLbSentence2().setText("transportnetwerk.");
                                    metrostGUI.getLbSentence3().setText("");
                                    break;
                                case "Welkom !":
                                    metrostGUI.getLbWelcome().setText("Witamy !"); // Polish
                                    metrostGUI.getLbSentence1().setText("Zaprojektuj");
                                    metrostGUI.getLbSentence2().setText("swoją sieć");
                                    metrostGUI.getLbSentence3().setText("transportową.");
                                    break;
                                case "Witamy !":
                                    metrostGUI.getLbWelcome().setText("ברוך הבא!"); // Hebrew
                                    metrostGUI.getLbSentence1().setText("התחבורה שלך");
                                    metrostGUI.getLbSentence2().setText("את רשת ");
                                    metrostGUI.getLbSentence3().setText("תכנן");
                                    break;
                                case "ברוך הבא!":
                                    metrostGUI.getLbWelcome().setText("欢迎！"); // Chinese (simplified)
                                    metrostGUI.getLbSentence1().setText("设计您");
                                    metrostGUI.getLbSentence2().setText("的运");
                                    metrostGUI.getLbSentence3().setText("输网络。");
                                    break;
                                case "欢迎！":
                                    metrostGUI.getLbWelcome().setText("Welcome !"); // English
                                    metrostGUI.getLbSentence1().setText("Design your");
                                    metrostGUI.getLbSentence2().setText("transportation");
                                    metrostGUI.getLbSentence3().setText("network.");
                                    break;
                            }
                        }
                    });
                } else {
                    metrostGUI.getLbWelcome().setOpacity(metrostGUI.getLbWelcome().getOpacity() - 0.1);
                    metrostGUI.getLbSentence1().setOpacity(metrostGUI.getLbSentence1().getOpacity() - 0.1);
                    metrostGUI.getLbSentence2().setOpacity(metrostGUI.getLbSentence2().getOpacity() - 0.1);
                    metrostGUI.getLbSentence3().setOpacity(metrostGUI.getLbSentence3().getOpacity() - 0.1);
                    countDecreaseOP++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
            boolean increase = true;
            while (increase) {
                if (countIncreaseOP == 10) {
                    increase = false;
                    countDecreaseOP = 0;
                } else {
                    metrostGUI.getLbWelcome().setOpacity(metrostGUI.getLbWelcome().getOpacity() + 0.1);
                    metrostGUI.getLbSentence1().setOpacity(metrostGUI.getLbSentence1().getOpacity() + 0.1);
                    metrostGUI.getLbSentence2().setOpacity(metrostGUI.getLbSentence2().getOpacity() + 0.1);
                    metrostGUI.getLbSentence3().setOpacity(metrostGUI.getLbSentence3().getOpacity() + 0.1);
                    countIncreaseOP++;
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