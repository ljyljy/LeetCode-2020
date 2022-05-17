import java.io.File;
import java.io.IOException;

//�г�File��һЩ���ò���
public class util {
    /**
     * ����ָ��Ŀ¼�£���������Ŀ¼���������ļ�����ɾ���� lastUpdated ��β���ļ�
     * @param dir Ŀ¼��λ�� path
     * @throws IOException
     */
    public static void listDirectory(File dir) throws IOException {
        if (!dir.exists())
            throw new IllegalArgumentException("Ŀ¼��" + dir + "������.");
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + " ����Ŀ¼��");
        }
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory())
                    //�ݹ�
                    listDirectory(file);
                else{ // ɾ���� lastUpdated ��β���ļ�
                    String fileName = file.getName();
                    boolean isLastupdated = fileName.toLowerCase().endsWith("lastupdated");
                    if (isLastupdated){
                        boolean is_delete = file.delete();
                        System.out.println("ɾ�����ļ��� => " + file.getName() + "  || �Ƿ�ɾ���ɹ��� ==> " + is_delete);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // ָ��maven�ı��زֿ�
        listDirectory(new File("D:\\dubbo-dubbo-2.7.15"));
    }
}