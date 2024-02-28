package appScaleGenerator;

import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.Scanner;


public class SoundGenerator {
    public static void main(String[] args) {
        //menuPrograma(); 
        todasNotas();

    }

    public static void todasNotas(){ //Executa todas as notas da classe Frequências
        frequencias todas = new frequencias();

        int duration = 500; // 0,5 second

        for ( float nota: todas.getNotas() ) {
            try {
                generateTone(nota, duration);
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }   
        }
    }



    public static void generateTone(float hz, int duration) throws LineUnavailableException {
        float sampleRate = 44100; // Hz (CD quality)
        byte[] buf = new byte[2];
        AudioFormat af = new AudioFormat(sampleRate, 16, 1, true, false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();

        for (int i = 0; i < duration * sampleRate / 1000; i++) {
            double angle = i / (sampleRate / hz) * 2.0 * Math.PI;
            short a = (short) (Math.sin(angle) * 32767);
            buf[0] = (byte) (a & 0xFF);
            buf[1] = (byte) ((a >> 8) & 0xFF);
            sdl.write(buf, 0, 2);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
    
    public static void cabecalho() {

        System.out.println(" "
                        + "  _____  ______  _____             _____    ____   _____     _____   ______    ______   _____   _____            _                  _____\n"
                        + "  / ____||  ____||  __ \\     /\\    |  __ \\  / __ \\ |  __ \\   |  __ \\ |  ____|  |  ____| / ____| / ____|    /\\    | |         /\\     / ____|\n"
                        + " | |  __ | |__   | |__) |   /  \\   | |  | || |  | || |__) |  | |  | || |__     | |__   | (___  | |        /  \\   | |        /  \\   | (___  \n"
                        + " | | |_ ||  __|  |  _  /   / /\\ \\  | |  | || |  | ||  _  /   | |  | ||  __|    |  __|   \\___ \\ | |       / /\\ \\  | |       / /\\ \\   \\___ \\ \n"
                        + " | |__| || |____ | | \\ \\  / ____ \\ | |__| || |__| || | \\ \\   | |__| || |____   | |____  ____) || |____  / ____ \\ | |____  / ____ \\  ____) |\n"
                        + "  \\_____||______||_|  \\_\\/_/    \\_\\|_____/  \\____/ |_|  \\_\\  |_____/ |______|  |______||_____/  \\_____|/_/    \\_\\|______|/_/    \\_\\|_____/\n");
    }
    

    public static void menuPrograma(){
        cabecalho();
        Scanner entrada = new Scanner(System.in);
        System.out.print("\nEscreva C para ouvir a escala de C Maior: ");
        String escolha = entrada.nextLine();
        entrada.close();

        switch (escolha) {
            case "c":
                System.out.println("\nReproduzindo escala...");
                //escalaCM();
                System.out.println("\nFim da escala.");
                break;
        
            default:
                System.out.println("\nmandei apertar o C seu pau no cu");
                return;
        }
    }
}