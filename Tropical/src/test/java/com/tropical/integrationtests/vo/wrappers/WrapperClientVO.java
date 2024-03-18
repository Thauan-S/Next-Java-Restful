package com.tropical.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WrapperClientVO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("_embedded")
	private ClientEmbeddedVO embeded;
	
	public WrapperClientVO() {
	}
	public ClientEmbeddedVO getEmbeded() {
		return embeded;
	}
	public void setEmbeded(ClientEmbeddedVO embeded) {
		this.embeded = embeded;
	}
	@Override
	public int hashCode() {
		return Objects.hash(embeded);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WrapperClientVO other = (WrapperClientVO) obj;
		return Objects.equals(embeded, other.embeded);
	}
	
	
}
