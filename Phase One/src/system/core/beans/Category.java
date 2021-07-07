package system.core.beans;

public enum Category {

	FOOD(1), ELECTRICITY(2), RESTAURANT(3), VACATION(4), GYM(5), SPA(6);

	private int catId;

	private Category(int catId) {
		this.catId = catId;
	}

	public Category getCategory(int catId) {
		for (Category c : Category.values()) {
			if (c.catId == catId) {
				return c;
			}
		}
		return null;
	}

	public int getCatId() {
		return catId;
	}

}
