package br.com.rafawhite.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int LAST_ID = 0;
	
	private Integer id;
	private String description;
	private List<String> urls;
	
	public Profile() {
		populateId();
		initializeList();
	}
	
	public Profile(String description) {
		populateId();
		initializeList();
		this.description = description;
	}
	
	public Profile(String description, List<String> urls) {
		populateId();
		this.description = description;
		this.urls = urls;
	}
	
	private void populateId() {
		id = LAST_ID;
		LAST_ID++;
	}
	
	private void initializeList() {
		this.urls = new ArrayList<String>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((urls == null) ? 0 : urls.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (urls == null) {
			if (other.urls != null)
				return false;
		} else if (!urls.equals(other.urls))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", description=" + description + ", urls=" + urls + "]";
	}

}
