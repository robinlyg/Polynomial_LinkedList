import java.util.LinkedList;

public class Polynomial {

    private LinkedList<Term> mTerms;

    public Polynomial()
    {
        mTerms = new LinkedList<>();
    }

    //copy constructor
    public Polynomial(Polynomial p)
    {
        this.mTerms = new LinkedList<>();
        for(int i = 0; i < p.mTerms.size(); i++)
        {
            int coefficient = p.mTerms.get(i).getCoefficient();
            int exponent = p.mTerms.get(i).getExponent();

            this.mTerms.add(new Term(coefficient, exponent));
        }
    }

    //addTerm()
    public void addTerm(Term term)
    {
        if(mTerms.size() == 0){
            mTerms.add(term);
        }
        else{
            for(int i = 0; i < this.getNumTerms(); i++) {
                if(term.getExponent() == this.getTerm(i).getExponent()){
                    this.getTerm(i).setCoefficient(term.getCoefficient() + this.getTerm(i).getCoefficient());
                    if(this.getTerm(i).getCoefficient() == 0){
                        this.mTerms.remove(i);
                    }
                    return;
                }
            }

            for(int i = 0; i <this.mTerms.size(); i++){
                if(this.mTerms.get(i).getExponent() < term.getExponent()){
                    this.mTerms.add(i, term);
                    return;
                }
            }
            this.mTerms.add(term);
        }

    }
    public int getNumTerms()
    {return this.mTerms.size();}

    public Term getTerm(int index){
        if(index < this.getNumTerms()){
            return mTerms.get(index);
        }
        return new Term();
    }

    public void clear()
    {this.mTerms.clear();}

    public void add(Polynomial p)
    {
        //need to loop throught the poly in the argument to compare to this
        for(int i = 0; i <p.getNumTerms(); i++)
        {
            //effectively we are adding terms to an existing poly so we can all our addTerm method which will check for matching coef and put the terms in order
            this.addTerm(p.getTerm(i));
        }
    }

    @Override
    public String toString()
    {
        //use stringBuilder to build our return string
        StringBuilder poly = new StringBuilder();
        //if size is 0 of the poly just return "0" (decision to return "0" based on test script)
        if(this.getNumTerms() == 0)
        {
            return "0";
        }
        //if size is 1 remove any '+' from the way we print a single term and replace with an empty string (according to test, dont want it)
        if(this.getNumTerms() == 1 ){
            return String.valueOf(poly.append(getTerm(0))).replaceAll("\\+", "");
        }
        //if our size is larger than 1
        if(this.getNumTerms() > 1)
        {
            //we loop through the object and print adding each term to the stringBuilding and  adding a space for readability
            //originally i had it check if the next number was pos or neg to add in a '+' if necessary but how we set up term that was not needed those signs are already there
            //this original iteration also meant i had to do one less than size to not include our last index because otherwise it was adding the sign to the very end of the string
            //again how we set up term prevents this issue
            for(int i = 0; i < this.getNumTerms(); i++) {

                poly.append(this.getTerm(i)).append(" ");
            }
        }

        return String.valueOf(poly).replaceFirst("\\+", "");
    }

}
