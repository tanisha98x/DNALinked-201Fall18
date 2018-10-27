/**
 * @author Tanisha Nalavadi, tn85
 */
import java.lang.IndexOutOfBoundsException;
public class LinkStrand implements IDnaStrand{
	private LinkStrand myInfo;

	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;

	/**
	 * Constructor that calls initialize on the given String
	 * @param A given String 
	 */
	public LinkStrand(String s) {
		initialize(s);
	}



	/**
	 * Default constructor that  calls the above constructor with a parameter empty String
	 */
	public LinkStrand() {
		this("");
	}



	/**
	 * Creates and returns the LinkStrand Object
	 * @param A given String 
	 * @return A new Link Strand Object
	 */
	@Override
	public long size() {
		return mySize;
	}



	/**
	 * Initializes the LinkStrand object with a String
	 * @param A given String
	 */
	@Override
	public void initialize(String source) {
		myFirst= new Node(source);
		myLast=myFirst;
		mySize=source.length();
		myAppends = 0;	
		myIndex=0;
		myLocalIndex=0;
		myCurrent=myFirst;
	}



	/**
	 * Creates and returns the LinkStrand Object
	 * @param A given String 
	 * @return A new Link Strand Object
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}



	/**
	 * Creates one new node  and adds it to the end of the Link Strand Object and updates instance variables to maintain class invariants as described in the document 
	 * @param A given String 
	 * @return A Link Strand Object
	 */
	@Override
	public IDnaStrand append(String dna) {
		myAppends=myAppends+1;
		mySize=mySize+dna.length();
		if (myFirst==myLast) {
			myLast= new Node(dna);
			myFirst.next=myLast;
		}else {
			Node pointer= new Node(dna);
			myLast.next=pointer;
			myLast=myLast.next;}

		return this;
	}



	/**
	 * Return a new LinkStrand object that's the reverse of the object on which it's called. Even reverses the information in each node using a String builder and reverse method 
	 * @return A new Link Strand Object
	 */
	@Override
	public IDnaStrand reverse() {
		if (myFirst.next==null) {
			StringBuilder thang= new StringBuilder(myFirst.info);
			thang=thang.reverse();
			LinkStrand answer=new LinkStrand(thang.toString());
			return answer;
		}
		Node p1=myFirst;
		StringBuilder thing2= new StringBuilder (p1.info);
		thing2.reverse();		
		LinkStrand copy= new LinkStrand(thing2.toString());
		p1=p1.next;
		while (p1!=null) {
			StringBuilder thing= new StringBuilder (p1.info);
			thing.reverse();	
			copy.append(thing.toString());
			p1=p1.next;
		}		
		Node current=copy.myFirst;
		Node prev=null;
		Node next=copy.myFirst.next;
		while (current!=null) {
			current.next=prev;
			prev=current;
			current=next;
			if (current!=null) {
				next=current.next;	
			}
		}
		copy.myLast=copy.myFirst;
		copy.myFirst=prev;
		return copy;
	} 

	/**
	 * Return the number of times append is called 
	 * @return An int
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}



	/**
	 * Returns a char at a specified index
	 * @param An int
	 * @return A char
	 */
	@Override
	public char charAt(int index) {
		if (index < 0 || index >= mySize) {
			throw new IndexOutOfBoundsException();
		}
		if (myIndex > index) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		while (myIndex + (myCurrent.info.length()-1 - myLocalIndex) < index) {
			myIndex += myCurrent.info.length() - myLocalIndex;
			myLocalIndex = 0;
			myCurrent = myCurrent.next;
		}
		long differnce = index - myIndex;
		myIndex += differnce;
		myLocalIndex += differnce; 
		return myCurrent.info.charAt(myLocalIndex);
	}


	/**
	 *Returns the String representation of the LinkStrand by looping over nodes and appending their values to a StringBuilder object. 
	 * @return A String
	 */
	@Override
	public String toString(){
		StringBuilder ans = new StringBuilder();
		Node head=myFirst;
		while(head!=null) {
			ans.append(head.info);
			head=head.next;
		}return ans.toString();
	}

}





