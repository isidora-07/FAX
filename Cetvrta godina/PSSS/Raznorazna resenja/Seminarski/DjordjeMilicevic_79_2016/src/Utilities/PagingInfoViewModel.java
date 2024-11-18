package Utilities;

public class PagingInfoViewModel {
	public int totalItems;
	public int itemsPerPage = 5;
	public int currentPage;
	public int totalPages;
	
	public PagingInfoViewModel(int totalItems, int currentPage) {
		this.totalItems = totalItems;
		this.currentPage = currentPage;
		this.totalPages = (int)Math.ceil((double)totalItems / itemsPerPage);
	}
}
