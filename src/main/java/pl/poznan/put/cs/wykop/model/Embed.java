package pl.poznan.put.cs.wykop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties("preview")
public class Embed {
	private boolean plus18;
	private String source;
	private String type;
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
