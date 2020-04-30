package jp.co.internous.hope.model.form;

public class SearchItemForm {
	private String goodsSearch;
	private int category;
	
	public String getGoodsSearch() {
		return goodsSearch;
	}
	public void setGoodsSearch(String keywords) {
		this.goodsSearch = keywords;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
}