package util;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Xhy
 */
public class Audio extends Thread {

    private boolean isLoop;
    private URL path;

    public Audio(URL path, boolean isLoop) {
        this.path = path;
        this.isLoop = isLoop;
    }

    public URL getPath() {
        return path;
    }

    public void setPath(URL path) {
        this.path = path;
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
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
     *
     * @return wav或者pcm文件的编码信息
     * @Title: getInfo
     * @Description: 获取件的编码信息
     * @date 2020年6月21日 14:14:48
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
     * 播放 mp3
     *
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @Title: playMp3
     * @Description: 播放 mp3
     * @date 2020年6月21日 14:21:57
     */
    public void playMp3() throws UnsupportedAudioFileException, IOException {
        AudioInputStream stream = null;
        //使用 mp3spi 解码 mp3 音频文件
        MpegAudioFileReader mp = new MpegAudioFileReader();
        stream = mp.getAudioInputStream(path);
        AudioFormat baseFormat = stream.getFormat();
        //设定输出格式为pcm格式的音频文件
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
        // 输出到音频
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
     * 播放 flac
     *
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @Title: playFlac
     * @Description: 播放 flac
     * @date 2020年6月21日 14:34:20
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
     * 播放 wav
     *
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @Title: playWav
     * @Description: 播放 wav
     * @date 2020年6月21日 14:45:31
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
     * 播放 pcm
     *
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @Title: playPcm
     * @Description: 播放 pcm
     * @date 2020年6月21日 14:48:10
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
     *
     * @param path pcm文件路径
     * @return AudioInputStream
     * @Title: readPcm
     * @Description: 读取pcm文件
     * @date 2020年6月21日 14:53:12
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
     * 获取 mp3 脉冲编码调制
     *
     * @param rPath mp3文件路径
     * @param sPath pcm文件保存路径
     * @return AudioInputStream
     * @Title: getPcmFromMp3
     * @Description: 获取 mp3 脉冲编码调制
     * @date 2020年6月21日 15:01:43
     */
    public void getPcmFromMp3(String rPath, String sPath) {
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
     * Java Music mp3 转 pcm
     *
     * @param rPath MP3文件路径
     * @param sPath PCM文件保存路径
     * @return AudioInputStream
     * @Title: mp3ToPcm
     * @Description: MP3 PCM
     * @date 2020年6月21日 15:08:03
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
     * wav转pcm
     *
     * @param wPath wav文件路径
     * @param pPath pcm文件保存路径
     * @return AudioInputStream
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @Title: wavToPcm
     * @Description: wav转pcm
     * @date 2020年6月21日 15:13:45
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
