package OneToMany_ManyToOne;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "item_data")
public class Item {
	@Id
	private int id;
	private double item_ttl;
	private int quantity;
	@ManyToOne
	private Cart cart;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getItem_ttl() {
		return item_ttl;
	}
	public void setItem_ttl(double item_ttl) {
		this.item_ttl = item_ttl;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", item_ttl=" + item_ttl + ", quantity=" + quantity + ", cart=" + cart + "]";
	}
	
	
}