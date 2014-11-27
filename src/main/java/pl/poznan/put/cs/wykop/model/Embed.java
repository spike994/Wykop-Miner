package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties("preview")
@Embeddable
public class Embed {
	@Column(name = "embed_plus18")
	private boolean plus18;
	@Column(name = "embed_source")
	private String source;
	@Column(name = "embed_type")
	private String type;
	@Column(name = "embed_name")
	private String url;

	public String getSource() {
		return source;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public boolean isPlus18() {
		return plus18;
	}

	@JsonProperty("plus18")
	public void setPlus18(boolean plus18) {
		this.plus18 = plus18;
	}

	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Embed [type=" + type + ", url=" + url + ", plus18=" + plus18 + ", source=" + source + "]";
	}
}
