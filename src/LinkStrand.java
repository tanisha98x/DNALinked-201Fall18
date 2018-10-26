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

	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	public LinkStrand() {
		this("");
	}
	
	

	@Override
	public long size() {
		return mySize;
	}

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

	@Override
	public IDnaStrand getInstance(String source) {
		return null;
	}

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

	@Override
	public IDnaStrand reverse() {
		LinkStrand ans= new LinkStrand();
		Node pointer= myFirst;
		if (myFirst==myLast) {
			StringBuilder first = new StringBuilder(myFirst.info);
			first=first.reverse();
			ans.myFirst=new Node (first.toString());
			ans.mySize=myFirst.info.length();
			return ans;
		}
		
		if (myFirst.next==myLast) {
			StringBuilder first = new StringBuilder(myFirst.info);
			first=first.reverse();
			StringBuilder last = new StringBuilder(myLast.info);
			last=first.reverse();
			ans.myFirst=new Node (last.toString());
			ans.myFirst.next=new Node (first.toString());
			ans.mySize=myFirst.info.length()+myLast.info.length();
			return ans;
		}
		
		else {
			while (pointer!=null) {
				StringBuilder information=new StringBuilder(pointer.info);
				information=information.reverse();
				Node copy=new Node (information.toString());
				ans.myFirst=copy;
				pointer=pointer.next;
				Node copy2= ans.myFirst;
				copy=copy.next;
			}
			
		} ans.mySize= mySize;
		return ans;
		
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

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




