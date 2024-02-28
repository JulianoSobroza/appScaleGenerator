package appScaleGenerator;

import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.Scanner;


public class SoundGenerator {
    public static void main(String[] args) {
        menuPrograma(); 
        //todasNotas();

    }

    public static void escalaCM(){ //Executa todas as notas da classe Frequências
        //0 2 4 5 7 9 11 12

        frequencias CM = new frequencias();

        int duration = 500; // 0,5 second

        for(int i = 0; i<=12; i++){
            if(i == 0 || i == 2 || i == 4 || i == 5 || i == 7 || i == 9 || i == 11 || i == 12){
                float executaNota = CM.getNota(i);
                try {
                    generateTone(executaNota, duration);
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }   
            } else {
                continue;
            }
        }
    }

    //  
    public static void destinoPeao(){

        frequencias destino = new frequencias();

        int duration = 500; // 0,5 second
        int[] indices = {16, 16, 40, 38, 16, 16, 40, 38, 16, 16, 38, 37, 16, 16, 38, 37, 16, 16, 37, 35, 33, 32, 30, 28, 26, 25, 23, 21};

        for(int i = 0; i<=indices.length; i++){
            float notaFreq = destino.getNota(indices[i]);
                try {
                    generateTone(notaFreq, duration);
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }   
            }
        }


    public static void todasNotas(){ //Executa todas as notas da classe Frequências
        frequencias todas = new frequencias();

        int duration = 100; // 0,5 second

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
        System.out.print("\nEscreva C ===> escala de C Maior ");
        //System.out.print("\nEscreva D ===> escala de D Maior: ");
        //System.out.print("\nEscreva E ===> escala de E Maior: ");
        //System.out.print("\nEscreva F ===> escala de F Maior: ");
        //System.out.print("\nEscreva G ===> escala de G Maior: ");
        //System.out.print("\nEscreva A ===> escala de A Maior: ");
        //System.out.print("\nEscreva B ===> escala de B Maior: ");
        System.out.println("\nEscreva noel => Destino de peao ");
        System.out.println();

        String escolha = entrada.nextLine();
        entrada.close();

        switch (escolha) {
            case "c":
                System.out.println("\nReproduzindo escala de Dó maior...");
                escalaCM();
                System.out.println("\nFim da escala.");
                return;
            
            case "noel":
                System.out.println("Reproduzindo destino de peao...");
                destinoPeao();
                System.out.println("fim");
                return;
        
            default:
                System.out.println("\nmandei apertar o C seu pau no cu");
                return;
        }
    }
}