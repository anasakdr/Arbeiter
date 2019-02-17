
package Arbeiter;

public class Arbeiter {
    private int Id;
    private String Name;
    private float Lohn;
   
    private byte[] Image;
    
    public Arbeiter(int pid,String pname,float pLohn,byte[]pimage){
        this.Id=pid;
        this.Name=pname;
        this.Lohn=pLohn;
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
   
    public byte[]getImage(){
        return Image;
    }
}
