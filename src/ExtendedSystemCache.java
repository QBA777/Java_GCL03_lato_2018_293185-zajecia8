import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;


public class ExtendedSystemCache {
    public static final String OUTPUT_FILE = "C:\\Users\\Jakub\\Desktop\\test.txt";
    static final File test=new File("C:\\Users\\Jakub\\Desktop\\test.txt");
    static final String SER_FILE = "C:\\Users\\Jakub\\Desktop\\jessica.ser";
    static double[] input={1,5,6,77,8,11,13,21,32,69};
    private static SystemCache cache = new SystemCache();
    static OutputStream outputStream=null;
    static InputStream inputStream=null;
    public static void main(String[] args){

        Double output=0.0;
        output=cache.get(input);

        if(output==null){
            output=BasicCalc.calculateSum(input);
            cache.put(input, output);
        }
        System.out.println(cache.get(input));


        //druga wartosc do mapy
        double[] tab={1,2,3,4,5,6,7,8,9,0};
        output=0.0;
        output=cache.get(tab);

        if(output==null){
            output=BasicCalc.calculateSum(tab);
            cache.put(tab, output);
        }
        System.out.println(cache.get(tab));

        //exportCSV(OUTPUT_FILE, SystemCache.cache);  //dziala
        //importCSV(OUTPUT_FILE);     //dziala
        //exportCSV(test, SystemCache.cache);     //dziala
        //importCSV(test);        //dziala
/*
        try{
            outputStream = new FileOutputStream(test);
        }catch (IOException e){
            e.printStackTrace();
        }
        exportCSV(outputStream, SystemCache.cache);     //dziala
        try{
            inputStream=new FileInputStream(test);
        }catch (IOException e){
            e.printStackTrace();
        }
        */
        //importCSV(inputStream);    //jeszcze nie
        //serialize(SER_FILE, SystemCache.cache);
        deserialize(SER_FILE);

    }
    private static void exportCSV(File file, HashMap hmap){
        Set set = hmap.entrySet();
        String splitBy=",";
        Iterator iterator=set.iterator();
        try{
            OutputStream out=new FileOutputStream(file);
            while (iterator.hasNext()){
                Map.Entry mentry=(Map.Entry)iterator.next();
                out.write(mentry.getValue().toString().getBytes());
                out.write(splitBy.getBytes());
                out.write(mentry.getKey().toString().getBytes());
                out.write(splitBy.getBytes());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("zapisano");
    }
    private static void exportCSV(String path, HashMap hmap){
        Set set = hmap.entrySet();
        String splitBy=",";
        Iterator iterator=set.iterator();
        try{
            OutputStream out=new FileOutputStream(path);
            while (iterator.hasNext()){
                Map.Entry mentry=(Map.Entry)iterator.next();
                out.write(mentry.getValue().toString().getBytes());
                out.write(splitBy.getBytes());
                out.write(mentry.getKey().toString().getBytes());
                out.write(splitBy.getBytes());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("zapisano");
    }

    private static void exportCSV(OutputStream stream, HashMap hmap){
        Set set = hmap.entrySet();
        String splitBy=",";
        Iterator iterator=set.iterator();
        try{
            while (iterator.hasNext()){
                Map.Entry mentry=(Map.Entry)iterator.next();
                stream.write(mentry.getValue().toString().getBytes());
                stream.write(splitBy.getBytes());
                stream.write(mentry.getKey().toString().getBytes());
                stream.write(splitBy.getBytes());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("zapisano");
    }

    private static void importCSV(String path){
        String splitBy=",";
        String line = "";
        HashMap<String,String> hmap=new HashMap<>();
        try(BufferedReader br=new BufferedReader(new FileReader(path))){
            while ((line=br.readLine())!=null){
                String[] tmp=line.split(splitBy);
                for (int i=0; i<3; i+=2){
                    System.out.println("key: "+tmp[i+1]);
                    System.out.println("value: "+tmp[i]);
                    hmap.put(tmp[i+1],tmp[i]);
                }
            }
            //System.out.println(hmap);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void importCSV(File file){
        String splitBy=",";
        String line = "";
        HashMap<String,String> hmap=new HashMap<>();
        try(BufferedReader br=new BufferedReader(new FileReader(test))){
            while ((line=br.readLine())!=null){
                String[] tmp=line.split(splitBy);
                for (int i=0; i<3; i+=2){
                    System.out.println("key: "+tmp[i+1]);
                    System.out.println("value: "+tmp[i]);
                    hmap.put(tmp[i+1],tmp[i]);
                }
            }
            System.out.println(hmap);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /*private static void importCSV(InputStream stream){
        String splitBy=",";
        String line = "";
        HashMap<String,String> hmap=new HashMap<>();
        try(BufferedReader br=new BufferedReader(new FileReader(stream))){
            while ((line=br.readLine())!=null){
                String[] tmp=line.split(splitBy);
                for (int i=0; i<3; i+=2){
                    System.out.println("key: "+tmp[i+1]);
                    System.out.println("value: "+tmp[i]);
                    hmap.put(tmp[i+1],tmp[i]);
                }
            }
            System.out.println(hmap);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    */

    /*private static void serialize(String serFile, HashMap hmap) {
        try {
            FileOutputStream fos = new FileOutputStream(serFile);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.close();
            fos.close();
            System.out.println("serialized data saved");
        }catch (IOException e){
            e.printStackTrace();
        }
    }*/

    private static void serialize(File file){

    }

    private static void serialize(OutputStream stream){

    }

    private static void deserialize(String path){
        HashMap<Integer, String> serMap = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            serMap = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            return;
        }
        //display serialized map
        System.out.println("deserialized map: ");
        Set set = serMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key: " + mentry.getKey() + " & Value: ");
            System.out.println(mentry.getValue());
        }
    }

    private static void deserialize(File file){

    }

    private static void deserialize(InputStream stream) {

    }

}
