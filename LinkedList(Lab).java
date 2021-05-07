
import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST IDENTICAL TO OTHER
	@SuppressWarnings("unchecked")
	public LinkedList( LinkedList<T> other )
	{
		Node <T> curr = other.head; // YOU ABSOLUTLEY MUST CHANGE THIS. THIS IS A SHALLOW COPY :(
		while(curr != null)
		{
			curr.setNext(new Node<T>(curr.getData(),null));
			curr = curr.getNext();
	    }
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName ) 
	{
		try 
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{  
				insertAtTail( (T)infile.readLine() );  
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}	
	
	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " -> ";
		}

		return toString + "\n";
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		 if(size() == 0)
		{
			insertAtFront(data);
			return;
		}
		  Node <T> curr = head;
		while(curr !=null && curr.getNext() != null)
		{
		curr = curr.getNext();
		}
		 curr.setNext(new Node<T>(data,null));
		 
		//IF THERE ARE NO NODES IN LIST TACK THIS ONE RIGHT ONTO THE HEAD
		//ELSE GET A REF TO THE VERY LAST NODE AND HANG IT OFF THE LAST NODE'S NEXT REF
	}

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT YOU COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	@SuppressWarnings("unchecked")
	public int size()
	{	//return 0;
	    int count = 0;
		Node <T> curr = head;
	    while(curr != null)
		{
			curr = curr.getNext();
			count++;
		}
		return count;
		// WALK THE LIST AND COUNT THE NODES
	}
	
	// MUST CALL SEARCH AND IF SEARCH RETURNS NULL, THIS METHOD RETURNS FALSE, OTHERWIASE RETURN TRUE
	public boolean contains( T key )
	{
	  
		  return search(key) !=null;
	 }
	// REPLACE WITH CALL TO SEARCH. IF NULL RETURN FALSE ELSE RETURN TRUE
	

	// TRAVERSE LIST FRONT TO BACK LOOKING FOR THIS DATA VALUE.
	// RETURN REF TO THE FIRST NODE THAT CONTAINS THIS KEY. DO -NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	@SuppressWarnings("unchecked")
	public Node<T> search( T key )
	{
		//WALK THE LIST LOOKING FOR 1ST OCCURANCE OF NODE WITH DATA VALUE EQUAL TO KEY
		Node<T> curr = head;
		while (curr != null)
		{
			 if(curr.getData().equals(key))
			 {
				 return curr;
			 }
			 else
			 {
				 curr = curr.getNext();
			 }	 
	}
return null;
} 
}
//EOF