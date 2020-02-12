// Set class

import java.util.ArrayList;
import java.util.Iterator;

public class Set <E> implements Iterable<E> {
    private ArrayList<E> theSet;

    // empty constructor
    public Set ()
    {
        theSet = new ArrayList<E>();
    }

    // constructor with list
    public Set (ArrayList<E> list)
    {
        theSet = new ArrayList<E>();    // initialize the Set
        int i = 0;

        // copy & insert members to the Set from list
        for (E a:list) {
            E data = list.get(i);
            insert(data);
            i++;
        }
    }

    // copy constructor
    public Set (Set other)
    {
        // deep copy the original ArrayList to a new one
        ArrayList<E>cpSet = new ArrayList<E>();
        for (int i = 0; i < other.theSet.size(); i++){
            cpSet.add((E)other.theSet.get(i));
        }

        this.theSet =  cpSet;
    }

    public E getX(int i)
    {
        return this.theSet.get(i);
    }

	// return the size of the set
    public int getSetSize()
    {
        return this.theSet.size();
    }

    // insert member to the set
    public void insert(E data)
    {
        if (!equals(data))
            this.theSet.add(data);
    }

    // remove member from the set
    public void delete(E data)
    {
        if (equals(data))
            this.theSet.remove(data);
    }

    // union 2 Set by override the Set to be the union Set
    public void union (Set SetAfterUnion)
    {
        int i = 0;

        for (E a:this.theSet) { // for each member in theSet get & insert to the union Set
            E data = this.theSet.get(i);
            SetAfterUnion.insert(data);
            i++;
        }
    }

    // intersect 2 Set by override the Set to be the intersect Set
    public void intersect (Set SetAfterIntersect)
    {
        int i = 0;
        for (E a:this.theSet) { // for each member in theSet get & delete from the intersect Set
            E data = this.theSet.get(i);
            SetAfterIntersect.delete(data);
            i++;
        }
    }

    // check if data is member/element of Set
    public boolean isMember(E data)
    {
        return equals(data); // equal return true if there member in the set that already exist
    }

    // check if Set x is subset of Set y
    public boolean isSubset(Set theSubset)
    {
        boolean ans = true;
        int i;

        for (i = 0; i<theSubset.theSet.size(); i++)
        {
            ans = this.isMember((E) theSubset.getX(i));  // if any member is element of this Set return true
            if (ans == false)
                break;
        }

        return ans;
    }

    // override equals method object
    public boolean equals(Object obj)
    {
        if (obj instanceof Set && this instanceof Set)
            return super.equals(obj);   // return true if the Set is the same Set

        return this.equalsMemberInSet((E)obj);  // check member equals

    }

    // check if there one member in the set that equal to data
    private boolean equalsMemberInSet(E data)
    {
        int i = 0;

        for (E a:this.theSet) { // NOTE for me: n^2 complexity, if I have time I will change that!
            if (data == this.theSet.get(i))
                return true;    // if true there is member in the set that already exist
            i++;
        }

        return false;
    }

    // create Iterator for the Set
    public Iterator<E> iterator()
    {
        Iterator<E> iterator = this.theSet.iterator();
        return iterator;
    }

    // return String representation of Set object
    public String toString ()
    {
        return String.format("" + this.theSet);
    }
	
}
