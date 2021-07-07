package util;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 音频输出工具类
 * @author xuehy
 * @since 2020/6/9
 */
public final class Audio extends Thread {

    private boolean isLoop;
    private URL path;

    public Audio(URL path, boolean isLoop) {
        this.path = path;
        this.isLoop = isLoop;
    }

    @Override
    public synchronized void run() {
        if (path.toString().endsWith(".mp3")) {
            try {
                playMp3();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (path.toString().endsWith(".wav")) {
            try {
                playWav();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (path.toString().endsWith(".flac")) {
            try {
                playFlac();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (path.toString().endsWith(".pcm")) {
            try {
                playPcm();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("未知的文件格式!");
        }
    }

    /**
     * 获取文件的编码信息
     * @return wav或者pcm文件的编码信息
     */
    public String getInfo() {
        /*if (!new File(path.toString()).exists()) {
            throw new RuntimeException("文件不存在");
        }*/
        AudioInputStream ais;
        String result = "";
        try {
            ais = AudioSystem.getAudioInputStream(path);
            AudioFormat af = ais.getFormat();
            result = af.toString();
            System.out.println(result);
            System.out.println("音频总帧数:" + ais.getFrameLength());
            System.out.println("每秒播放帧数:" + af.getSampleRate());
            float len = ais.getFrameLength() / af.getSampleRate();
            System.out.println("音频时长(s):" + len);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    /**
     * 播放MP3音频
     */
    public void playMp3() throws UnsupportedAudioFileException, IOException {
        AudioInputStream stream = null;
        //使用 mp3spi 解码 mp3 音频文件
        MpegAudioFileReader mp = new MpegAudioFileReader();
        stream = mp.getAudioInputStream(path);
        AudioFormat baseFormat = stream.getFormat();
        //设定输出格式为pcm格式的音频文件
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
        //输出到音频
        stream = AudioSystem.getAudioInputStream(format, stream);
        AudioFormat target = stream.getFormat();
        DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, target, AudioSystem.NOT_SPECIFIED);
        SourceDataLine line = null;
        int len = -1;
        try {
            line = (SourceDataLine) AudioSystem.getLine(dinfo);
            line.open(target);
            line.start();
            byte[] buffer = new byte[1024];
            while ((len = stream.read(buffer)) > 0) {
                line.write(buffer, 0, len);
            }
            line.drain();
            line.stop();
            line.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            stream.close();
        }
        if (isLoop) {
            playMp3();
        }
    }

    /**
     * 播放FLAC音频
     */
    public void playFlac() throws UnsupportedAudioFileException, IOException {
        AudioInputStream audio = AudioSystem.getAudioInputStream(path);
        AudioFormat format = audio.getFormat();
        if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);
            audio = AudioSystem.getAudioInputStream(format, audio);
        }
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format, AudioSystem.NOT_SPECIFIED);
        SourceDataLine data = null;
        try {
            data = (SourceDataLine) AudioSystem.getLine(info);
            data.open(format);
            data.start();
            byte[] bt = new byte[1024];
            while (audio.read(bt) != -1) {
                data.write(bt, 0, bt.length);
            }
            data.drain();
            data.stop();
            data.close();
            audio.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (isLoop) {
            playFlac();
        }
    }

    /**
     * 播放WAV音频
     */
    public void playWav() throws UnsupportedAudioFileException, IOException {
        AudioInputStream stream = AudioSystem.getAudioInputStream(path);
        AudioFormat target = stream.getFormat();
        DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, target);
        SourceDataLine line = null;
        int len = -1;
        try {
            line = (SourceDataLine) AudioSystem.getLine(dinfo);
            line.open(target);
            line.start();
            byte[] buffer = new byte[1024];
            while ((len = stream.read(buffer)) > 0) {
                line.write(buffer, 0, len);
            }
            line.drain();
            line.stop();
            line.close();
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (isLoop) {
            playWav();
        }
    }

    /**
     * 播放PCM音频
     */
    public void playPcm() throws UnsupportedAudioFileException, IOException {
        AudioInputStream stream = AudioSystem.getAudioInputStream(path);
        AudioFormat target = stream.getFormat();
        DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, target, AudioSystem.NOT_SPECIFIED);
        SourceDataLine line = null;
        int len = -1;
        try {
            line = (SourceDataLine) AudioSystem.getLine(dinfo);
            line.open(target);
            line.start();
            byte[] buffer = new byte[1024];
            while ((len = stream.read(buffer)) > 0) {
                line.write(buffer, 0, len);
            }
            line.drain();
            line.stop();
            line.close();
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (isLoop) {
            playPcm();
        }
    }

    /**
     * 读取pcm文件
     * @param path pcm文件路径
     * @return AudioInputStream 流
     */
    public AudioInputStream readPcm(String path) throws UnsupportedAudioFileException, IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
        AudioFormat format = stream.getFormat();
        System.out.println(format.toString());
        return stream;
    }

    /**
     * MP3转PCM
     * @param rPath MP3文件路径
     * @param sPath PCM文件保存路径
     */
    public void mp3ToPcm(String rPath, String sPath) {
        File file = new File(rPath);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        AudioInputStream stream = null;
        AudioFormat format = null;
        try {
            AudioInputStream in = null;
            //读取音频文件的类
            MpegAudioFileReader mp = new MpegAudioFileReader();
            in = mp.getAudioInputStream(file);
            AudioFormat baseFormat = in.getFormat();
            //设定输出格式为pcm格式的音频文件
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            //输出到音频
            stream = AudioSystem.getAudioInputStream(format, in);
            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, new File(sPath));
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * WAV转PCM
     * @param wPath wav文件路径
     * @param pPath pcm文件保存路径
     */
    public void wavToPcm(String wPath, String pPath) {
        File file = new File(wPath);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        AudioInputStream stream1;
        try {
            stream1 = AudioSystem.getAudioInputStream(file);
            // 根据实际情况修改 AudioFormat.Encoding.PCM_SIGNED
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, stream1);
            AudioSystem.write(stream2, AudioFileFormat.Type.WAVE, new File(pPath));
            stream2.close();
            stream1.close();
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
