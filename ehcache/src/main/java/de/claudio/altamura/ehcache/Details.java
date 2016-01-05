package de.claudio.altamura.ehcache;

import java.io.Serializable;

public class Details implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean hasA;
	private boolean hasB;
	
	public boolean isHasA() {
		return hasA;
	}

	public boolean isHasB() {
		return hasB;
	}

	public Details(boolean hasA, boolean hasB) {
		super();
		this.hasA = hasA;
		this.hasB = hasB;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasA ? 1231 : 1237);
		result = prime * result + (hasB ? 1231 : 1237);
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
		Details other = (Details) obj;
		if (hasA != other.hasA)
			return false;
		if (hasB != other.hasB)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Details [hasA=" + hasA + ", hasB=" + hasB + "]";
	}
	
}
