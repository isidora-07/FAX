package jsp;

public class TableOrder {
	private int Id;
	private String description;
	private int quantity;
	
	public TableOrder()
	{
		
	}
	
	public TableOrder(int id, String description, int quantity) {
		super();
		Id = id;
		this.description = description;
		this.quantity = quantity;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
