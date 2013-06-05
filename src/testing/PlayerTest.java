package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.AccountException;
import Exception.ShareException;
import asset.CashAccount;
import asset.Player;
import asset.Share;
import asset.ShareDeposit;

public class PlayerTest {
    private final String name = "manu";
    private final long cashaccountstatus = 1000000;
    private final Player player = new Player(name, cashaccountstatus);
    private final ShareDeposit deposit = new ShareDeposit(name);
    private final CashAccount account = new CashAccount(name, cashaccountstatus);
    private final Share share = new Share("BMW", 200L);
    private final int amount = 10;
    
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testGetCashAccount()
    {
        assertTrue("�berpr�ft ob cashaccountstatus identisch ist", player.getCashAccount().getvalue()==cashaccountstatus);
        assertTrue("�berpr�ft ob der name identisch ist", player.getCashAccount().name.equals(name));
    }
    @Test
    public void testGetShareDeposit(){
        assertTrue(player.getShareDeposit().equals(deposit));
    }
    @Test
    public void testSetAccountWorth() throws AccountException{
        player.setAccountWorth(cashaccountstatus);
        assertTrue("�berpr�ft ob die ChashAccounts identisch sind", player.getCashAccount().equals(account));
    }
    @Test
    public void testBuyShare() throws AccountException{
        player.buyShare(share, amount);
        deposit.buyShare(share, amount);
        assertTrue("�berpr�ft ShareDeposit auf Gleichheit", player.getShareDeposit().equals(deposit));
        account.setAccountStatus(account.getvalue()-share.getActualSharePrice()*amount);
        assertTrue("�berpr�ft CashAccount auf Gleichheit", player.getCashAccount().equals(account));
    }
    @Test
    public void testSellShare() throws AccountException, ShareException{
        player.buyShare(share, amount);
        player.sellShare(share, amount-1);
        deposit.buyShare(share, 1);
        assertTrue("�berpr�ft ShareDeposit auf Gleichheit", player.getShareDeposit().equals(deposit));
        account.setAccountStatus(account.getvalue()-share.getActualSharePrice()*1);
        assertTrue("�berpr�ft CashAccount auf Gleichheit", player.getCashAccount().equals(account));
    }

}
