package uniresolver.examples;
import uniresolver.driver.did.btcr.DidBtcrDriver;
import uniresolver.driver.did.btcr.bitcoinconnection.BlockcypherAPIBitcoinConnection;
import uniresolver.driver.did.sov.DidSovDriver;
import uniresolver.local.LocalUniResolver;
import uniresolver.result.ResolveResult;

public class TestLocalUniResolver {

	public static void main(String[] args) throws Exception {

		LocalUniResolver uniResolver = LocalUniResolver.getDefault();
		uniResolver.getDrivers().put("btcr", new DidBtcrDriver());
		uniResolver.getDrivers().put("sov", new DidSovDriver());
		uniResolver.getDriver(DidSovDriver.class).setLibIndyPath("./sovrin/lib/");
		uniResolver.getDriver(DidSovDriver.class).setPoolConfigs("_;./sovrin/live.txn;stn;./sovrin/stn.txn;danube;./sovrin/11347-05.txn");
		uniResolver.getDriver(DidSovDriver.class).setPoolVersions("_;1;stn;1;danube;2");
		uniResolver.getDriver(DidBtcrDriver.class).setBitcoinConnection(BlockcypherAPIBitcoinConnection.get());

		ResolveResult ResolveResult1 = uniResolver.resolve("did:sov:WRfXPg8dantKVubE3HX8pw");
		System.out.println(ResolveResult1.toJson());

		ResolveResult ResolveResult2 = uniResolver.resolve("did:btcr:xkrn-xzcr-qqlv-j6sl");
		System.out.println(ResolveResult2.toJson());

		ResolveResult ResolveResult3 = uniResolver.resolve("did:stack:v0:16EMaNw3pkn3v6f2BgnSSs53zAKH4Q8YJg-0");
		System.out.println(ResolveResult3.toJson());
	}
}
