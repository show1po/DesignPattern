package shixinzhang.dp.proxy;

/**
 * Created by albert on 2017/6/17.
 */
public class Client {
    public static void main(String[] args) {
        IArtist MCHotDog = new IdolArtist("MCHotDog");
        Agent agent = new Agent(MCHotDog);
        agent.talkShow(1000,"康熙來了");
        agent.liveShow(20000,"康熙來了");
    }
}
