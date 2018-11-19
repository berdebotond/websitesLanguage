import java.util.ArrayList;

class Element{

    protected int value;  //two charachter ascii code, example 'a'+'b'
    protected Element left;
    protected Element right;
}

public class Tree {

    private Element root;
    private String name;

    Tree(){
        this.root = null;
    }
    public Tree(Tree e){
        this.root = e.root;
        this.name = e.name;
    }
    Tree(String name){
        this.name = name;
        this.root = null;
    }
    public String getName(){ //getter for name
        return name;
    }
    public void addName(String name) { //setter for name
        this.name = name;
    }
    //insert an element with a recursion method if Tree is not empty
    private void insertpv(int value, Element a){

        if( a.value > value ){
            if(a.right != null){
                insertpv(value,a.right);
            }else{
                a.right = new Element();
                a.right.value = value;
                a.right.right = null;
                a.right.left = null;
            }
        }else if(a.value < value){
            if(a.left != null){
                insertpv(value,a.left);
            }else{
                a.left = new Element();
                a.left.value = value;
                a.left.right = null;
                a.left.right = null;
            }
        }
    }
    //insert an element if Tree is empty else call insertpv
    public void insert(int value){

        if(root != null ){
            insertpv(value,root);
        }else{
            root = new Element();
            root.value = value;
            root.left = null;
            root.right = null;
        }
    }
    // search a value in the Tree
    private boolean searchpv(int value,Element a){
        if( a.value != value ){
            if( a.value > value ){
                if(a.right != null) {
                    return searchpv(value, a.right);
                }else{
                    return false;
                }
            }else {
                if(a.left != null) {
                    return searchpv(value, a.left);
                }else{
                    return false;
                }
            }
        }else{
            return true;
        }
    }
    private boolean search(int value){
        if( root != null){
            return searchpv(value,root);
        }else {
            return false;
        }
    }
    //how much same value are in two diffrent Tree(recursion method)
    private int checkpv(int point, Element a, Tree e) {
        if (a != null) {
            if(e.search(a.value)) {

                point++;
            }
            if (a.right != null) {
                point = checkpv(point, a.right, e);
            }
            if(a.left != null) {
                point = checkpv(point, a.left, e);
            }
        }else{
            if(e.search(a.value)) {
                point++;
            }
        }
        return point;
    }

    private String toStringpv(Element a){
        String str = "";
        if (a != null) {
            str = str + a.value;
            if (a.right != null) {
                str = str + "\n" + toStringpv(a.right);
            }
            if(a.left != null) {
                str = str +"\n"+ toStringpv(a.left);
            }
        }
        return str;
    }
    @Override
    public String toString() {
        if( root != null ){
            return toStringpv(root);
        }else
            throw new NullPointerException("Tree is empty");
    }

    public int getPoint(Tree e){
        int point = 0;
        if( root != null ) {
            point = checkpv(point, root, e);
        }
        return point;
    }
    private ArrayList<Integer> getArraypw(Element tree, ArrayList<Integer> array){
        if (tree != null) {
            array.add(tree.value);
            if(tree.left != null) {
                getArraypw(tree.left,array);
            }
            if (tree.right != null) {
               getArraypw(tree.right,array);
            }
        }
        return array;
    }
    public ArrayList<Integer> getArray(){
        if( root != null ){
            ArrayList<Integer> array = new ArrayList();
            return getArraypw(root,array);
        }
        else return null;
    }

}
