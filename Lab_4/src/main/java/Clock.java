import java.util.*;

public class Clock {
    private int currentPage=0;
    private List<Integer> pagesPhysical;
    private List<Page> pagesVirtual;
    public Clock(List<Integer> pPhysical, Vector pVirtual){
        pagesPhysical=pPhysical;
        pagesVirtual=new ArrayList<>();
        for(int i=0;i<pVirtual.size();i++){
            Page temp=(Page)pVirtual.elementAt(i);
            pagesVirtual.add(temp);
        }
    }
    public Page getNext(){
        return(pagesVirtual.get(pagesPhysical.get((currentPage+1)%pagesPhysical.size())));
    }
    public void set(int i,int newVirtual){
        pagesPhysical.set(i,newVirtual);
    }
    public void setR(int i,byte R){
        pagesVirtual.get(pagesPhysical.get(i)).R=R;
    }
    public Page getByIndex(int i){
        return pagesVirtual.get(pagesPhysical.get(i));
    }
    public Page getOldest(){
        int oldest=0;
        for(int i=1;i<pagesPhysical.size();i++){
            if(getByIndex( i ).lastTouchTime>getByIndex( oldest ).lastTouchTime){
                oldest=i;
            }
        }
        currentPage=oldest;
        return getByIndex( oldest );
    }
}
