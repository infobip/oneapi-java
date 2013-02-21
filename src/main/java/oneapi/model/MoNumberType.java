package oneapi.model;

public class MoNumberType {
    
    private int id;
	private String noType;
	private String description;

    public MoNumberType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoType() {
        return noType;
    }

    public void setNoType(String noType) {
        this.noType = noType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	@Override
	public String toString() {
		return "MoNumberType [id=" + id + ", noType=" + noType + ", description=" + description + "]";
	}
}
