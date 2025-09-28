// File: MediaAdapterDemo.java

// Target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee interface (advanced players)
interface AdvancedMediaPlayer {
    void playMp4(String fileName);
    // imagine more advanced methods for different formats
}

// Concrete Adaptee
class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}

// Adapter: makes AdvancedMediaPlayer compatible with MediaPlayer
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if ("mp4".equalsIgnoreCase(audioType)) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if ("mp4".equalsIgnoreCase(audioType)) {
            advancedMusicPlayer.playMp4(fileName);
        } else {
            System.out.println("Adapter: Unsupported format " + audioType);
        }
    }
}

// Client that uses MediaPlayer
class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if ("mp4".equalsIgnoreCase(audioType)) {
            // Use adapter to play mp4
            MediaAdapter adapter = new MediaAdapter(audioType);
            adapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

// Demo runner
public class MediaAdapterDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("mp3", "song1.mp3");
        player.play("mp4", "video1.mp4");
        player.play("avi", "movie.avi");
    }
}
