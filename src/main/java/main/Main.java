package main;

import entity.Tower;

/**
 * @author Xhy
 */
public class Main {

//    private static List<String> libList;

    public static void main(String[] args) {
//        libList = new ArrayList<>();
//        libList.add("gluegen-rt");
//        libList.add("joal");
//        libList.add("jocl");
//        libList.add("jogl_cg");
//        libList.add("jogl_desktop");
//        libList.add("jogl_mobile");
//        libList.add("nativewindow_awt");
//        libList.add("nativewindow_win32");
//        libList.add("newt");
//        libList.add("soft_oal");
//        Main main = new Main();
//        for (int i = 0, length = libList.size(); i < length; i++) {
//            //main.loadLib(libList.get(i));
//        }
//        System.out.println(System.getProperty("java.library.path"));
//        System.loadLibrary("gluegen-rt");
        TowerPanel game = new TowerPanel(new Tower());
        game.start();
    }

//    private synchronized void loadLib(String libName) throws IOException {
//        String systemType = System.getProperty("os.name");
//        //System.out.println("systemType:" + systemType);
//        String BIN_LIB = (systemType.toLowerCase().indexOf("win") != -1) ? "/natives/windows-amd64/" : "/natives/linux-amd64";
//        //System.out.println("BIN_LIB:" + BIN_LIB);
//        String libExtension = (systemType.toLowerCase().indexOf("win") != -1) ? ".dll" : ".so";
//        String libFullName = libName + libExtension;
//        //System.out.println("libFullName:" + libFullName);
//        String nativeTempDir = System.getProperty("java.io.tmpdir");
//        //System.out.println("nativeTempDir:" + nativeTempDir);
//        InputStream in = null;
//        BufferedInputStream reader = null;
//        FileOutputStream writer = null;
//
//
//        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        path = path.substring(0, path.lastIndexOf("/") + 1);
//
//
//        File extractedLibFile = new File(path + libFullName);
//        System.out.println("extractedLibFile:" + extractedLibFile.getPath());
//        if (!extractedLibFile.exists()) {
//            //不存在的话
//            try {
//                //System.out.println("BIN_LIB + libFullName:" + getClass().getResource("/image/npc/npc03_1.png"));
//                //System.out.println("BIN_LIB + libFullName:" + BIN_LIB + libFullName);
//                URL url = getClass().getResource(BIN_LIB + libFullName);
//                //System.out.println("url:" +url);
//                URI uri = url.toURI();
//                File inFile = new File(uri);
//                in = new FileInputStream(inFile);
//                if (in == null) {
//                    inFile = new File(Main.class.getResource(libFullName).toURI());
//                    in = new FileInputStream(inFile);
//                }
//                Main.class.getResource(libFullName);
//                reader = new BufferedInputStream(in);
//                writer = new FileOutputStream(extractedLibFile);
//
//                byte[] buffer = new byte[1024];
//
//                while (reader.read(buffer) > 0) {
//                    writer.write(buffer);
//                    buffer = new byte[1024];
//                }
//            } catch (IOException | URISyntaxException e) {
//                e.printStackTrace();
//            } finally {
//                if (in != null) {
//                    in.close();
//                }
//                if (writer != null) {
//                    writer.close();
//                }
//            }
//        }
//        //System.out.println(path + libName + libExtension);
//        //System.load(path + libName + libExtension);
//    }


}
