
package Arbeiter;

public class Arbeiter {
    private int Id;
    private String Name;
    private float Lohn;
    private String Date;
    private byte[] Image;
    
    public Arbeiter(int pid,String pname,float pLohn,String pdate,byte[]pimage){
        this.Id=pid;
        this.Name=pname;
        this.Lohn=pLohn;
        this.Date=pdate;
        this.Image=pimage;
        
    }
    public int getId(){
        return Id;
       
    }
    public String getName(){
        return Name;
        
    }
    public float getLohn(){
        return Lohn;
    }
    public String getDate(){
        return Date;
    }
    public byte[]getImage(){
        return Image;
    }
}
