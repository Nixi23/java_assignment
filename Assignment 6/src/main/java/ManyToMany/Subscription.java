
package ManyToMany;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subscription {
	@Id
	private int id;
	private String s_name;
	@ManyToMany
	private List<Reader> reader;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public List<Reader> getReader() {
		return reader;
	}
	public void setReader(List<Reader> reader) {
		this.reader = reader;
	}
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", s_name=" + s_name + ", reader=" + reader + "]";
	}
	
	
}
