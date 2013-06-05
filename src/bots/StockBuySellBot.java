package bots;

import java.util.TimerTask;
import asset.Player;
import Exception.BotException;
import Exception.ShareException;
import innerimpl.AccountManager;
import priceprovider.StockPriceProvider;
import priceprovider.GlobalTimer;

public class StockBuySellBot extends Player implements Bot {
	private AccountManager accountmanager = null;
	private String playerbotname = null;
	private StockPriceProvider provider = null;
	private MyTask task = null;
	
	/**
	 * 
	 * @param manager
	 * @param priceinfo
	 */
	public StockBuySellBot(AccountManager manager, StockPriceProvider priceinfo){
		super("Bot");
		accountmanager = manager;
		provider = priceinfo;
	}
	
	/**
	 * 
	 */
	@Override
	public void start(String playername) throws BotException {
		if(task == null){
		playerbotname = playername;
		MyTask task = new MyTask();
		GlobalTimer.getTimer().addTask(task);
		}else{
			throw new BotException("Bot ist still running");
		}
	}
	/**
	 * 
	 */
	public void doAction() {
		for (int i = 0; i < provider.getAvailableShare().length; i++) {
			try {
				if (accountmanager.diverShareSell(provider.getAvailableShare()[i].name,
						playerbotname)) {
					accountmanager.buyShare(playerbotname,
							provider.getAvailableShare()[i].name, 5);
				}
			} catch (ShareException e) {
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (!accountmanager.diverShareSell(
						provider.getAvailableShare()[i].name, playerbotname)) {
					accountmanager.sellShare(playerbotname,
							provider.getAvailableShare()[i].name, 5);
				}
			} catch (Exception e) {
			}
		}
	}
	/**
	 * 
	 */
	@Override
	public void stop(String playername) throws BotException{
		if(task!=null){
			task.cancel();
		} else {
			throw new BotException("no Bot is startet");
		}
	}
	/**
	 * 
	 * @author daniel und manuel
	 *
	 */
	class MyTask extends TimerTask {
        @Override
        public void run() {
            StockBuySellBot.this.doAction();
        }
    }
}
