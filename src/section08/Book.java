package section08;

import java.util.Date;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**Q8-2*/
public class Book implements Comparable<Book>,Cloneable{
	private String title;
	private Date publishDate;
	private String comment;
	@Override
	public boolean equals(Object tar) {
		return EqualsBuilder.reflectionEquals(this, tar);
	}
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public int compareTo(Book tar) {
		return CompareToBuilder.reflectionCompare(this, tar);
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public Book clone(Book tar) {
		Book cloned = new Book();
		cloned.title = tar.title;
		cloned.publishDate = (Date) tar.publishDate.clone();
		cloned.comment = tar.comment;
		return cloned;
	}
}
