
package gisapplication;
import java.util.*;

/**
 * Nuran Kasthuriarachchi
 */
public class Main {

static traveller brave=new traveller();

    public static void main(String[] args) {
        brave.travel("AAAA");//LET THE CITIES ARE AAAA,PPPP,MMMM,KKKK,UOM,CCCC,DDDD
        brave.travel("PPPP");
        brave.travel("MMMM");
        brave.travel("KKKK");
        brave.travel("UOM");
        brave.travel("CCCC");
        brave.travel("UOM");
        brave.travel("KKKK");
        brave.travel("DDDD");
        brave.travel("End");
        
        brave.A.getTheRoute();
        brave.B.getTheRoute();
        brave.C.getTheRoute();

    }

}

class traveller{
private String startingPoint;
private String destination;
private String position;

public completePathDevice A;
public reduntantPathDevice B;
public collectWayPointDevice C;



public traveller(){
    startingPoint="Null";
    destination="Null";
    position="Null";

    A=new completePathDevice();
    B=new reduntantPathDevice();
    C=new collectWayPointDevice();
}

    public void travel(String wayPoint){
        if(startingPoint.compareTo("Null")==0){
        setStartingPoint(wayPoint);
    }
    if(wayPoint.compareTo("End")==0){           //if the traveller come to the destination you have to enter End
        destination=position;
    }
    else{
       A.reach(wayPoint);
       B.reach(wayPoint);
       C.reach(wayPoint);
    }

    position=wayPoint;

    }
    public void setStartingPoint(String Point){
        startingPoint=Point;
         }
        public void setDestination(String Point){
         destination=Point;
    }
    public String getStartingPoint(){
    return startingPoint;
    }
    public String getDestination(){
    return destination;
    }
}
abstract class device{
   
    protected ArrayList<String> wayPoint;
          protected int step;
      protected Iterator position;

    abstract public void reach(String point);
   abstract public void getTheRoute();

   
}
class completePathDevice extends device{

    public completePathDevice(){
        wayPoint=new ArrayList();
        step=0;
        position=wayPoint.iterator();
    }
    public void reach(String point){
        wayPoint.add(point);
        step++;
    }
    public void getTheRoute(){
        int track=0;
System.out.println("Complete path is:");
        while(track<step){  
            System.out.println("\t"+wayPoint.get(track));
            track++;  
        }
        step=0;
    }

}
class reduntantPathDevice extends device{
    
        public reduntantPathDevice(){
        wayPoint=new ArrayList();
        step=0;
    }
     public void reach(String point){
            int track;
            if(step==0){
            wayPoint.add(point);
            step++;
            }
            else{
                track=search(point);
            if(track>0){
                wayPoint.remove(track+1);
            }
            else
                wayPoint.add(point);
    }
    }
     public int search(String place){
     int i;
     i=wayPoint.size();
     for(int j=0;j<i;j++){
        if(wayPoint.get(j).compareTo(place)==0)
        return j;
         }
     return -1;
    }
    public void getTheRoute(){
        int track=0;
        System.out.println("Reduntant path is:");
        int no=wayPoint.size();
                 while(track<no){
              System.out.println("\t"+wayPoint.get(track));
            track++;
        }
    }

}
class collectWayPointDevice extends device{
    private ArrayList paths;
    public collectWayPointDevice(){
        wayPoint=new ArrayList();
        step=0;
        paths=new ArrayList();
    }
 public void reach(String point){
              wayPoint.add(point);
                }
 public void getTheRoute(){
 int size=wayPoint.size(),k=0;
 ArrayList<String> temp=new ArrayList(); 
       for(int i=0;i<size;i++){
         k=search(wayPoint.get(i), i+1);
         if(k!=0){
             temp.add(wayPoint.get(i));
             i=k;
             size=wayPoint.size();
         }
         else{
            temp.add(wayPoint.get(i));
         }
         
     }

     wayPoint=temp;
        System.out.println("Collect way point path is:");
     for(String t:wayPoint){
         System.out.println("        "+t);
     }
 }
   
         public int search(String place,int start){
     int i,track=0;
     i=wayPoint.size()-start;
     track=start;
     if(track>=wayPoint.size())
         return 0;

     for(int j=0;j<i;j++,track++){
        if(wayPoint.get(track).compareTo(place)==0){
            if(start==track)
                return 0;
            return track;
        }


     }

     return 0;

    }

}
