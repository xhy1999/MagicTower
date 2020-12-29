package util;

import java.io.*;
import java.util.List;

public class CopyUtil {

    public static <T> List<T> deepCopyList(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List<T> copy_list = (List<T>) in.readObject();
        return copy_list;
    }

    public static <T extends Serializable> T deepCopy(T obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(obj);
        oos.close();
        ByteArrayInputStream bai = new ByteArrayInputStream(stream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bai);
        T clonedObj = (T) ois.readObject();
        ois.close();
        return clonedObj;
    }

}
