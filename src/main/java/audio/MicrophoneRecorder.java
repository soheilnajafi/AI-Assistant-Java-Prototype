package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MicrophoneRecorder {

    public static void recordAudio(int seconds) {

        File outputFile = new File("recorded_audio.wav");

        AudioFormat format = new AudioFormat(
                44100.0f,   // sample rate
                16,         // sample size
                1,          // mono
                true,       // signed
                false       // little endian
        );

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Microphone not supported!");
            return;
        }

        try {
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            System.out.println("🎤 Recording started...");

            AudioInputStream ais = new AudioInputStream(line);

            Thread stopper = new Thread(() -> {
                try {
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                line.stop();
                line.close();
                System.out.println("✅ Recording finished.");
            });

            stopper.start();

            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, outputFile);

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}