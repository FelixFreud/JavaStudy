package section04;

import java.util.Date;

/**Q4-1*/
public class Book implements Comparable<Book>,Cloneable{
	private String title;
	private Date publishDate;
	private String comment;
	@Override
	public boolean equals(Object tar) {
		if(tar == this) return true;
		if(tar == null) return false;
		if(tar instanceof Book) {
			Book b = (Book) tar;
			if(publishDate.equals(b.publishDate) && title.equals(b.title)) return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		int result = 37;
		result = (result*31+title.hashCode())*31+publishDate.hashCode();
		return result;
	}
	@Override
	public int compareTo(Book tar) {
		return this.publishDate.compareTo(tar.publishDate); //Date型のcompareToを利用できる
/*		if(publishDate.before(tar.publishDate)) return 1;
		if(publishDate.after(tar.publishDate)) return -1;
		return 0;
*/	}
	public Book clone(Book tar) {
		Book cloned = new Book();
		cloned.title = tar.title;
		cloned.publishDate = (Date) tar.publishDate.clone();
		cloned.comment = tar.comment;
		return cloned;
	}
}
