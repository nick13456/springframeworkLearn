package learn.proxy.byInterface;

public class ImpProducer implements Producer {

    public void produce(){
        System.out.println("生产了一台电脑");
    }
    public void sell(float money){
        System.out.println("出售了一台电脑,得到:"+money+"元");
    }
    public void sellMany(Integer num, Float money){
        System.out.println("出售了"+num+"台电脑,得到"+num*money+"元");
    }
}
