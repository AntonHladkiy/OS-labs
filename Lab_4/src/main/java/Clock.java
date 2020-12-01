import lombok.Getter;

import java.util.*;

public class Clock {
    @Getter
    private int tau;
    @Getter
    private int currentPage=0;
    private List<Integer> pagesPhysical;
    private List<Page> pagesVirtual;
    public Clock(List<Integer> pPhysical, Vector pVirtual,int t){
        pagesPhysical=pPhysical;
        pagesVirtual=new ArrayList<>();
        for(int i=0;i<pVirtual.size();i++){
            Page temp=(Page)pVirtual.elementAt(i);
            pagesVirtual.add(temp);
        }
        tau=t;
    }
    public Page getCurrent(){
        return(pagesVirtual.get(pagesPhysical.get((currentPage)%pagesPhysical.size())));
    }
    public Page getNext(){
        currentPage++; return getCurrent();
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
//    public Page getOldest(){
//        int oldest=0;
//        for(int i=1;i<pagesPhysical.size();i++){
//            if(getByIndex( i ).lastTouchTime>getByIndex( oldest ).lastTouchTime){
//                oldest=i;
//            }
//        }
//        currentPage=oldest;
//        return getByIndex( oldest );
//    }
}
