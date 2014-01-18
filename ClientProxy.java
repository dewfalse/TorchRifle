package torchrifle;


public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		Entities.registerRenderer();
	}

}
