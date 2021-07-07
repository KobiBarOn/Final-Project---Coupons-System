package system.core.clients;

public enum ClientType {

	ADMINISTRATOR(1), COMPANY(2), CUSTOMER(3);

	private int clientId;

	private ClientType(int clientId) {
		this.clientId = clientId;
	}

	public ClientType getClientType(int clientId) {
		for (ClientType ct : ClientType.values()) {
			if (ct.clientId == clientId) {
				return ct;
			}
		}
		return null;
	}

	public int getClientId() {
		return clientId;
	}

}
